import java.awt.*;

public class Volvo240 extends Car {

    protected final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, 100, Color.black, "Volvo240", Size.MEDIUM);
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }
}



