import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
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
    // A list of cars, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    static private final int vehicleWidth = 60;
    static private final int vehicleHeight = 100;
    static private final int controllerHeight = 200;



    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Scania());
        cc.vehicles.add(new Saab95());


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getxPos());
                int y = (int) Math.round(vehicle.getyPos());

                // vänsterkant elr högerkant eler topkant eler nedrekant
                if (x < 0 || x > frame.getWidth() - vehicleWidth || y < 0 || y > frame.getHeight() - vehicleHeight - controllerHeight) {
                    vehicle.stopEngine();
                    vehicle.turnLeft();
                    vehicle.turnLeft();
                    if (x < 0) {
                        vehicle.setxPos(1);
                    } else if (x > frame.getWidth() - vehicleWidth) {
                        vehicle.setxPos(frame.getWidth() - vehicleWidth - 1);
                    }
                    if (y < 0) {
                        vehicle.setyPos(1);
                    } else if (y > frame.getHeight() - vehicleHeight - controllerHeight) {
                        vehicle.setyPos(frame.getHeight() - vehicleHeight - controllerHeight - 1);
                    }
                    vehicle.startEngine();
                }
                frame.drawPanel.moveit(x, y);



                // repaint() calls the paintComponent method of the panel
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
