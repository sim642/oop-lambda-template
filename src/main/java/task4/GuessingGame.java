package task4;

import task3.FeedbackInputChecker;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TASK 4.
 *
 * Use the FeedbackInputChecker to implement the number guessing game:
 * computer chooses a number 1..100,
 * the user starts to guess it and gets feedback like "Too small guess!" or "Too large guess!"
 * until guesses correctly.
 */
public class GuessingGame {
    public static void main(String[] args) {
        // TODO: Create the FeedbackInputChecker
        FeedbackInputChecker input = new FeedbackInputChecker(System.out, new Scanner(System.in));

        // TODO: Generate the number to guess
        int toGuess = ThreadLocalRandom.current().nextInt(1, 100 + 1); // ThreadLocalRandom has nextInt with lower bound
        // TODO: Abuse askInt to implement the game (needs to be called once, ignore return value)
        input.askInt("Guess the number between 1 and 100: ", i -> {
            if (i < toGuess)
                return "Too small guess!";
            else if (i > toGuess)
                return "Too large guess!";
            else
                return null;
        }); // can't directly be extracted to method reference because it depends on local variable toGuess

        // TODO: Congratulate the user
        System.out.println("Congratulations, you guessed it!");
    }
}
