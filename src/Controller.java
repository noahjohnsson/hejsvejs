import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Controller {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    Model model;
    View frame;
    int gasAmount = 0;
    ArrayList<Vehicle> vehicles;
    CarWorkshop<Volvo240> volvoWorkshop;


    public Controller(Model model, View vehicleView) {
        this.model = model;
        this.vehicles = model.getVehicles();
        this.volvoWorkshop = model.getVolvoWorkshop();
        this.frame = vehicleView;
        addListeners();
    }

    private void addListeners() {
        frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gas(gasAmount);
            }
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.brake(gasAmount);}
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setTurboOn();
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setTurboOff();
            }
        });

        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.raisePlatform();
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.lowerPlatform();
            }
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.startVehicles();
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.stopVehicles();
            }
        });
    }
    public Timer getTimer(){return timer;}

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                double x = vehicle.getxPos();
                double y = vehicle.getyPos();
                BufferedImage vehicleImage = vehicle.getImage();
                double vehicleHeight = vehicleImage.getHeight();
                double vehicleWidth = vehicleImage.getWidth();
                double panelHeight = frame.drawPanel.getHeight();
                double panelWidth = frame.drawPanel.getWidth();

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
                    if (vehicle instanceof Volvo240) {
                        if (x < workshopX + volvoWorkshopWidth &&
                                x + vehicleWidth > workshopX &&
                                y < workshopY + volvoWorkshopHeight &&
                                y + vehicleHeight > workshopY) {
                            vehicle.stopEngine();
                            volvoWorkshop.loadObject((Volvo240) vehicle);
                            vehicles.remove(vehicle);
                            //frame.drawPanel.repaint();

                        }
                    }
                    model.moveit(vehicle);
                    frame.drawPanel.repaint();
                }
                //model.moveit(vehicle);
                frame.drawPanel.repaint();

            }
        }
    }



