package task8;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TASK 8.
 *
 * Your task is to implement some infinite streams.
 * These are relatively obscure and impractical but great brain teasers.
 */
public class InfiniteStreams {
    /**
     * Returns an infinite stream of random integers (1…100).
     */
    private static Stream<Integer> randomInts() {
        // TODO: Implement
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(1, 100 + 1));
    }

    /**
     * Returns an infinite stream of all positive integers.
     */
    private static Stream<Integer> positiveInts() {
        // TODO: Implement
        return Stream.iterate(1, i -> i + 1);
    }

    /**
     * Returns an infinite stream of squares of all positive integers.
     */
    private static Stream<Integer> positiveIntSquares() {
        // TODO: Implement
        // HINT: reuse positiveInts
        return positiveInts()
                .map(i -> i * i);
    }

    /**
     * Returns an infinite stream of all prime numbers.
     */
    private static Stream<Integer> primes() {
        // TODO HARD: Implement
        // HINT: Create a helper method for checking whether a number is prime (also use a stream).
        return positiveInts()
                .filter(InfiniteStreams::isPrime);
    }

    private static boolean isPrime(int n) {
        return IntStream.range(2, n)
                .noneMatch(i -> n % i == 0);
    }

    /**
     * Returns an infinite stream of all Fibonacci numbers.
     */
    private static Stream<Integer> fibonacci() {
        // TODO HARD: Implement
        // HINT: Create a helper class for a pair of consecutive Fibonacci numbers.
        return Stream.iterate(new FibonacciPair(0, 1), FibonacciPair::next)
                .map(FibonacciPair::getCurrent);
    }

    private static class FibonacciPair {
        private final int current;
        private final int next;

        private FibonacciPair(int current, int next) {
            this.current = current;
            this.next = next;
        }

        private FibonacciPair next() {
            return new FibonacciPair(next, current + next);
        }

        private int getCurrent() {
            return current;
        }
    }

    public static void main(String[] args) {
        outputInfinite("Random integers", InfiniteStreams::randomInts);
        outputInfinite("Positive integers", InfiniteStreams::positiveInts);
        outputInfinite("Positive integer squares", InfiniteStreams::positiveIntSquares);
        outputInfinite("Primes", InfiniteStreams::primes);
        outputInfinite("Fibonacci", InfiniteStreams::fibonacci);

        // TODO: Refactor this class to use the more efficient IntStream instead of Stream<Integer>.
    }

    private static <T> void outputInfinite(String prompt, Supplier<Stream<T>> streamSupplier) {
        Stream<T> stream = streamSupplier.get(); // TODO: Explain why Supplier is used here.
        // Streams aren't reusable so above there are methods to return new copies of the same stream that can be used
        String elemStr = stream
                .limit(20)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        System.out.println(prompt + ":");
        System.out.println(elemStr + ", ...");
        System.out.println();
    }
}
