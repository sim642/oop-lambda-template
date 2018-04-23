package task1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * TASK 1.
 *
 * This is a shorter and simpler version of the "2. Failipuu läbimine" interfaces homework task:
 * https://courses.cs.ut.ee/2018/OOP/spring/Main/Practice5Harjutused.
 *
 * Now we try to use lambdas instead of separate classes to make everything really simple.
 */
public class ReverseSorter {
    public static void main(String[] args) throws IOException {
        Path rootPath = Paths.get("src"); // hardcoded root path with existing files to avoid messing with args

        List<String> filenames = new ArrayList<>();

        // TODO: What is this SimpleFileVisitor?
        // A FileVisitor implementation that doesn't require implementing all the methods.
        // TODO: Can this anonymous inner class be replaced with a lambda? Why? Why not?
        // No, because SimpleFileVisitor is a class, not an interface. Furthermore, there's even no abstract methods.
        Files.walkFileTree(rootPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                filenames.add(file.toString());
                return FileVisitResult.CONTINUE;
            }
        });

        // TODO: Sort the filenames in REVERSE order using a lambda
        filenames.sort((lhs, rhs) -> rhs.compareTo(lhs));

        // TODO: Print the filenames on separate lines using a lambda
        filenames.forEach(filename -> System.out.println(filename));

        // TODO: Can we use method references anywhere? Why? Why not?
        // IntelliJ suggests instead:
        filenames.sort(Comparator.reverseOrder()); // not really a method reference: calls method with (), no ::
        filenames.forEach(System.out::println); // method reference: println takes one argument
    }
}
