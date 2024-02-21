public class CarTransportPlatform extends Platform{

    public VehicleTransportPlatform() {
        rampLoadable = false;
    }

    @Override
    public void raisePlatform() {rampLoadable = false;}

    @Override
    public void lowerPlatform() {rampLoadable = true;}

}
