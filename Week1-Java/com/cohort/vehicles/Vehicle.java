package com.cohort.vehicles;

public abstract class Vehicle {

    protected String model;
    protected double speed;

    public Vehicle(String model) {
        this.model = model;
        this.speed = 0.0;
    }

    public abstract void start();

    public void stop() {
        this.speed = 0.0;
        System.out.println("[" + model + "] Engine stopping... speed reset to 0.0");
    }
}