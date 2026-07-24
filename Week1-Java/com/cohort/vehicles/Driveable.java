package com.cohort.vehicles;

public interface Driveable {

    void accelerate();

    default void soundHorn() {
        System.out.println("[HORN] Beep beep!");
    }
}
