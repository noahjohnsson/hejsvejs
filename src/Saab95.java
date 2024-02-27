import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Saab95 extends Vehicle {
    private boolean turboOn;

    public Saab95(double xPos, double yPos, BufferedImage image) {
        super(2, 125, Color.red, "Saab95", Size.SMALL, xPos, yPos, false, image);
        this.turboOn = false;
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}

