import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestVolvo {
    private Volvo240 volvo;
    @Before
    public void setup () {
        volvo = new Volvo240();
    }

    @Test
    public void testStartEngine() {
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testMinSpeedisZero() {
        volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed(), 0);

    }

    @Test
    public void testMaxSpeedisEnginePower(){
        double volvo_current_speed;
        double volvo_prev_speed;
        volvo_current_speed = volvo.getCurrentSpeed();
        volvo_prev_speed = -1;

        while (volvo_current_speed > volvo_prev_speed){
            volvo_prev_speed = volvo.getCurrentSpeed();
            volvo.gas(1);
            volvo_current_speed = volvo.getCurrentSpeed();
        }
        assertEquals(volvo.getEnginePower(), volvo.getCurrentSpeed(), 0);

    }

    @Test
    public void testGasInputInRange0to1() {
        double input = 2;
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(input));
    }

    @Test
    public void testBrakeInputInRange0to1() {
        double input = 2;
        assertThrows(IllegalArgumentException.class, () -> volvo.brake(input));
    }

    @Test
    public void testGasNotDecreases() {

        for (int i = 0; i < 15; i++);
        {
            double volvo_initial_speed = volvo.getCurrentSpeed();
            volvo.gas(1);
            assertTrue(volvo_initial_speed < volvo.getCurrentSpeed() || volvo_initial_speed == volvo.getCurrentSpeed());
        }
    }

    @Test
    public void testBrakeNotIncreases() {
        double volvo_initial_speed = volvo.getCurrentSpeed();
        volvo.brake(1);
        assertTrue(volvo_initial_speed > volvo.getCurrentSpeed() || volvo_initial_speed == volvo.getCurrentSpeed());

    }

    @Test
    public void testTurnLeft() {
        volvo.turnLeft();
        assertTrue(volvo.getxDir() == -1 && volvo.getyDir() == 0);
    }

    @Test
    public void testTurnRight() {
        volvo.turnRight();
        assertTrue(volvo.getxDir() == 1 && volvo.getyDir() == 0);
    }

    @Test
    public void testMoving() {
        volvo.gas(1);
        volvo.move();
        assertTrue(volvo.getxDir() == 0 && volvo.getyDir() > 0);

    }

    @Test
    public void testTrimfactor() {
        double shouldbe = volvo.enginePower * 1.25 * 0.01;
        double itis = volvo.speedFactor();
        assertTrue(itis == shouldbe);
    }
}