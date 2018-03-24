package task7;

import java.io.IOException;
import java.nio.file.*;

/**
 * TASK 7.
 *
 * This is task 1 taken to extremes.
 *
 * Now we try to do the same thing but with a single stream.
 */
public class StreamReverseSorter {
    public static void main(String[] args) throws IOException {
        Path rootPath = Paths.get("src"); // hardcoded root path with existing files to avoid messing with args

        // TODO HARD: Create a stream of all files in rootPath, sort them in reverse and print them
        // HINT: Files.walk
    }
}
