import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Model vehicleModel = new Model();
        View vehicleView = new View("CarSim 1.0", vehicleModel);
        Controller vehicleController = new Controller(vehicleModel, vehicleView);
        vehicleController.getTimer().start();

    }

}
