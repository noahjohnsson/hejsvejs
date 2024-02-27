public class Application {
    public static void main(String[] args) {
        Model vehicleModel = new Model(); // Instantiate the Model
        View vehicleView = new View("CarSim 1.0", vehicleModel); // Instantiate the View
        Controller vehicleController = new Controller(vehicleModel, vehicleView); // Instantiate the Controller
        vehicleModel.addObserver(vehicleView);
    }
}
