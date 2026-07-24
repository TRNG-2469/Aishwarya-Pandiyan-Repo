package com.cohort;

class Vehicle2 {}
class Car2 extends Vehicle2 {}
class Airplane2 extends Vehicle2 {}

public class AirportApp {
    public static void main(String[] args) {
        Vehicle2 myVehicle = new Airplane2();

        if (myVehicle instanceof Car2) {
            Car2 myCar = (Car2) myVehicle;
            System.out.println("Vehicle downcasted successfully.");
        } else {
            System.out.println("Cannot downcast: myVehicle is not a Car2.");
        }
    }
}