import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestSaab {

    private Saab95 saab;

    @Before
    public void setup() {
        saab = new Saab95();
    }

    @Test
    public void testStartEngine() {
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testMinSpeedisZero() {
        saab.brake(1);
        assertEquals(0, saab.getCurrentSpeed(), 0);

    }

    @Test
    public void testMaxSpeedisEnginePower() {

        double saab_current_speed;
        double saab_prev_speed;
        saab_current_speed = saab.getCurrentSpeed();
        saab_prev_speed = -1;

        while (saab_current_speed > saab_prev_speed) {
            saab_prev_speed = saab.getCurrentSpeed();
            saab.gas(1);
            saab_current_speed = saab.getCurrentSpeed();

        }
        assertEquals(saab.getEnginePower(), saab.getCurrentSpeed(), 0);

    }

    @Test
    public void testGasInputInRange0to1() {
        double input = 2;
        assertThrows(IllegalArgumentException.class, () -> saab.gas(input));
    }

    @Test
    public void testBrakeInputInRange0to1() {
        double input = 2;
        assertThrows(IllegalArgumentException.class, () -> saab.brake(input));
    }

    @Test
    public void testGasNotDecreases() {

        for (int i = 0; i < 15; i++) ;
        {
            double saab_initial_speed = saab.getCurrentSpeed();
            saab.gas(1);
            assertTrue(saab_initial_speed < saab.getCurrentSpeed() || saab_initial_speed == saab.getCurrentSpeed());
        }
    }

    @Test
    public void testBrakeNotIncreases() {
        double saab_initial_speed = saab.getCurrentSpeed();
        saab.brake(1);
        assertTrue(saab_initial_speed > saab.getCurrentSpeed() || saab_initial_speed == saab.getCurrentSpeed());

    }

    @Test
    public void testTurnLeft() {
        saab.turnLeft();
        assertTrue(saab.getxDir() == -1 && saab.getyDir() == 0);
    }

    @Test
    public void testTurnRight() {
        saab.turnRight();
        assertTrue(saab.getxDir() == 1 && saab.getyDir() == 0);
    }

    @Test
    public void testMoving() {
        saab.gas(1);
        saab.move();
        assertTrue(saab.getxDir() == 0 && saab.getyDir() > 0);

    }

    @Test
    public void testSpeedfactorWithTurbo() {
        double shouldbe = saab.enginePower * 1.3 * 0.01;
        saab.setTurboOn();
        double itis = saab.speedFactor();
        assertTrue(itis == shouldbe);
    }

    @Test
    public void testSpeedfactorNoTurbo() {
        double expected = saab.enginePower * 1 * 0.01;
        saab.setTurboOff();
        double real = saab.speedFactor();
        assertTrue(real == expected);
    }
}