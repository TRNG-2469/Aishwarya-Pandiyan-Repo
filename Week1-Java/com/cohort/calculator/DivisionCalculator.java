package com.cohort.calculator;

import com.cohort.exceptions.InvalidInputException;

public class DivisionCalculator {

    public static int divide(String numeratorStr, String denominatorStr) throws InvalidInputException {
        if (numeratorStr == null || numeratorStr.isEmpty() ||
                denominatorStr == null || denominatorStr.isEmpty()) {
            throw new InvalidInputException("Input arguments cannot be null or empty.");
        }

        int numerator;
        int denominator;
        try {
            numerator = Integer.parseInt(numeratorStr);
            denominator = Integer.parseInt(denominatorStr);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Inputs must be valid integers. Parsing failed.");
        }

        try {
            return numerator / denominator;
        } catch (ArithmeticException e) {
            throw new InvalidInputException("Division by zero is mathematically undefined.");
        }
    }

    public static void main(String[] args) {
        runTest("100", "5");
        runTest("100", "0");
        runTest("abc", "5");
        runTest(null, "5");
    }

    private static void runTest(String numerator, String denominator) {
        try {
            int result = divide(numerator, denominator);
            System.out.println("Result: " + result);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("[CALCULATOR] Execution cycle complete.");
        }
    }
}