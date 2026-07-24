package com.cohort;

public class ParserUtility {

    public static int[] parseNumbers(String input) {
        if (input == null || input.strip().isEmpty()) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        int validCount = 0;

        for (int i = 0; i < parts.length; i++) {
            try {
                String cleanPart = parts[i].strip();
                nums[validCount] = Integer.parseInt(cleanPart);
                validCount++;
            } catch (NumberFormatException e) {
                System.out.println("Warning: Skipping invalid number entry: '" + parts[i] + "'");
            }
        }

        if (validCount < nums.length) {
            int[] cleanedNums = new int[validCount];
            System.arraycopy(nums, 0, cleanedNums, 0, validCount);
            return cleanedNums;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] result1 = parseNumbers("10, 20 , 30, 40");
        System.out.println("Valid parse: " + java.util.Arrays.toString(result1));

        int[] result2 = parseNumbers("10, invalid_text, 30");
        System.out.println("Partial parse: " + java.util.Arrays.toString(result2));

        int[] result3 = parseNumbers(null);
        System.out.println("Null check parse: " + java.util.Arrays.toString(result3));
    }
}