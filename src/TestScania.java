import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestScania {

    private Scania scania;

    @Before
    public void setup() {
        scania = new Scania();
    }

    @Test
    public void testNotGassingWhenAngledTruckBed() {
        double input = 0.5;
        scania.platform.raisePlatform();
        assertThrows(IllegalArgumentException.class, () -> scania.gas(input));
    }

    @Test
    public void testNotIncreasingAngleWhileDriving() {
        double angleBefore = scania.truckbedAngle;
        double input = 0.5;
        scania.gas(input);
        scania.platform.raisePlatform();
        double angleAfter = scania.truckbedAngle;
        assertEquals(angleBefore, angleAfter,0);
    }

    @Test
    public void testNotIncreasedAbove70() {
        scania.platform.raisePlatform();
        scania.platform.raisePlatform();
        scania.platform.raisePlatform();
        assertEquals(scania.truckbedAngle,70,0);
    }

    @Test
    public void testNotDecreasedBelow0() {
        scania.platform.lowerPlatform();
        assertEquals(scania.truckbedAngle, 0, 0);
    }

    @Test
    public void testGetAngle() {
        assertEquals(scania.truckbedAngle, 0,0);
    }
    @Test
    public void testDecrementSpeed() {
        double input = 0.5;
        scania.gas(input);
        double speedBefore = scania.currentSpeed;
        scania.brake(input);
        double speedAfter = scania.currentSpeed;
        assertNotEquals(speedBefore, speedAfter, 0);
    }
}
