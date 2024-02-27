import java.awt.*;
import java.awt.image.BufferedImage;

public class Scania extends Truck {
    protected final ScaniaPlatform platform;

    public Scania(double xPos, double yPos, BufferedImage image) {
        super(2, 250, Color.lightGray, "Scania", xPos, yPos, false, image);
        this.platform = new ScaniaPlatform();
    }

    @Override
    public void gas(double amount) {
        if (this.drivable()) {
            super.gas(amount);
        } else throw new IllegalArgumentException("TruckBed is open");
    }

    public boolean drivable() { return this.platform.fullyClosed();}
}

