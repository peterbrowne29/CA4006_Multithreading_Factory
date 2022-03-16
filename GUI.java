import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GUI implements ActionListener {

    static JFrame frame;

    private static JLabel carrierCapLabel;
    private static JLabel productionLabel;
    private static JLabel warehouseCapLabel;
    private static JLabel carModelLabel;
    private static JLabel coloursLabel;
    private static JLabel dippingTimeLabel;
    private static JLabel washingTimeLabel;
    private static JLabel sandingTimeLabel;
    private static JLabel primingTimeLabel;
    private static JLabel paintingTimeLabel;
    private static JLabel priceLabel;
    private static JLabel ticLable;
    private static JLabel carProductionLable;
    private static JLabel orderRateLable;
    private static JLabel carMoveRateLable;
    private static JLabel numberOfOrdersLable;
    private static JLabel chanceOfOrderLable;

    private static JTextField carrierCap;
    private static JTextField productionLines;
    private static JTextField warehouseCapacity;
    private static JTextField carModel;
    private static JTextField colours;
    private static JTextField dippingTime;
    private static JTextField washingTime;
    private static JTextField sandingTime;
    private static JTextField primingTime;
    private static JTextField paintingTime;
    private static JTextField price;
    private static JTextField tic;
    private static JTextField carProductionRate;
    private static JTextField orderRate;
    private static JTextField carMoveRate;
    private static JTextField numberOfOrders;
    private static JTextField chanceOfOrder;

    private static JButton button;

    Driver driver;

    public static void main(String [] args){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,800));

        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(500, 800, 500, 800));
        panel.setLayout(null);

        tic = new JTextField("1");
        tic.setBounds(400,30,80,25);
        panel.add(tic);

        ticLable = new JLabel("Tic Rate?");
        ticLable.setBounds(200,30,300,25);
        panel.add(ticLable);

        carProductionRate = new JTextField("10");
        carProductionRate.setBounds(400,60,80,25);
        panel.add(carProductionRate);

        carProductionLable = new JLabel("Car Production Rate?");
        carProductionLable.setBounds(200,60,300,25);
        panel.add(carProductionLable);

        orderRate = new JTextField("10");
        orderRate.setBounds(400,90,80,25);
        panel.add(orderRate);

        orderRateLable = new JLabel("Order Rate?");
        orderRateLable.setBounds(200,90,300,25);
        panel.add(orderRateLable);

        numberOfOrders = new JTextField("10");
        numberOfOrders.setBounds(400,120,80,25);
        panel.add(numberOfOrders);

        numberOfOrdersLable = new JLabel("Number Of Orders?");
        numberOfOrdersLable.setBounds(200,120,300,25);
        panel.add(numberOfOrdersLable);

        chanceOfOrder = new JTextField("10");
        chanceOfOrder.setBounds(400,150,80,25);
        panel.add(chanceOfOrder);

        chanceOfOrderLable = new JLabel("Chance Of Order Per Tic?");
        chanceOfOrderLable.setBounds(200,150,300,25);
        panel.add(chanceOfOrderLable);

        carMoveRate = new JTextField("10");
        carMoveRate.setBounds(400,180,80,25);
        panel.add(carMoveRate);

        carMoveRateLable = new JLabel("Car Movement Rate?");
        carMoveRateLable.setBounds(200,180,300,25);
        panel.add(carMoveRateLable);

        carrierCap = new JTextField("10");
        carrierCap.setBounds(400,210,80,25);
        panel.add(carrierCap);

        carrierCapLabel = new JLabel("Carrier Capacity?");
        carrierCapLabel.setBounds(200,210,300,25);
        panel.add(carrierCapLabel);

        productionLines = new JTextField("1");
        productionLines.setBounds(400,240,80,25);
        panel.add(productionLines);

        productionLabel = new JLabel("Num. Production Lines?");
        productionLabel.setBounds(200,240,300,25);
        panel.add(productionLabel);

        warehouseCapacity = new JTextField("10");
        warehouseCapacity.setBounds(400,270,80,25);
        panel.add(warehouseCapacity);

        warehouseCapLabel = new JLabel("Warehouse Capacity?");
        warehouseCapLabel.setBounds(200,270,300,25);
        panel.add(warehouseCapLabel);

        carModel = new JTextField("Audi BMW Porsche Ferrari Volkswagen");
        carModel.setBounds(400,300,300,25);
        panel.add(carModel);

        carModelLabel = new JLabel("Car Models?");
        carModelLabel.setBounds(200,300,300,25);
        panel.add(carModelLabel);

        colours = new JTextField("Red Blue Green Yellow Purple");
        colours.setBounds(400,330,300,25);
        panel.add(colours);

        coloursLabel = new JLabel("Colours?");
        coloursLabel.setBounds(200,330,300,25);
        panel.add(coloursLabel);

        dippingTime = new JTextField("17 39 24 18 20");
        dippingTime.setBounds(400,360,300,25);
        panel.add(dippingTime);

        dippingTimeLabel = new JLabel("Dipping Time? In the order of the models");
        dippingTimeLabel.setBounds(150,360,300,25);
        panel.add(dippingTimeLabel);

        washingTime = new JTextField("17 39 24 18 10");
        washingTime.setBounds(400,390,300,25);
        panel.add(washingTime);

        washingTimeLabel = new JLabel("Washing Time? In the order of the models");
        washingTimeLabel.setBounds(150,390,300,25);
        panel.add(washingTimeLabel);

        sandingTime = new JTextField("17 39 24 18 20");
        sandingTime.setBounds(400,420,300,25);
        panel.add(sandingTime);

        sandingTimeLabel = new JLabel("Sanding Time? In the order of the models");
        sandingTimeLabel.setBounds(150,420,300,25);
        panel.add(sandingTimeLabel);

        primingTime = new JTextField("17 39 24 18 10");
        primingTime.setBounds(400,450,300,25);
        panel.add(primingTime);

        primingTimeLabel = new JLabel("Priming Time? In the order of the models");
        primingTimeLabel.setBounds(150,450,300,25);
        panel.add(primingTimeLabel);

        paintingTime = new JTextField("17 39 24 18 30");
        paintingTime.setBounds(400,480,300,25);
        panel.add(paintingTime);

        paintingTimeLabel = new JLabel("Painting Time? In the order of the models");
        paintingTimeLabel.setBounds(150,480,300,25);
        panel.add(paintingTimeLabel);

        price = new JTextField("200000 1000000 100000 40000 3000");
        price.setBounds(400,510,300,25);
        panel.add(price);

        priceLabel = new JLabel("Car Price? In the order of the models");
        priceLabel.setBounds(150,510,300,25);
        panel.add(priceLabel);

        button = new JButton("Start Program");
        button.setBounds(300, 540, 200, 25);
        button.addActionListener(new GUI());
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Car Painting Service");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Map<String, Integer> wash = new HashMap<>();
        Map<String, Integer> dip = new HashMap<>();
        Map<String, Integer> sand = new HashMap<>();
        Map<String, Integer> prime = new HashMap<>();
        Map<String, Integer> paint = new HashMap<>();

        Map<String, Integer> prices = new HashMap<>();

        String[] listColours = String.valueOf(colours.getText()).split(" ");
        String[] listModels = String.valueOf(carModel.getText()).split(" ");

        CarDealer.models = listModels;
        CarDealer.colors = listColours;
        CarFactory.models = listModels;
        Warehouse.models = listModels;

        String[] listWash = String.valueOf(washingTime.getText()).split(" ");
        String[] listDip = String.valueOf(dippingTime.getText()).split(" ");
        String[] listSand = String.valueOf(sandingTime.getText()).split(" ");
        String[] listPrime = String.valueOf(primingTime.getText()).split(" ");
        String[] listPaint = String.valueOf(paintingTime.getText()).split(" ");
        String[] listPrices = String.valueOf(price.getText()).split(" ");

        for(int i = 0; i < listColours.length; i++){
            wash.put(listModels[i],Integer.valueOf(listWash[i]));
            dip.put(listModels[i],Integer.valueOf(listDip[i]));
            sand.put(listModels[i],Integer.valueOf(listSand[i]));
            prime.put(listModels[i],Integer.valueOf(listPrime[i]));
            paint.put(listModels[i],Integer.valueOf(listPaint[i]));
            prices.put(listModels[i],Integer.valueOf(listPrices[i]));
        }

        Car.washTimes = wash;
        Car.dipTimes = dip;
        Car.sandTimes = sand;
        Car.primeTimes = prime;
        Car.paintTimes = paint;
        CustomerOrder.prices = prices;

        Integer carrierCapacity = Integer.valueOf(carrierCap.getText());
        Integer numProdLines = Integer.valueOf(productionLines.getText());
        Integer warehouseCap = Integer.valueOf(warehouseCapacity.getText());
        Integer ticValue = Integer.valueOf(tic.getText());
        Integer productionRateValue = Integer.valueOf(carProductionRate.getText());
        Integer orderRateValue = Integer.valueOf(orderRate.getText());
        Integer movementRateValue = Integer.valueOf(carMoveRate.getText());
        Integer numberOfOrdersValue = Integer.valueOf(numberOfOrders.getText());
        Integer chanceOfOrderValue = Integer.valueOf(chanceOfOrder.getText());

        this.driver = new Driver(ticValue, carrierCapacity, numProdLines, warehouseCap,
         productionRateValue, orderRateValue, movementRateValue, numberOfOrdersValue, chanceOfOrderValue);
        this.driver.main();
        frame.dispose();
    }
}
