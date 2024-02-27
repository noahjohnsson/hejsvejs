import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class CarWorkshop <T extends Vehicle> implements Loadable<T> {
    protected String workshopName;
    protected double maxSize;
    protected ArrayList<T> carsInWorkshop;
    private double xPos;
    private double yPos;

    private BufferedImage image;

    public CarWorkshop(String workshopName, double maxSize, double xPos, double yPos) {
        this.workshopName = workshopName;
        this.maxSize = maxSize;
        this.carsInWorkshop = new ArrayList<>();
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = image;
    }

    public void setMaxSize(double size) {
        maxSize = size;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public void loadObject(T car) {
        if (carsInWorkshop.size() < maxSize && !carsInWorkshop.contains(car)) {
            carsInWorkshop.add(car);
        } else {
            throw new IllegalArgumentException("Workshop cannot handle this model or car already in Workshop");
        }
    }

    public T unloadObject() {return carsInWorkshop.removeFirst();}
    //Ändra detta när vi fattar hur man gör det

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public BufferedImage getImage() {return image;}
}
