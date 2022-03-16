import java.util.LinkedList;

public class Manufacturer {
    private Warehouse warehouse;
    private CarFactory carFactory;
    private CarCarrier carCarrier;
    private Integer numProdLines;
    private Integer warehouseCapacity;
    LinkedList<CustomerOrder> customerOrders;

    public Manufacturer(CarCarrier carCarrier, Integer numProdLine, Integer warehouseCap) {
        customerOrders = new LinkedList<CustomerOrder>();
        warehouseCapacity = warehouseCap;
        warehouse = new Warehouse(warehouseCapacity);
        numProdLines = numProdLine;
        carFactory = new CarFactory(this.warehouse, this.customerOrders);
        this.carCarrier = carCarrier;
        Thread factoryThread = new Thread(this.carFactory);
        factoryThread.start();


        for (int i = 0; i < numProdLines; i++) {
            ProductionLine productionLine = new ProductionLine(i, this.warehouse, this.carCarrier, this.customerOrders);
            Thread productionLineThread = new Thread(productionLine);
            productionLineThread.start();
            String out = productionLineThread.getName() + " started new production line at " + Driver.time.getTime()
                    + " milliseconds";
            System.out.println(out);
            Driver.rec.addToFile(out);
        }
    }

    public synchronized void placeOrder(CustomerOrder customerOrder) {
        Integer i = 0;
        synchronized(this.customerOrders){
            while (i < this.customerOrders.size() && customerOrder.getPrice() <= customerOrders.get(i).getPrice()) {
            i++;
            }
        }
        customerOrders.add(i, customerOrder);
        String out = Thread.currentThread().getName() + " Manufacturer logged an customer order "
                + customerOrder.getCustomerId() + " at " + Driver.time.getTime() + " milliseconds";
        System.out.println(out);
        Driver.rec.addToFile(out);
    }
}
