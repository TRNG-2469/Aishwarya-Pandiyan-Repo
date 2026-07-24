package com.cohort;

public class RosterApp {
    public static void main(String[] args) {
        String[] studentNames = new String[5];
        studentNames[0] = "Alice";
        studentNames[1] = "Bob";

        for (int i = 0; i < studentNames.length; i++) {
            if (studentNames[i] != null && studentNames[i].startsWith("A")) {
                System.out.println("Student: " + studentNames[i]);
            }
        }
    }
}