import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

// thread running this

public class CarDealer implements Runnable {
    Manufacturer manufacturer;
    Integer customerIds = 1;
    private CarCarrier carCarrier;
    private Random random = new Random();
    static String[] colors;
    static String[] models;
    private Integer numberOfOrders = Driver.numberOfOrders;
    private Integer numberOfSuccessfulOrders = 0;


    public CarDealer(CarCarrier carCarrier, Manufacturer manufacturer) {
        this.carCarrier = carCarrier;
        this.manufacturer = manufacturer;
    }

    public void run() {
        Integer i = 1;
        while (Driver.isAlive) {
            if (random.nextInt(99) <= Driver.chanceOfOrder && i <= numberOfOrders) {
                Integer customerId = this.customerIds;
                this.customerIds++;
                String carColor = colors[random.nextInt(colors.length)];
                String carModel = models[random.nextInt(models.length)];
                CustomerOrder customerOrder = new CustomerOrder(customerId, carModel, carColor);
                manufacturer.placeOrder(customerOrder);
                String out = Thread.currentThread().getName() + " Car Dealer received customer order " + customerId
                        + " and sent it to the manufacturer at " + Driver.time.getTime() + " milliseconds";
                System.out.println(out);
                Driver.rec.addToFile(out);
                i++;
            }
            try {
                Thread.sleep(Driver.orderRate * Driver.tic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.carCarrier.isFull()) {
                unloadCars();
            }
        }
    }

    public synchronized void unloadCars() {
        LinkedList<Car> cars = carCarrier.shipCarrier();
        while (cars.size() > 0) {
            Car car = cars.remove();
            String out = Thread.currentThread().getName() + " Customer " + car.getCustomerOrder().getCustomerId()
                    + " recieved car model "
                    + car.getCarModel() + " in " + car.getCarColor() + " at " + Driver.time.getTime() + " milliseconds";
            System.out.println(out);
            Driver.rec.addToFile(out);
            this.numberOfSuccessfulOrders++;
        }
        if (this.numberOfOrders == this.numberOfSuccessfulOrders) {
            try {
                Driver.rec.writeToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Driver.isAlive = false;
            System.out.println("Finished");
        }
    }
}
