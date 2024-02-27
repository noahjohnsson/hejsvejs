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
    Model model;
    ArrayList<Vehicle> vehicles;
    CarWorkshop<Volvo240> volvoWorkshop;


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, Model model) {
        this.vehicles = model.getVehicles();
        this.volvoWorkshop = model.getVolvoWorkshop();
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
//        this.loadImages();
//        this.addVehicleImages(model.getVehicles());
//        this.addWorkshopImage(model.getVolvoWorkshop());
    }

//    public void loadImages() {
//        try {
//            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"));
//            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg"));
//            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg"));
//            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

//    public void addVehicleImages(ArrayList<Vehicle> vehicles){
//        for (Vehicle vehicle : vehicles) {
//            if (vehicle.getModelName().equals("Volvo240")) {
//                images.put(vehicle, volvoImage);
//            } else if (vehicle.getModelName().equals("Saab95")) {
//                images.put(vehicle, saabImage);
//            } else if (vehicle.getModelName().equals("Scania")) {
//                images.put(vehicle, scaniaImage);
//            }
//
//        }
//    }
//
//    public void addWorkshopImage(CarWorkshop<Volvo240> workshop){
//            workshopImage.put(workshop, volvoWorkshopImage);
//    }




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
    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        for (Vehicle vehicle : vehicles) {
//            BufferedImage image = null;
//            String modelName = vehicle.getModelName();
//
//            if (modelName.equals("Volvo240")) {
//                image = volvoImage;
//            } else if (modelName.equals("Saab95")) {
//                image = saabImage;
//            } else if (modelName.equals("Scania")) {
//                image = scaniaImage;
//        }   if (image != null) {
//                g.drawImage(image, (int) vehicle.getxPos(), (int) vehicle.getyPos(), null);
//            }
//        }
//
//        if (volvoWorkshopImage != null) {
//            g.drawImage(volvoWorkshopImage, (int) volvoWorkshop.getxPos(), (int) volvoWorkshop.getyPos(), null);
//        }
//    }
}

        // Draw vehicles
//        for (Map.Entry<Vehicle, BufferedImage> entry : images.entrySet()) {
//            Vehicle vehicle = entry.getKey();
//            BufferedImage image = entry.getValue();
//            g.drawImage(image, (int) vehicle.getxPos(), (int) vehicle.getyPos(), null);
//        }
//
//        // Draw Workshop
//        for (Map.Entry<CarWorkshop<Volvo240>, BufferedImage> entry : workshopImage.entrySet()) {
//            CarWorkshop<Volvo240> workshop = entry.getKey();
//            BufferedImage image = entry.getValue();
//            g.drawImage(image, (int) workshop.getxPos(), (int) workshop.getyPos(), null);
//        }
//    }


