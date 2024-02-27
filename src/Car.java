import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Car extends Vehicle{
    public Car(int nrDoors, double enginePower, Color color, String modelName, Size size, double xPos, double yPos, boolean engineOn, BufferedImage image) {
        super(nrDoors, enginePower, color, modelName, size,engineOn, xPos, yPos, image);
    }

}
