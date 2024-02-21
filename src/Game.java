import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private WorldFactory worldFactory;
    private ArrayList<Vehicle> vehicles;
    private CarWorkshop volvoWorkshop;
    private CarView frame;
    private EventHandler eventHandler;
    private CarController cc;
    private ButtonController buttonController;
    public Game() {
        this.worldFactory = new WorldFactory();
        this.vehicles.add(this.worldFactory.createVolvo(100,100));
        this.vehicles.add(this.worldFactory.createSaab(200,200));
        this.frame = new CarView("frame 1",cc);
        this.volvoWorkshop = this.worldFactory.createVolvoWorkshop("Workshop", 3, 250, 250);
        this.eventHandler = new EventHandler(this.vehicles, this.volvoWorkshop, frame);
        this.buttonController = new ButtonController("frame 1", vehicles);
    }
}
