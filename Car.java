import java.util.Map;

public class Car {
    private String model;
    private String color;
    private CustomerOrder customerOrder;
    static Map<String, Integer> washTimes;
    static Map<String, Integer> dipTimes;
    static Map<String, Integer> sandTimes;
    static Map<String, Integer> primeTimes;
    static Map<String, Integer> paintTimes;

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public String getCarModel() {
        return this.model;
    }

    public String getCarColor() {
        return this.color;
    }

    public void setCarColor(String color) {
        this.color = color;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public CustomerOrder getCustomerOrder() {
        return this.customerOrder;
    }

    public Integer getTaskTime(String task) {
        if (task == "washing") {
            return washTimes.get(model);
        }
        if (task == "dipping") {
            return dipTimes.get(model);
        }
        if (task == "sanding") {
            return sandTimes.get(model);
        }
        if (task == "priming") {
            return primeTimes.get(model);
        }
        if (task == "painting") {
            return paintTimes.get(model);
        }
        return null;
    }

    public String toString() {
        return "Car model: " + this.model + " in " + this.color;
    }

}
