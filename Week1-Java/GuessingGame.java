import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        int targetNumber = (int) (Math.random() * 50) + 1;

        int maxAttempts = 5;
        int attemptCount = 0;
        boolean hasGuessedCorrectly = false;

        Scanner scanner = new Scanner(System.in);

        while (attemptCount < maxAttempts && !hasGuessedCorrectly) {
            attemptCount++;

            System.out.print("Attempt [" + attemptCount + "/" + maxAttempts + "] - Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess < 1 || guess > 50) {
                System.out.println("Please enter a number between 1 and 50.");
                attemptCount--;
                continue;
            }

            if (guess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Congratulations! you guessed the secret number! ");
                hasGuessedCorrectly = true;
                break;
            }
        }

        scanner.close();

        if (!hasGuessedCorrectly) {
            System.out.println("Game over! You've run out of attempts. The secret number was " + targetNumber + ".");
    }
    }
}
