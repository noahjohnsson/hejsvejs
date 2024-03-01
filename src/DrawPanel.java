import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;


// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    ArrayList<Vehicle> vehicles;
    CarWorkshop<Volvo240> volvoWorkshop;


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, Model model) {
        this.vehicles = model.getVehicles();
        this.volvoWorkshop = model.getVolvoWorkshop();
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw vehicles
        for (Vehicle vehicle : vehicles) {
            BufferedImage image = vehicle.getImage();
            g.drawImage(image, (int) vehicle.getxPos(), (int) vehicle.getyPos(), null);
        }

        // Draw Volvoworkshop
        BufferedImage workshopImage = volvoWorkshop.getImage();
        g.drawImage(workshopImage, (int) volvoWorkshop.getxPos(), (int) volvoWorkshop.getyPos(), null);
    }
}