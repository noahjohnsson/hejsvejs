import java.awt.*;

public abstract class Car extends Vehicle{
    public Car(int nrDoors, double enginePower, Color color, String modelName, Size size, double xPos, double yPos, boolean engineOn) {
        super(nrDoors, enginePower, color, modelName, size, xPos, yPos, engineOn);
    }

}
