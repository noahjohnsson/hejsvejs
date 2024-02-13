public class ScaniaPlatform extends Platform {
    protected double truckBedAngle;

    public ScaniaPlatform() {
        this.truckBedAngle = 70;
    }

    public boolean fullyClosed() {return truckBedAngle == 70;}

    @Override
    public void raisePlatform() {
        truckBedAngle = Math.min(truckBedAngle + 30, 70);
        rampLoadable = false;
    }

    @Override
    public void lowerPlatform() {
        truckBedAngle = Math.max(truckBedAngle - 30, 0);
        if (truckBedAngle == 0) {
            rampLoadable = true;
        }
    }

    public double getTruckBedAngle(){return this.truckBedAngle;}
}
