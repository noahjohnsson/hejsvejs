import java.awt.image.BufferedImage;

public class WorldFactory {
    private ImageLoader imageLoader;
    public WorldFactory() {
        this.imageLoader = new ImageLoader();}

    public Car createVolvo(double xPos, double yPos) {
        BufferedImage image = imageLoader.getImage("Volvo240");
        return new Volvo240(xPos, yPos, image);
    }

    public Car createSaab(double xPos, double yPos) {
        BufferedImage image = ImageLoader.getImage("Saab95");
        return new Saab95(xPos, yPos, image);
    }

    public Scania createScania(double xPos, double yPos) {
        BufferedImage image = imageLoader.getImage("Scania");
        return new Scania(xPos, yPos, image);
    }

    public CarWorkshop createVolvoWorkshop(String name, int maxSize, double xPos, double yPos) {
        BufferedImage image = imageLoader.getImage("VolvoWorkshop");
        return new CarWorkshop<Volvo240>(name, maxSize, xPos, yPos, image);
    }

    public CarWorkshop createSaabWorkshop(String name, int maxSize, double xPos, double yPos) {
        BufferedImage image = imageLoader.getImage("SaabWorkshop");
        return new CarWorkshop<Saab95>(name, maxSize, xPos, yPos, image);
    }

    public CarWorkshop createAllCarsWorksop(String name, int maxSize, double xPos, double yPos) {
        BufferedImage image = imageLoader.getImage("Workshop");
        return new CarWorkshop<Car>(name, maxSize, xPos, yPos, image);
    }
}
