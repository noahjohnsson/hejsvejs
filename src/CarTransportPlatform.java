public class CarTransportPlatform extends Platform{
    private CarTransport carTransport;

    public CarTransportPlatform(CarTransport carTransport) {
        this.carTransport = carTransport;
    }

    @Override
    public void raisePlatform() {
        if (carTransport.getCurrentSpeed() == 0) {carTransport.rampLoadable = true;}
    }

    @Override
    public void lowerPlatform() {
        if (carTransport.getCurrentSpeed()  == 0) {carTransport.rampLoadable = false;}

    }

    @Override
    public boolean drivable() {
        return carTransport.rampLoadable;
    }
}
