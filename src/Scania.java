import java.awt.*;

public class Scania extends Truck {
    protected double truckbedAngle;
    protected final Platform platform;

    public Scania() {
        super(2, 250, Color.lightGray, "Scania Truck");
        this.truckbedAngle = 0;
        this.platform = new ScaniaPlatform(this);
    }

    public void gas(double amount) {
        if (truckbedAngle == 0) {
            incrementSpeed(amount);
        } else throw new IllegalArgumentException("Truckebed is open");
    }
}

