import java.awt.*;

public abstract class Vehicle implements Movable {
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    private Color color;
    public String modelName;
    private double xPos;
    private double yPos;
    private double xDir;
    private double yDir;
    private Size size;
    public enum Size { SMALL, MEDIUM, LARGE }

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName, Size size) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.size = size;
        this.xPos = 0;
        this.yPos = 0;
        this.xDir = 0;
        this.yDir = 1;
        stopEngine();
    }

    protected int getNrDoors() {
        return nrDoors;
    }

    protected double getEnginePower() {
        return enginePower;
    }

    protected double getCurrentSpeed() {
        return currentSpeed;
    }

    protected double getxPos() { return xPos; }
    protected double getyPos() { return yPos; }
    protected double getxDir() { return xDir; }
    protected double getyDir() { return yDir; }

    protected void setxPos(double amount) { this.xPos = amount;}
    protected void setyPos(double amount) { this.yPos = amount;}
    protected void setxDir(double amount) { this.xDir = amount;}
    protected void setyDir(double amount) { this.yDir = amount;}

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color clr) {
        color = clr;
    }

    protected Size getsize() {return size; }

    protected void startEngine() {
        currentSpeed = 0.1;
    }

    protected void stopEngine() {
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Invalid gas amount");
        } else {
            incrementSpeed(amount);
        }
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Invalid brake amount");
        } else {
                decrementSpeed(amount);
            }
    }

    public void move() {
        this.xPos += currentSpeed * this.xDir;
        this.yPos += currentSpeed * this.yDir;

    }
    public void turnLeft() {
        System.out.println(this.xDir);
        System.out.println(this.yDir);

        if (this.xDir == 0 && this.yDir == 1) {
            this.xDir = -1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == -1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = -1;
            return;
        }
        if (this.xDir == 0 && this.yDir == -1) {
            this.xDir = 1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == 1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = 1;
        }
    }
    public void turnRight() {
        if (this.xDir == 0 && this.yDir == 1) {
            this.xDir = 1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == -1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = 1;
            return;
        }
        if (this.xDir == 0 && this.yDir == -1) {
            this.xDir = -1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == 1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = -1;
        }
    }
    protected void incrementSpeed(double amount){
        if (getCurrentSpeed() + speedFactor() * amount > currentSpeed)
            currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        if (currentSpeed > enginePower)
            currentSpeed = enginePower;
    }
    protected void decrementSpeed(double amount){
        if (getCurrentSpeed() - speedFactor() * amount < currentSpeed)
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}


    
