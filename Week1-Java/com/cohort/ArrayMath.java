package com.cohort;

public class ArrayMath {

    public static double findAverage(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Warning: Input array is null or empty. Returning 0.0");
            return 0.0;
        }

        long sum = 0;
        for (int value : arr) {
            sum += value;
        }

        return (double) sum / arr.length;
    }

    public static void main(String[] args) {
        int[] scores = {5, 6};
        System.out.println("Average: " + findAverage(scores));

        System.out.println("Empty check: " + findAverage(new int[0]));
        System.out.println("Null check: " + findAverage(null));
    }
}
