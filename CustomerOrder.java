import java.util.Map;

public class CustomerOrder {
    private Integer id;
    private String carModel;
    private String carColor;
    private Integer price;
    static Map<String, Integer> prices;

    public CustomerOrder(Integer id, String carModel, String carColor) {
        this.id = id;
        this.carModel = carModel;
        this.carColor = carColor;
        this.price = prices.get(carModel);
    }

    public Integer getCustomerId() {
        return this.id;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public String getCarColor() {
        return this.carColor;
    }

    public Integer getPrice() {
        return this.price;
    }

    public String toString() {
        return "ID: " + this.id + " carModel: " + this.carModel + " carColor: " + this.carColor;
    }
}