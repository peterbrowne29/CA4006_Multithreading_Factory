public class Robot implements Runnable {
    private Car car;
    private Boolean hasCar = false;
    private Boolean isFinished = false;
    private String robotName;
    private Integer productionId;

    public Robot(String robotName, Integer productionId) {
        this.robotName = robotName;
        this.productionId = productionId;
    }

    public void run() {
        while (Driver.isAlive) {
            synchronized(this){
                // check to see if a unprocessed car is present
                if (this.isUnprocessedWaiting()) {
                    String out = Thread.currentThread().getName() + " " + this.robotName.substring(0, 1).toUpperCase()
                            + this.robotName.substring(1) + " Robot on production Line " + this.productionId + " is "
                            + this.robotName + " customer order "
                            + car.getCustomerOrder().getCustomerId() + " at " + Driver.time.getTime()
                            + " milliseconds";
                    System.out.println(out);
                    Driver.rec.addToFile(out);
                    try {
                        Thread.sleep(car.getTaskTime(this.robotName) * Driver.tic);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.isFinished = true;
                }
            }
        }
    }

    public Boolean isReady() {
        return !this.hasCar;
    }

    public Boolean hasFinishedCar() {
        return this.isFinished;
    }

    // get the car for moving to next task
    public synchronized Car getCar(String nextTask) {
        String out = Thread.currentThread().getName() + " Production line " + this.productionId
                + " is moving customer order "
                + this.car.getCustomerOrder().getCustomerId() + " from " + this.robotName + " to " + nextTask
                + " at " + Driver.time.getTime() + " milliseconds";
        System.out.println(out);
        Driver.rec.addToFile(out);
        try {
            Thread.sleep(Driver.carMoveRate * Driver.tic);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isFinished = false;
        this.hasCar = false;
        return this.car;
    }

    public String getName() {
        return this.robotName;
    }

    public synchronized void move(Car car) {
        this.hasCar = true;
        this.car = car;
    }

    public Boolean isUnprocessedWaiting() {
        return this.hasCar && !this.isFinished;
    }
}