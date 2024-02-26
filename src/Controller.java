import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    Model vehicleModel;
    View frame;
    int gasAmount = 0;


    public Controller(Model vehicleModel, View vehicleView) {
        this.vehicleModel = vehicleModel;
        this.frame = vehicleView;
        addListeners();
    }

    private void addListeners() {
        // TODO: Create more for each component as necessary
        frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.gas(gasAmount);
            }
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Brake");

                vehicleModel.brake(gasAmount);
            }
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("TurboOn");
                vehicleModel.setTurboOn();
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("TurboOff");

                vehicleModel.setTurboOff();
            }
        });

        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("liftUp");

                vehicleModel.raisePlatform();
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("lowerDown");

                vehicleModel.lowerPlatform();
            }
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("start");

                vehicleModel.startVehicles();
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("stop");

                vehicleModel.stopVehicles();
            }
        });
    }


    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicleModel.getVehicles()) {
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
                    if (x < vehicleModel.getVolvoWorkshop().getxPos() + frame.drawPanel.volvoWorkshopImage.getWidth() &&
                            x + vehicleWidth > vehicleModel.getVolvoWorkshop().getxPos() &&
                            y < vehicleModel.getVolvoWorkshop().getyPos() + frame.drawPanel.volvoWorkshopImage.getHeight() &&
                            y + vehicleHeight > vehicleModel.getVolvoWorkshop().getyPos()) {
                        vehicle.stopEngine();
                        vehicleModel.getVolvoWorkshop().loadObject((Volvo240) vehicle);
                        vehicleModel.getVehicles().remove(vehicle);
                        frame.drawPanel.images.remove(vehicle);
                        break;
                    }
                }
                vehicleModel.moveit(vehicle);
                frame.drawPanel.repaint();
            }
        }
    }
    public Timer getTimer(){return timer;}

}


