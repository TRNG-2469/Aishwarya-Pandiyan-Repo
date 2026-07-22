package Monday;

import java.util.Random;

public class ArrayAnalyzer {
    public static void main(String[] args) {
        int[] values = new int[10];
        Random rand = new Random();

        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt(100) + 1;
        }

        int sum = 0;
        int min = values[0];
        int max = values[0];

        for (int i = 0; i < values.length; i++) {
            sum += values[i];

            if (values[i] < min) {
                min = values[i];
            }

            if (values[i] > max) {
                max = values[i];
            }
        }

        double average = (double) sum / values.length;

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        System.out.println("Array: " + sb.toString());
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
    }
}
