import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EventHandler {
    ArrayList<Vehicle> vehicles;
    CarWorkshop volvoWorkshop;
    CarView frame;
    public EventHandler(ArrayList<Vehicle> vehicles, CarWorkshop volvoWorkshop, CarView frame) {
        this.vehicles = vehicles;
        this.volvoWorkshop = volvoWorkshop;
        this.frame = frame;
    }
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

}
