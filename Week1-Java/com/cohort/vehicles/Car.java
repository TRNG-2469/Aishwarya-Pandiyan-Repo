package com.cohort.vehicles;

public class Car extends Vehicle implements Driveable {

    public Car(String model) {
        super(model);
    }

    @Override
    public void start() {
        System.out.println("[" + model + "] Car engine started. Vroom!");
    }

    @Override
    public void accelerate() {
        speed += 20.0;
        System.out.println("[" + model + "] Accelerating... current speed: " + speed + " mph");
    }
}