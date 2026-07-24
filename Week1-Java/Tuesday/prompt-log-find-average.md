# Prompt Log Entry: findAverage

## 1. Context
Building a utility method to calculate the average of an int array, returned as a double for decimal accuracy.

## 2. Prompt
"Write a static Java method named findAverage that takes an int[] array and returns the average as a double."

## 3. Output
public static double findAverage(int[] arr) {
int sum = 0;
for (int i = 0; i < arr.length; i++) {
sum += arr[i];
}
return sum / arr.length;
}

## 4. Evaluation
- Compiles successfully, no syntax errors.
- Precision bug: sum / arr.length does integer division before casting, so decimals are lost. Confirmed by running it, got 5.0 instead of 5.5 for {5, 6}.
- Null safety failure: passing null crashes with NullPointerException on arr.length.
- Division by zero risk: passing an empty array crashes with ArithmeticException.

## 5. Revised Prompt
"The method has three issues: 1) It performs integer division which loses decimal precision. Cast the sum to double first. 2) It crashes with a NullPointerException if the array is null. 3) It crashes with an ArithmeticException if the array is empty. Please add guard checks to return 0.0 if the array is null or empty, and fix the decimal division."

## Final Verified Output
Average: 5.5
Warning: Input array is null or empty. Returning 0.0
Empty check: 0.0
Warning: Input array is null or empty. Returning 0.0
Null check: 0.0