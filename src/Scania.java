import java.awt.*;

public class Scania extends Truck {

    protected final ScaniaPlatform platform;

    public Scania() {
        super(2, 250, Color.lightGray, "Scania Truck");
        this.platform = new ScaniaPlatform();
    }

    @Override
    public void gas(double amount) {
        if (this.drivable()) {
            super.gas(amount);
        } else throw new IllegalArgumentException("Truckebed is open");
    }

    public boolean drivable() { return this.platform.fullyClosed();}
}

