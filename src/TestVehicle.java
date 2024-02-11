import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;


public class TestVehicle {

    private Volvo240 volvo;
    private Saab95 saab;
    @Before
    public void setup () {
        volvo = new Volvo240();
        saab = new Saab95();
    }

    @Test
    public void testxPos() {
        assertEquals(0, volvo.getxPos(), 0);
    }
    @Test
    public void testyPos() {
        assertEquals(0, volvo.getyPos(), 0);
    }
    @Test
    public void testxDir() {
        assertEquals(0, volvo.getxDir(), 0);
    }
    @Test
    public void testyDir() {
        assertEquals(1, volvo.getyDir(), 0);
    }
    @Test
    public void testGetColor() {
        assertEquals(Color.red, saab.getColor());
    }
    @Test
    public void testSetColor() {
        saab.setColor(Color.yellow);
        assertEquals(Color.yellow, saab.getColor());
    }
    @Test
    public void testGetNrdoors() {
        assertEquals(2, saab.getNrDoors());
    }
}