//data store has to be protected

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Warehouse {
    private Integer capacity = 0;
    private LinkedList<Car> cars;
    HashMap<String, Integer> counts = new HashMap<String, Integer>();
    static String[] models;

    public Warehouse(Integer capacity) {
        this.capacity = capacity;
        this.cars = new LinkedList<Car>();
    }

    public synchronized Car getCar(String model) {
        Integer i = 0;
        // find the car model
        while (i < this.cars.size() && model != cars.get(i).getCarModel()) {
            i++;
        }
        // return it if its not null
        if (i == this.cars.size()) {
            return null;
        } else {
            Car reCar = this.cars.get(i);
            this.cars.remove(reCar);
            return reCar;
        }
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    // get the car model with the lowest count. If even then choose random car
    public String getLowestCarCount() {
        if (this.cars.size() > 0) {
            for (int i = 0; i < this.cars.size(); i++) {
                String model = this.cars.get(i).getCarModel();
                if (counts.get(model) == null) {
                    counts.put(model, 1);
                } else {
                    counts.put(model, counts.get(model) + 1);
                }
            }

            if (counts.size() == this.models.length) {
                Map.Entry<String, Integer> min = null;
                for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                    if (min == null || entry.getValue() <= min.getValue()) {
                        min = entry;
                    }
                }
                counts.clear();
                return min.getKey();
            } else {
                Set<String> keys = counts.keySet();
                for (String key : models) {
                    if (!keys.remove(key))
                        return key;
                }
            }
        }
        return null;
    }

    public synchronized Integer getNoOfCars() {
        if (cars == null) {
            return 0;
        }
        return cars.size();
    }

    public synchronized void addCar(Car car) {
        this.cars.add(car);
    }

    public Boolean isFull() {
        return this.capacity == this.getNoOfCars();
    }
}
