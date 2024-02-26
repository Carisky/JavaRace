import factories.RaceCarFactory;
import models.RaceCarRunnable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class Race {
    public static AtomicLong startRaceTime = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        int numberOfCars = 5;
        CountDownLatch finishLatch = new CountDownLatch(numberOfCars);

        List<RaceCarRunnable> cars = RaceCarFactory.getRaceCars(numberOfCars, finishLatch);
        List<Thread> threads = new ArrayList<>();

        for (RaceCarRunnable car : cars) {
            car.show();
        }

        startRaceTime.set(System.currentTimeMillis());

        for (RaceCarRunnable car : cars) {
            Thread thread = new Thread(car);
            threads.add(thread);
        }

        startRace(threads);

        try {
            finishLatch.await();

            System.out.println("Результаты гонки:");
            for (RaceCarRunnable car : cars) {
                long finishTime = car.getFinishTime();
                System.out.println(car.getName() + " FINISHED ! Время: " + convertToTime(finishTime));
            }

            RaceCarRunnable winner = findWinner(cars);
            System.out.println("Победитель: " + winner.getName() + " с временем " + convertToTime(winner.getFinishTime()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startRace(List<Thread> cars) throws InterruptedException {
        for (int i = 3; i > 0; i--) {
            System.out.println(i + "...");
            Thread.sleep(500);
        }

        System.out.println("GO!!!");

        for (Thread thread : cars) {
            thread.start();
        }
    }

    public static String convertToTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
        Date resultDate = new Date(time);
        return sdf.format(resultDate);
    }

    public static RaceCarRunnable findWinner(List<RaceCarRunnable> cars) {
        RaceCarRunnable winner = cars.getFirst();
        for (RaceCarRunnable car : cars) {
            if (car.getFinishTime() < winner.getFinishTime()) {
                winner = car;
            }
        }
        return winner;
    }
}
