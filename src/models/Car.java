package models;

import java.util.Random;

public class Car {
    private String name = "";
    private int maxSpeed = 0;
    private int currentSpeed = 0;
    public Car(String name, int maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public Car(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setRandomSpeed(){
        Random random = new Random();
        this.currentSpeed = random.nextInt(maxSpeed/2,maxSpeed);
    }
    public int getCurrentSpeed(){
        return currentSpeed;
    }

    @Override
    public String toString() {
        return "name = '" + name + '\'' +
                ", maxSpeed = " + maxSpeed +
                ", currentSpeed = " + currentSpeed +" ";
    }
}
