package task5;

/**
 * TASK 5.
 *
 * Write a very generic static method for combining a list of elements with the given binary operation.
 * Example usages and outputs are given in the main method.
 */
public class Semigroup {

    // TODO: Find which built-in functional interface from java.util.function could
    // be used for the second argument (or if you can't find one, define it yourself)

    // TODO: Figure out the signature for combine method
    // TODO: Implement the combine method

    // TODO HARD: Reimplement the method using streams

    public static void main(String[] args) {
        // TODO: Uncomment these examples
        /*List<Integer> ints = List.of(1, 3, 10, -5);
        output("int sum",     combine(ints, (l, r) -> l + r)); // should be 9
        output("int product", combine(ints, (l, r) -> l * r)); // should be -150
        output("int min",     combine(ints, Math::min));       // should be -5
        output("int max",     combine(ints, Math::max));       // should be 10

        List<String> strings = List.of("foo", "bar", "baz");
        output("string concat", combine(strings, (l, r) -> l + r));        // should be "foobarbaz"
        output("string join",   combine(strings, (l, r) -> l + ", " + r)); // should be "foo, bar, baz"*/
    }

    private static <T> void output(String prompt, T obj) {
        System.out.print(prompt + ": ");
        System.out.println(obj);
    }
}
