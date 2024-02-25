import javax.swing.*;
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


        // TODO: Create more for each component as necessary
        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.gas(gasAmount);
            }
        });


        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.brake(gasAmount);
            }
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.setTurboOn();
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.setTurboOff();
            }
        });

        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.raisePlatform();
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.lowerPlatform();
            }
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicleModel.startVehicles();
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                System.out.println(x);
                System.out.println(y);


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
                frame.drawPanel.moveit(vehicle);
                frame.drawPanel.repaint();
            }
        }
    }
    public Timer getTimer(){return timer;}

}


