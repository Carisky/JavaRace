package models;

import enums.ConsoleColor;

import java.util.concurrent.CountDownLatch;

public class RaceCarRunnable extends Car implements Runnable {

    public RaceCarRunnable(String name, int maxSpeed, int passed, int distance,CountDownLatch finishLatch) {
        super(name, maxSpeed);
        this.passed = passed;
        this.distance = distance;
        this.finishLatch = finishLatch;
    }

    private int passed = 0;
    private int distance = 0;
    private boolean isFinish = false;

    private long finishTime;

    private final CountDownLatch finishLatch;

    private final String color = ConsoleColor.getRandomColor().toString();

    @Override
    public String toString() {
        return super.toString() + "passed = " + passed +
                ", distance = " + distance +
                ", isFinish = " + isFinish ;
    }

    public void show(){
        System.out.println(this);
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                setRandomSpeed();

                passed += getCurrentSpeed();

                System.out.println(color + getName() + " " + "progress " + passed + "/" + distance + ConsoleColor.RESET);

                if (passed >= distance) {
                    isFinish = true;
                    finishTime = System.currentTimeMillis();
                    finishLatch.countDown();
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public long getFinishTime() {
        return finishTime;
    }
}
