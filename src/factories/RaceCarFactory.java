package factories;

import enums.CarName;
import enums.Distance;
import enums.MaxSpeed;
import models.RaceCarRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RaceCarFactory {
    public static RaceCarRunnable getRaceCar(CountDownLatch finishLatch){
        MaxSpeed randomMaxSpeed = getRandomMaxSpeed();
        String randomName = getRandomCarName();
        Distance randomDistance = getRandomDistance();

        return new RaceCarRunnable(randomName, randomMaxSpeed.getSpeed(), 0, randomDistance.getDistance(),finishLatch);
    }

    public static List<RaceCarRunnable> getRaceCars(int count, CountDownLatch finishLatch){
        Distance randomDistance = getRandomDistance();
        List<RaceCarRunnable> cars = new ArrayList<RaceCarRunnable>();

        for (int i = 0; i < count; i++) {
            cars.add(new RaceCarRunnable(getRandomCarName(), getRandomMaxSpeed().getSpeed(), 0, randomDistance.getDistance(),finishLatch));
        }

        return cars;
    }

    private static MaxSpeed getRandomMaxSpeed() {
        MaxSpeed[] values = MaxSpeed.values();
        int randomIndex = (int) (Math.random() * values.length);
        return values[randomIndex];
    }

    private static String getRandomCarName() {
        CarName[] values = CarName.values();
        int randomIndex = (int) (Math.random() * values.length);
        return values[randomIndex].toString();
    }

    private static Distance getRandomDistance() {
        Distance[] values = Distance.values();
        int randomIndex = (int) (Math.random() * values.length);
        return values[randomIndex];
    }
}
