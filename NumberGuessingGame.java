import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int totalRounds = 3;
        int maxAttempts = 10;
        int totalScore = 0;

        System.out.println("=== Welcome to the Number Guessing Game ===");
        System.out.println("Rules: Guess the number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts per round. Total rounds: " + totalRounds + "\n");

        for (int round = 1; round <= totalRounds; round++) {
            int targetNumber = random.nextInt(100) + 1;
            int attemptsUsed = 0;
            boolean roundWon = false;

            System.out.println("--- Round " + round + " Begins ---");

            while (attemptsUsed < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsUsed++;

                if (userGuess == targetNumber) {
                    int roundPoints = (maxAttempts - attemptsUsed + 1) * 10;
                    totalScore += roundPoints;
                    System.out.println("Correct! You guessed it in " + attemptsUsed + " attempts.");
                    System.out.println("Points earned this round: " + roundPoints);
                    roundWon = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("The target number is HIGHER.");
                } else {
                    System.out.println("The target number is LOWER.");
                }
                
                System.out.println("Attempts remaining: " + (maxAttempts - attemptsUsed));
            }

            if (!roundWon) {
                System.out.println("Round over! The correct number was: " + targetNumber);
            }
            System.out.println("Current Total Score: " + totalScore + "\n");
        }

        System.out.println("=== Game Over ===");
        System.out.println("Final Score: " + totalScore + " points.");
    }
}