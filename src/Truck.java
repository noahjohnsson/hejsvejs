import java.awt.*;

public abstract class Truck extends Vehicle {

    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName, Size.LARGE);
    }
    
    @Override
    public double speedFactor() {
        return enginePower * 0.005;
    }
}
