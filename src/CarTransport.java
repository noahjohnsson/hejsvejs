import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck implements Loadable<Car>{
    protected Stack<Car> loadedVehicles;
    protected final Platform platform;

    public CarTransport(int nrDoors, double enginePower, Color color, String modelName, double xPos, double yPos, double maxLoad, boolean engineOn) {
        super(nrDoors, enginePower, color, modelName, xPos, yPos, engineOn);
        loadedVehicles = new Stack<>();
        platform = new CarTransportPlatform();
        platform.setSize(maxLoad);
    }

    public boolean inRange(Car car) {
        double distance = Math.sqrt(Math.pow((this.getxPos() - car.getxPos()), 2) + Math.pow((this.getyPos() - car.getyPos()), 2));
        if (distance >= 10) { return false; }
            else { return true; }
    }

    public boolean drivable() {return !platform.isRampLoadable();}

    public void loadObject(Car car) {
        if (car.getsize() == Size.LARGE) {
            throw new IllegalStateException("Cannot load cars of this size");
        }
        if (!this.drivable() && inRange(car) && loadedVehicles.size() < this.platform.getSize()) {
            car.setxPos(this.getxPos());
            car.setyPos(this.getyPos());
            loadedVehicles.push(car);
        }  else {throw new IllegalStateException("Cannot load car - check truckbed and distance");}
    }

    public Car unloadObject() {
        Car unloadingCar;
        if (!this.drivable() && !loadedVehicles.isEmpty()) {
            unloadingVehicle = loadedVehicles.pop();
            unloadingVehicle.setxPos(unloadingVehicle.getxPos() + 5);
            unloadingVehicle.setyPos(unloadingVehicle.getyPos() + 5);
            return unloadingVehicle;
        }
        else throw new IllegalStateException("Truckbed is not closed for loading or truckbed is empty");
    }

    @Override
    public void gas(double amount) {
        if (this.drivable()) {
            super.gas(amount);
        }
        else throw new IllegalStateException("Truckbed is not closed for loading");
    }

    @Override
    public void move() {
        super.move();
        for(Vehicle loadedVehicle : loadedVehicles)
        {
            loadedVehicle.setxPos(this.getxPos());
            loadedVehicle.setyPos(this.getyPos());
        }
    }
}
