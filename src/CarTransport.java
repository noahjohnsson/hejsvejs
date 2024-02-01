import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck implements Loadable<Car>{

    protected Stack<Car> loadedCars;
    private final double maxLoad;
    protected boolean rampLoadable;
    protected final Platform platform;
    public CarTransport(double maxLoad) {
        super(2, 250, Color.magenta, "Car Transport");
        loadedCars = new Stack<>();
        this.maxLoad = maxLoad;
        rampLoadable = false;
        platform = new CarTransportPlatform(this);
    }

    public boolean inRange(Car car) {
        double distance = Math.sqrt(Math.pow((this.getxPos() - car.getxPos()), 2) + Math.pow((this.getyPos() - car.getyPos()), 2));
        if (distance >= 10) { return false; }
            else { return true; }
    }

    public void loadObject(Car car) {
        if (car.getsize() == Size.LARGE) {
            throw new IllegalStateException("Cannot load cars of this size");
        }
        if (!platform.drivable() && inRange(car) && loadedCars.size() < this.maxLoad) {
            car.setxPos(this.getxPos());
            car.setyPos(this.getyPos());
            loadedCars.push(car);
        }  else {throw new IllegalStateException("Cannot load car - check truckbed and distance");}
    }

    public Car unloadObject() {
        Car unloadingCar;
        if (!platform.drivable() && !loadedCars.isEmpty()) {
            unloadingCar = loadedCars.pop();
            unloadingCar.setxPos(unloadingCar.getxPos() + 5);
            unloadingCar.setyPos(unloadingCar.getyPos() + 5);
            return unloadingCar;
        }
        else throw new IllegalStateException("Truckbed is not closed for loading or truckbed is empty");
    }

    public void gas(double amount) {
        if (platform.drivable()) {
            incrementSpeed(amount);
        }
        else throw new IllegalStateException("Truckbed is not closed for loading");
    }

    @Override
    public void move() {
        this.setxPos(currentSpeed * this.getxDir());
        this.setyPos(currentSpeed * this.getyDir());

        for(Car loadedCar : loadedCars)
        {
            loadedCar.setxPos(this.getxPos());
            loadedCar.setyPos(this.getyPos());
        }
    }
}
