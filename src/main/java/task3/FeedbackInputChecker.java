package task3;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * TASK 3.
 *
 * This task is an extension of task 2. Now we want to show the user error messages if the input is bad but still continue asking until satisfied.
 *
 * An example run of the given program should go as follows:
 *
 * Choose a number between 1 and 100: 0
 * Too small!
 * Choose a number between 1 and 100: 1234
 * Too large!
 * Choose a number between 1 and 100: -10
 * Too small!
 * Choose a number between 1 and 100: text
 * Not a number!
 * Choose a number between 1 and 100: 12
 * Wrong answer!
 */
public class FeedbackInputChecker {
    private final PrintStream out;
    private final Scanner scanner;

    public FeedbackInputChecker(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
    }

    public int askInt(String prompt, InputFeedback<Integer> inputFeedback) {
        // TODO: Implement this function, use given field out for output and scanner for input
        throw new UnsupportedOperationException("Not implemented");
    }

    public static void main(String[] args) {
        FeedbackInputChecker input = new FeedbackInputChecker(System.out, new Scanner(System.in));

        // TODO: Refactor this outrageous multiline lambda
        int num = input.askInt("Choose a number between 1 and 100: ", i -> {
            if (i < 1)
                return "Too small!";
            else if (i > 100)
                return "Too large!";
            else
                return null;
        });

        System.out.println(num == 42 ? "Correct answer!" : "Wrong answer!");
    }
}
