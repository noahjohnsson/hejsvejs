import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class Model {

    private ArrayList<ModelObserver> observers;
    private ArrayList<Vehicle> vehicles;
    private WorldFactory worldFactory;
    private CarWorkshop volvoWorkshop;
    private Timer timer;

    // Constructor
    public Model(){
        this.observers = new ArrayList<>();
        this.worldFactory = new WorldFactory();
        this.vehicles = new ArrayList<>();
        this.addVehicles();
        this.addVolvoWorkshop();
        setupTimer();
    }

    private void addVolvoWorkshop() {
        this.volvoWorkshop = worldFactory.createVolvoWorkshop("Volvo Workshop", 2, 300,300);
    }

    private void addVehicles() {
        vehicles.add(worldFactory.createSaab(200, 200));
        vehicles.add(worldFactory.createVolvo(300, 0));
        vehicles.add(worldFactory.createScania(0, 100));
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.update();
        }
    }

    public void setupTimer() {
        int delay = 50;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                moveVehicles();
                triggerUpdate();
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    public void triggerUpdate() {
        notifyObservers();
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public CarWorkshop getVolvoWorkshop() {
        return volvoWorkshop;
    }

    protected void gas(int amount) {
        double gas = (double) amount / 100;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getEngineStatus()) {
                try {
                    vehicle.gas(gas);
                } catch (IllegalArgumentException ignored) {}
            }
        }
    }


    void brake(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.brake(gas);
        }
    }

    void setTurboOn(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModelName() == "Saab95"){
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    void setTurboOff(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModelName() == "Saab95"){
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    void raisePlatform(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModelName() == "Scania"){
                ((Scania) vehicle).platform.raisePlatform();
            }
        }
    }

    void lowerPlatform(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModelName() == "Scania"){
                ((Scania) vehicle).platform.lowerPlatform();
            }
        }
    }

    void startVehicles(){
        for (Vehicle vehicle : vehicles)
        {
            vehicle.startEngine();
        }
    }

    void stopVehicles(){
        for (Vehicle vehicle : vehicles)
        {
            vehicle.stopEngine();
        }
    }

    public void moveVehicles() {
        for (Vehicle vehicle : vehicles) {
            double x = vehicle.getxPos();
            double y = vehicle.getyPos();
            BufferedImage vehicleImage = vehicle.getImage();
            double vehicleHeight = vehicleImage.getHeight();
            double vehicleWidth = vehicleImage.getWidth();
            double panelHeight = 560;
            double panelWidth = 800;

            // Check collision with walls
            if (x < 0 || x > panelWidth - vehicleWidth || y < 0 || y + vehicleHeight > panelHeight) {
                vehicle.stopEngine();
                vehicle.turnLeft();
                vehicle.turnLeft();
                if (x < 0) {
                    vehicle.setxPos(1);
                } else if (x > panelWidth - vehicleWidth) {
                    vehicle.setxPos(panelWidth - vehicleWidth - 1);
                }
                if (y < 0) {
                    vehicle.setyPos(1);
                } else if (y + vehicleHeight > panelHeight) {
                    vehicle.setyPos(panelHeight - vehicleHeight - 1);
                }
                vehicle.startEngine();
            }

            double workshopX = volvoWorkshop.getxPos();
            double workshopY = volvoWorkshop.getyPos();
            BufferedImage volvoWorkshopImage = volvoWorkshop.getImage();
            double volvoWorkshopHeight = volvoWorkshopImage.getHeight();
            double volvoWorkshopWidth = volvoWorkshopImage.getWidth();

            // Check collision with VolvoWorkshop
            if (vehicle.getModelName() == "Volvo240") {
                if (x < workshopX + volvoWorkshopWidth &&
                        x + vehicleWidth > workshopX &&
                        y < workshopY + volvoWorkshopHeight &&
                        y + vehicleHeight > workshopY) {
                    vehicle.stopEngine();
                    volvoWorkshop.loadObject((Volvo240) vehicle);
                    vehicles.remove(vehicle);
                }
            }
            vehicle.move();
        }
    }

}
