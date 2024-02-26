import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;


// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    BufferedImage volvoImage, saabImage, scaniaImage, volvoWorkshopImage;
    Map<Vehicle, BufferedImage> images = new HashMap<>();
    Map<CarWorkshop<Volvo240>, BufferedImage> workshopImage = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, Model model) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.loadImages();
        this.addVehicleImages(model.getVehicles());
        this.addWorkshopImage(model.getVolvoWorkshop());
    }

    public void loadImages() {
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addVehicleImages(ArrayList<Vehicle> vehicles){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModelName().equals("Volvo240")) {
                images.put(vehicle, volvoImage);
            } else if (vehicle.getModelName().equals("Saab95")) {
                images.put(vehicle, saabImage);
            } else if (vehicle.getModelName().equals("Scania")) {
                images.put(vehicle, scaniaImage);
            }

        }
    }
    public void addWorkshopImage(CarWorkshop<Volvo240> workshop){
            workshopImage.put(workshop, volvoWorkshopImage);
    }


    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw vehicles
        for (Map.Entry<Vehicle, BufferedImage> entry : images.entrySet()) {
            Vehicle vehicle = entry.getKey();
            BufferedImage image = entry.getValue();
            g.drawImage(image, (int) vehicle.getxPos(), (int) vehicle.getyPos(), null);
        }

        // Draw Workshop
        for (Map.Entry<CarWorkshop<Volvo240>, BufferedImage> entry : workshopImage.entrySet()) {
            CarWorkshop<Volvo240> workshop = entry.getKey();
            BufferedImage image = entry.getValue();
            g.drawImage(image, (int) workshop.getxPos(), (int) workshop.getyPos(), null);
        }
    }
}
