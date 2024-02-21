public class WorldFactory {
    public Car createVolvo(double xPos, double yPos) {
        return new Volvo240(10, 10);
    }

    public Car createSaab(double xPos, double yPos) {
        return new Saab95(xPos, yPos);
    }

    public Scania createScania(double xPos, double yPos) {
        return new Scania(xPos, yPos);
    }

    public CarWorkshop createVolvoWorkshop(String name, int maxSize, double xPos, double yPos) {
        return new CarWorkshop<Volvo240>(name, maxSize, xPos, yPos);
    }

    public CarWorkshop createSaabWorkshop(String name, int maxSize, double xPos, double yPos) {
        return new CarWorkshop<Saab95>(name, maxSize, xPos, yPos);
    }

    public CarWorkshop createAllCarsWorksop(String name, int maxSize, double xPos, double yPos) {
        return new CarWorkshop<Car>(name, maxSize, xPos, yPos);
    }
}
