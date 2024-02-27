import java.util.ArrayList;
import java.util.Random;


public class Model {
    private ArrayList<Vehicle> vehicles;
    private WorldFactory worldFactory;
    private CarWorkshop volvoWorkshop;

    // Constructor
    public Model(){
        this.worldFactory = new WorldFactory();
        this.vehicles = new ArrayList<>();
        this.addVehicles();
        this.volvoWorkshop = worldFactory.createVolvoWorkshop("Volvo Workshop", 2, 300,300);
    }

    private void addVehicles() {
        vehicles.add(worldFactory.createSaab(200, 200));
        vehicles.add(worldFactory.createVolvo(0, 300));
        vehicles.add(worldFactory.createScania(0, 100));
    }

    public void addExtraVehicle() {
        if (vehicles.size() < 6) {
            Random rand = new Random();
            int randomxPos = rand.nextInt(800);
            int randomyPos = rand.nextInt(400);
        vehicles.add(worldFactory.createSaab(randomxPos, randomyPos));
    }}

    public void removeVehicle() {
        if (!vehicles.isEmpty()) {
            vehicles.removeFirst();
        }
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public CarWorkshop getVolvoWorkshop() {
        return volvoWorkshop;
    }

    // Calls the gas method for each vehicle once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.gas(gas);
        }
    }

    // Calls the brake method for each vehicle once
    void brake(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.brake(gas);
        }
    }

    // Calls the turboOn method for each Saab95 once
    void setTurboOn(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    // Calls the turboOff method for each Saab95 once
    void setTurboOff(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    // Calls the raisePlatform method for each Scania once
    void raisePlatform(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania){
                ((Scania) vehicle).platform.raisePlatform();
            }
        }
    }

    // Calls the lowerPlatform method for each Scania once
    void lowerPlatform(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania){
                ((Scania) vehicle).platform.lowerPlatform();
            }
        }
    }

    // Calls the startEngine method for each vehicle once
    void startVehicles(){
        for (Vehicle vehicle : vehicles)
        {
            vehicle.startEngine();
        }
    }

    // Calls the stopEngine method for each vehicle once
    void stopVehicles(){
        for (Vehicle vehicle : vehicles)
        {
            vehicle.stopEngine();
        }
    }

    // Moves the vehicle
    public void moveit(Vehicle vehicle) {
        vehicle.move();

    }
}
