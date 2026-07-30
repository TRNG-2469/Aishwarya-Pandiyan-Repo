public class StringCalculator {

    public static int add(String numbers) {
        int[] values = parse(numbers);
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

    public static int multiply(String numbers) {
        int[] values = parse(numbers);
        int product = 1;
        for (int value : values) {
            product *= value;
        }
        return product;
    }

    public static int divide(String numbers) {
        int[] values = parse(numbers);
        if (values.length < 2) {
            throw new IllegalArgumentException("Division requires at least two numbers.");
        }
        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] == 0) {
                throw new IllegalArgumentException("Division by zero is mathematically undefined.");
            }
            result /= values[i];
        }
        return result;
    }

    private static int[] parse(String numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input arguments cannot be null.");
        }

        String trimmed = numbers.trim();
        if (trimmed.isEmpty()) {
            return new int[0];
        }

        // -1 limit preserves trailing empty tokens so "1,2," is caught, not silently dropped
        String[] tokens = trimmed.split(",", -1);
        int[] values = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();
            if (token.isEmpty()) {
                throw new IllegalArgumentException("Input contains an empty token (check for trailing or consecutive commas).");
            }
            try {
                values[i] = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Inputs must be valid integers. Parsing failed.");
            }
        }

        return values;
    }

    public static void main(String[] args) {
        runAdd("1,2,3");
        runAdd("");
        runAdd("1,2,");
        runMultiply("2,3,4");
        runDivide("100,5,2");
        runDivide("100,0");
        runAdd(null);
    }

    private static void runAdd(String numbers) {
        try {
            System.out.println("Add(" + numbers + ") = " + add(numbers));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runMultiply(String numbers) {
        try {
            System.out.println("Multiply(" + numbers + ") = " + multiply(numbers));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void runDivide(String numbers) {
        try {
            System.out.println("Divide(" + numbers + ") = " + divide(numbers));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}