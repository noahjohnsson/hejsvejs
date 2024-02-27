import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Truck extends Vehicle {

    public Truck(int nrDoors, double enginePower, Color color, String modelName, double xPos, double yPos, boolean engineOn) {
        super(nrDoors, enginePower, color, modelName, Size.LARGE, xPos, yPos, engineOn);
    }
    
    @Override
    public double speedFactor() {
        return enginePower * 0.005;
    }
}
