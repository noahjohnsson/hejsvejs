public abstract class Platform {

    private double size;
    protected boolean rampLoadable;
    public abstract void raisePlatform();
    public abstract void lowerPlatform();
    public boolean isRampLoadable() {return rampLoadable;};
    public double getSize() {return size;}
    public void setSize(double size) {
        this.size = size;
    }


}