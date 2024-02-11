import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;


public class TestVehicleTransport {
    private VehicleTransport vehicleTransport;
    private Volvo240 volvo1;
    private Volvo240 volvo2;
    private Saab95 saab1;
    private Saab95 saab2;

    @Before
    public void setUp() {
        vehicleTransport = new VehicleTransport(4);
        volvo1 = new Volvo240();
        volvo2 = new Volvo240();
        saab1 = new Saab95();
        saab2 = new Saab95();
    }
    @Test
    public void testNotGassingWhenTruckBedLowered() {
        double input = 0.5;
        vehicleTransport.platform.lowerPlatform();
        assertThrows(IllegalStateException.class, () -> vehicleTransport.gas(input));
    }

    @Test
    public void testLoadCars(){
        vehicleTransport.platform.lowerPlatform();
        vehicleTransport.loadObject(volvo1);
        vehicleTransport.loadObject(volvo2);
        vehicleTransport.loadObject(saab1);
        vehicleTransport.loadObject(saab2);

        Stack<Vehicle> loadedVehicles = vehicleTransport.loadedVehicles;

        assertEquals(4, loadedVehicles.size());

        assertSame(saab2, loadedVehicles.pop());
        assertSame(saab1, loadedVehicles.pop());
        assertSame(volvo2, loadedVehicles.pop());
        assertSame(volvo1, loadedVehicles.pop());
    }

    @Test
    public void unloadCarWhenTruckbedRaised(){
        vehicleTransport.platform.lowerPlatform();
        vehicleTransport.loadObject(volvo1);
        vehicleTransport.loadObject(saab2);
        vehicleTransport.platform.raisePlatform();
        assertThrows(IllegalStateException.class, ()-> vehicleTransport.unloadObject());


    }

    @Test
    public void movingCarsWithCarTransport() {
        double input = 0.5;
        vehicleTransport.platform.lowerPlatform();
        vehicleTransport.loadObject(volvo1);
        vehicleTransport.platform.raisePlatform();
        vehicleTransport.gas(input);
        vehicleTransport.move();
        assertEquals(vehicleTransport.getyPos(), volvo1.getyPos(), 0);
    }

}
