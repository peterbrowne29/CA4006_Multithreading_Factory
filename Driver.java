public class Driver {

    private static CarCarrier carCarrier;
    private static Integer carrierCapacity;
    private static Integer numProdLines;
    private static Integer warehouseCapacity;
    public static Timer time = new Timer();
    public static Record rec = new Record();
    public static Boolean isAlive = true;
    public static Integer tic;
    public static Integer carProductionRate;
    public static Integer orderRate;
    public static Integer carMoveRate;
    public static Integer numberOfOrders;
    public static Integer chanceOfOrder;



    public Driver(Integer tic, Integer carrierCap, Integer numProdLine,
            Integer warehouseCap, Integer carProductionRate, Integer orderRate, Integer carMoveRate,
            Integer numberOfOrders, Integer chanceOfOrder) {
        carrierCapacity = carrierCap;
        numProdLines = numProdLine;
        warehouseCapacity = warehouseCap;
        Driver.tic = tic;
        Driver.carProductionRate = carProductionRate;
        Driver.orderRate = orderRate;
        Driver.carMoveRate = carMoveRate;
        Driver.numberOfOrders = numberOfOrders;
        Driver.chanceOfOrder = chanceOfOrder; 
    }

    public static void main() {
        carCarrier = new CarCarrier(carrierCapacity);
        Manufacturer manufacturer = new Manufacturer(carCarrier, numProdLines, warehouseCapacity);
        CarDealer carDealer = new CarDealer(carCarrier, manufacturer);
        Thread dealerThread = new Thread(carDealer);
        dealerThread.start();
    }
}
