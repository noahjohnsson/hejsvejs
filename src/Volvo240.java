import javax.imageio.ImageIO;
import java.awt.*;

public class Volvo240 extends Vehicle {
    protected final static double trimFactor = 1.25;

    public Volvo240(double xPos, double yPos, BufferedImage image){
        super(4, 100, Color.BLACK, "Volvo240", Size.MEDIUM, xPos, yPos, false, image);
    }

    @Override
    protected double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }

    }




