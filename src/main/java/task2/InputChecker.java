package task2;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * TASK 2.
 *
 * Write a helper method for asking user input (an integer) from a Scanner.
 * It should ask again if the entered number does not satisfy the given conditions (checked with a lambda).
 * It should ask again if the entered text is not a number at all.
 *
 * An example run of the given program should go as follows:
 *
 * Choose a number between 1 and 100: 0
 * Choose a number between 1 and 100: 1234
 * Choose a number between 1 and 100: -10
 * Choose a number between 1 and 100: text
 * Choose a number between 1 and 100: 12
 * Wrong answer!
 */
public class InputChecker {
    private final PrintStream out;
    private final Scanner scanner;

    public InputChecker(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
    }

    public int askInt(String prompt, Predicate<Integer> badPredicate) {
        // TODO: Implement this method, use given field out for output and scanner for input
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                int i = Integer.parseInt(line); // better than scanner.nextInt() because that doesn't nicely handle input like "-1a"
                if (!badPredicate.test(i))
                    return i;
            }
            catch (NumberFormatException ignored) {

            }
        }
    }

    public static void main(String[] args) {
        InputChecker input = new InputChecker(System.out, new Scanner(System.in));

        int num = input.askInt("Choose a number between 1 and 100: ", i -> i < 1 || i > 100);

        System.out.println(num == 42 ? "Correct answer!" : "Wrong answer!");
    }
}
