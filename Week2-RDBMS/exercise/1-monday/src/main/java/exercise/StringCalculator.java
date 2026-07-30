package exercise;

public class StringCalculator {
    public int add(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String[] tokens = input.split(",");
        int sum = 0;
        for (String token : tokens) {
            sum += Integer.parseInt(token.trim());
        }
        return sum;
    }
    public int multiply(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String[] tokens = input.split(",");
        int product = 1;
        for (String token : tokens) {
            product *= Integer.parseInt(token.trim());
        }
        return product;
    }

    public int divide(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String[] tokens = input.split(",");
        int result = Integer.parseInt(tokens[0].trim());
        for (int i = 1; i < tokens.length; i++) {
            int divisor = Integer.parseInt(tokens[i].trim());
            if (divisor == 0) {
                throw new IllegalArgumentException("Division by zero is undefined.");
            }
            result /= divisor;
        }
        return result;
    }
}