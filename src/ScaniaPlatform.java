public class ScaniaPlatform extends Platform {

    protected double truckbedAngle;

    public ScaniaPlatform() {
        this.truckbedAngle = 70;
    }


    public boolean fullyClosed() {return truckbedAngle == 70;}

    @Override
    public void raisePlatform() {
        truckbedAngle = Math.min(truckbedAngle + 30, 70);
        rampLoadable = false;
    }

    @Override
    public void lowerPlatform() {
        truckbedAngle = Math.max(truckbedAngle - 30, 0);
        if (truckbedAngle == 0) {
            rampLoadable = true;
        }
    }

    public double getTruckbedAngle(){return this.truckbedAngle;}

}
