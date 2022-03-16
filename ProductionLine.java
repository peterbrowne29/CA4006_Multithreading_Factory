import java.util.LinkedList;

// thread running this

public class ProductionLine implements Runnable {
    private Integer id;
    private Warehouse warehouse;
    private CarCarrier carCarrier;
    LinkedList<CustomerOrder> customerOrders;
    private Robot washingRobot;
    private Robot dippingRobot;
    private Robot sandingRobot;
    private Robot primingRobot;
    private Robot paintingRobot;

    public ProductionLine(Integer id, Warehouse warehouse, CarCarrier carCarrier,
            LinkedList<CustomerOrder> customerOrders) {
        this.id = id;
        this.washingRobot = new Robot("washing", id);
        this.dippingRobot = new Robot("dipping", id);
        this.sandingRobot = new Robot("sanding", id);
        this.primingRobot = new Robot("priming", id);
        this.paintingRobot = new Robot("painting", id);
        this.warehouse = warehouse;
        this.carCarrier = carCarrier;
        this.customerOrders = customerOrders;
    }

    public void run() {
        Thread washingRobotThread = new Thread(this.washingRobot);
        Thread dippingRobotThread = new Thread(this.dippingRobot);
        Thread sandingRobotThread = new Thread(this.sandingRobot);
        Thread primingRobotThread = new Thread(this.primingRobot);
        Thread paintingRobotThread = new Thread(this.paintingRobot);
        washingRobotThread.start();
        dippingRobotThread.start();
        sandingRobotThread.start();
        primingRobotThread.start();
        paintingRobotThread.start();

        while (Driver.isAlive) {
            synchronized(this){
                this.startNewCarIfReady();
                synchronized (this.washingRobot) {
                    synchronized (this.dippingRobot) {
                        checkAndMove(washingRobot, dippingRobot);
                    }
                }
                synchronized (this.dippingRobot) {
                    synchronized (this.sandingRobot) {
                        checkAndMove(dippingRobot, sandingRobot);
                    }
                }
                synchronized (this.sandingRobot) {
                    synchronized (this.primingRobot) {
                        checkAndMove(sandingRobot, primingRobot);
                    }
                }

                synchronized (this.primingRobot) {
                    synchronized (this.paintingRobot) {
                        checkAndMove(primingRobot, paintingRobot);
                    }
                }

                synchronized (this.paintingRobot) {
                    if (paintingRobot.hasFinishedCar()) {
                        loadCar();       
                    }
                }
            }
        }
    }

    public void loadCar(){
        synchronized(this.carCarrier){
            this.carCarrier.addCar(paintingRobot.getCar("car carrier"));
        }
    }


    // only once an order exists and the washing robot is ready start a new order
    public void startNewCarIfReady() {
        synchronized (this.customerOrders) {
            synchronized (this.warehouse) {
                synchronized (this.washingRobot) {
                    if (this.customerOrders.peek() != null && this.washingRobot.isReady()) {
                        Integer i = 0;
                        while (i < this.customerOrders.size()) {
                            CustomerOrder order = this.customerOrders.get(i);
                            Car car = this.warehouse.getCar(order.getCarModel()); // returns null if no car of model is available

                            // start the painting process
                            if (car != null) {
                                car.setCarColor(order.getCarColor());
                                car.setCustomerOrder(order);
                                this.washingRobot.move(car);
                                this.customerOrders.remove(order);
                                String out = Thread.currentThread().getName() + " Production Line " + this.id
                                        + " is starting order "
                                        + order.getCustomerId() + " at " + Driver.time.getTime() + " milliseconds";
                                System.out.println(out);
                                Driver.rec.addToFile(out);
                                break;
                            // else look for an order with a different model
                            } else {
                                i++;
                                while (i < this.customerOrders.size()
                                        && order.getCarModel() != this.customerOrders.get(i).getCarModel()) {
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void checkAndMove(Robot fromRobot, Robot toRobot) {
        if (fromRobot.hasFinishedCar() && toRobot.isReady()) {
            toRobot.move(fromRobot.getCar(toRobot.getName()));
        }
    }
}
