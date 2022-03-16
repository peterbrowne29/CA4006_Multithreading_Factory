import java.util.LinkedList;

public class CarCarrier {
    private Integer capacity;
    private LinkedList<Car> cars;

    public CarCarrier(Integer capacity) {
        this.capacity = capacity;
        this.cars = new LinkedList<Car>();
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

    public synchronized Boolean isFull() {
        return this.capacity == this.getNoOfCars();
    }

    public synchronized LinkedList<Car> shipCarrier() {
        @SuppressWarnings("unchecked")
        LinkedList<Car> paintedCars = (LinkedList<Car>) this.cars.clone();
        this.cars.clear();
        return paintedCars;
    }

}
