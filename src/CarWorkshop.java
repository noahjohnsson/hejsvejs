import java.util.*;

public class CarWorkshop <T extends Car> implements Loadable<T> {
    protected String workshopName;
    protected double maxSize;
    protected LinkedList<T> carsInWorkshop;

    public CarWorkshop(String workshopName, double maxSize) {
        this.workshopName = workshopName;
        this.maxSize = maxSize;
        this.carsInWorkshop = new LinkedList<>();
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

    public T unloadObject() {return carsInWorkshop.remove();}

}
