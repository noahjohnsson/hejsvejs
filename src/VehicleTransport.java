import java.awt.*;
import java.util.Stack;

public class VehicleTransport extends Truck implements Loadable<Vehicle>{
    protected Stack<Vehicle> loadedVehicles;
    protected final Platform platform;

    public VehicleTransport(int nrDoors, double enginePower, Color color, String modelName, double xPos, double yPos, double maxLoad, boolean engineOn) {
        super(nrDoors, enginePower, color, modelName, xPos, yPos, engineOn);
        loadedVehicles = new Stack<>();
        platform = new VehicleTransportPlatform();
        platform.setSize(maxLoad);
    }

    public boolean inRange(Vehicle vehicle) {
        double distance = Math.sqrt(Math.pow((this.getxPos() - vehicle.getxPos()), 2) + Math.pow((this.getyPos() - vehicle.getyPos()), 2));
        if (distance >= 10) { return false; }
            else { return true; }
    }

    public boolean drivable() {return !platform.isRampLoadable();}

    public void loadObject(Vehicle vehicle) {
        if (vehicle.getsize() == Size.LARGE) {
            throw new IllegalStateException("Cannot load cars of this size");
        }
        if (!this.drivable() && inRange(vehicle) && loadedVehicles.size() < this.platform.getSize()) {
            vehicle.setxPos(this.getxPos());
            vehicle.setyPos(this.getyPos());
            loadedVehicles.push(vehicle);
        }  else {throw new IllegalStateException("Cannot load car - check truckbed and distance");}
    }

    public Vehicle unloadObject() {
        Vehicle unloadingVehicle;
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
