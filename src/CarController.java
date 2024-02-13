import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    CarWorkshop<Volvo240> volvoWorkshop;

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();


        cc.vehicles.add(new Volvo240(0, 300));
        cc.vehicles.add(new Saab95(0, 100));
        cc.vehicles.add(new Scania(0, 200));
        cc.volvoWorkshop = new CarWorkshop<>("Volvo Workshop", 2, 300,300);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Add images
        cc.frame.drawPanel.addVehicleImages(cc.vehicles);
        cc.frame.drawPanel.addWorkshopImage(cc.volvoWorkshop);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                double vehicleHeight = frame.drawPanel.images.get(vehicle).getHeight();
                double vehicleWidth = frame.drawPanel.images.get(vehicle).getWidth();
                double panelHeight = frame.drawPanel.getHeight();
                double panelWidth = frame.drawPanel.getWidth();
                double x = vehicle.getxPos();
                double y = vehicle.getyPos();

                // leftEdge or rightEdge or topEdge or bottomEdge
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
                if (vehicle instanceof Volvo240) {
                    // Check collision with VolvoWorkshop
                    if (x < volvoWorkshop.getxPos() + frame.drawPanel.volvoWorkshopImage.getWidth() &&
                            x + vehicleWidth > volvoWorkshop.getxPos() &&
                            y < volvoWorkshop.getyPos() + frame.drawPanel.volvoWorkshopImage.getHeight() &&
                            y + vehicleHeight > volvoWorkshop.getyPos()) {
                        vehicle.stopEngine();
                        volvoWorkshop.loadObject((Volvo240) vehicle);
                        vehicles.remove(vehicle);
                        frame.drawPanel.images.remove(vehicle);
                        break;
                    }
                }
                frame.drawPanel.moveit(vehicle);
                frame.drawPanel.repaint();
            }
        }
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

}
