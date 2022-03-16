import java.util.LinkedList;
import java.util.Random;

//thread running this

public class CarFactory implements Runnable {
    Warehouse warehouse;
    LinkedList<CustomerOrder> customerOrders;
    static String[] models ;

    Random random = new Random();

    public CarFactory(Warehouse warehouse, LinkedList<CustomerOrder> customerOrders) {
        this.warehouse = warehouse;
        this.customerOrders = customerOrders;
    }

    public void run() {
        while (Driver.isAlive) {
            if (!warehouse.isFull()) {
                synchronized (this.customerOrders) {
                    // decide what model of car to make
                    String carToMake = this.warehouse.getLowestCarCount();
                    if (carToMake != null)
                        makeCar(carToMake);
                    else
                        makeCar(models[random.nextInt(models.length)]);
                }

            }
            try {
                Thread.sleep(Driver.carProductionRate * Driver.tic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void makeCar(String model) {
        this.warehouse.addCar(new Car(model, null));
        String out = Thread.currentThread().getName() + " Car factory created a " + model + " model car at "
                + Driver.time.getTime() + " milliseconds";
        System.out.println(out);
        Driver.rec.addToFile(out);
    }

}
