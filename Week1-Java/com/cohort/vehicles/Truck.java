package com.cohort.vehicles;

public class Truck extends Vehicle implements Driveable {

    public Truck(String model) {
        super(model);
    }

    @Override
    public void start() {
        System.out.println("[" + model + "] Truck diesel engine rumbling to life.");
    }

    @Override
    public void accelerate() {
        speed += 10.0;
        System.out.println("[" + model + "] Accelerating slowly... current speed: " + speed + " mph");
    }

    @Override
    public void soundHorn() {
        System.out.println("[TRUCK HORN] HONK HONK!");
    }
}
