package task1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
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
        // TODO: Can this anonymous inner class be replaced with a lambda? Why? Why not?
        Files.walkFileTree(rootPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                filenames.add(file.toString());
                return FileVisitResult.CONTINUE;
            }
        });

        // TODO: Sort the filenames in REVERSE order using a lambda

        // TODO: Print the filenames on separate lines using a lambda

        // TODO: Can we use method references anywhere? Why? Why not?
    }
}
