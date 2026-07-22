package Tuesday;

public class MathUtils {
    public static long factorial(int n) {
        if(n <= 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i < n; i++) {
            result *= i;
        }
        return result;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Warning: array is null or empty.");
            return 0;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    public static double findMax(double[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Warning: array is null or empty.");
            return 0;
        }
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Is 7 prime? " + isPrime(7));
        System.out.println("Is 8 prime? " + isPrime(8));
        System.out.println("25.0 Celsius in Fahrenheit: " + celsiusToFahrenheit(25.0));

        int[] intArr = {3, 7, 2, 9, 4};
        System.out.println("Max of int array: " + findMax(intArr));

        double[] doubleArr = {3.5, 7.2, 2.1, 9.9,4.4};
        System.out.println("Max of double array: " + findMax(doubleArr));
    }
}