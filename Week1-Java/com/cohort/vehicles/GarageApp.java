package com.cohort.vehicles;

public class GarageApp {
    public static void main(String[] args) {

        Car myCar = new Car("Honda Civic");
        Truck myTruck = new Truck("Ford F-150");

        System.out.println("--- Car Test ---");
        myCar.start();
        myCar.accelerate();
        myCar.accelerate();
        myCar.soundHorn();
        myCar.stop();

        System.out.println("\n--- Truck Test ---");
        myTruck.start();
        myTruck.accelerate();
        myTruck.accelerate();
        myTruck.soundHorn();
        myTruck.stop();
    }
}