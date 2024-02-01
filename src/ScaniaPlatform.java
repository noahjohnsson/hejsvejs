public class ScaniaPlatform extends Platform{
    private Scania scania;

    public ScaniaPlatform(Scania scania) {
        this.scania = scania;
    }

    @Override
    public void raisePlatform() {
        if (scania.getCurrentSpeed() == 0)
        {
            scania.truckbedAngle = Math.min(scania.truckbedAngle + 30, 70);
        }
    }

    @Override
    public void lowerPlatform() {
        if (scania.getCurrentSpeed()== 0)
        {
            scania.truckbedAngle = Math.max(scania.truckbedAngle - 30, 0);
        }
    }

    @Override
    public boolean drivable() {
        return scania.truckbedAngle == 0;
    }
}
