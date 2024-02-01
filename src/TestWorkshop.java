import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestWorkshop {

    private Saab95 saab;
    private Volvo240 volvo;
    private CarWorkshop volvoCarWorkshop;
    private CarWorkshop allCarWorkshop;

    @Before
    public void setup() {
        saab = new Saab95();
        volvo = new Volvo240();
        volvoCarWorkshop = new CarWorkshop<Volvo240>("Volvo Workshop", 6);
        allCarWorkshop = new CarWorkshop<>("Volvo Workshop", 1);
    }

    @Test
    public void testSetSize() {
        allCarWorkshop.setMaxSize(7);
        assertEquals( 7,allCarWorkshop.getMaxSize(), 0);
    }

    @Test
    public void testGetSize() {
        assertEquals(allCarWorkshop.maxSize, allCarWorkshop.getMaxSize(), 1);
    }

    @Test
    public void leavingAcceptedCar() {
        volvoCarWorkshop.loadObject(volvo);
        assertTrue(volvoCarWorkshop.carsInWorkshop.contains(volvo));
    }

    @Test
    public void leavingDisallowedCar() {
        volvoCarWorkshop.loadObject(saab);
        assertTrue(volvoCarWorkshop.carsInWorkshop.contains(saab));
    }

    @Test
    public void gettingServicedCar() {
        Object car;
        volvoCarWorkshop.loadObject(volvo);
        assertTrue(volvoCarWorkshop.carsInWorkshop.contains(volvo));
        car = volvoCarWorkshop.unloadObject();
        assertEquals(car, volvo);
    }
}