package task5;

import java.util.List;

/**
 * TASK 5.
 *
 * Your task is to implement some methods that work on a list of books.
 * Preferably streams should be used with lambdas and method references.
 */
public class BookStreams {
    /**
     * Returns a list of book titles.
     */
    private static List<String> bookTitles(List<Book> books) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Returns books with at least 500 pages.
     */
    private static List<Book> thickBooks(List<Book> books) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Returns books that have the exact author.
     */
    private static List<Book> booksByFullName(List<Book> books, String author) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Returns books that have the partially given author.
     * Be case insensitive.
     */
    private static List<Book> booksByPartialName(List<Book> books, String partialAuthor) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Returns the total number of pages across all books.
     */
    private static int totalPages(List<Book> books) {
        // TODO: Implement
        // HINT: mapToInt, sum
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Returns a list of book authors in alphabetic order.
     * Should not contain duplicate names.
     */
    private static List<String> allAuthorsAlphabetic(List<Book> books) {
        // TODO: Implement
        // HINT: flatMap
        throw new UnsupportedOperationException("Not implemented");
    }

    public static void main(String[] args) {
        List<Book> books = List.of(
                new Book("Structure and Interpretation of Computer Programs", List.of("Abelson, Harold", "Sussman, Gerald J."), 657),
                new Book("Principles of Compiler Design", List.of("Aho, Alfred", "Ullman, Jeffrey"), 614),
                new Book("Introduction to Functional Programming", List.of("Bird, Richard", "Wadler, Phil"), 270),
                new Book("The Java Language Specification", List.of("Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad", "Buckley, Alex", "Smith, Daniel"), 808),
                new Book("Effective Java", List.of("Bloch, Joshua"), 416),
                new Book("Java Puzzlers", List.of("Bloch, Joshua", "Gafter, Neal"), 312),
                new Book("Programming in Scala", List.of("Odersky, Martin", "Spoon, Lex", "Venners, Bill"), 859)
        );

        output("Titles only", bookTitles(books));
        output("Thick books", thickBooks(books));
        output("Books by \"Bloch, Joshua\"", booksByFullName(books, "Bloch, Joshua"));
        output("Books by \"bloch\"", booksByPartialName(books, "bloch"));
        output("Total pages", totalPages(books));
        output("All authors in alphabetic order", allAuthorsAlphabetic(books));
    }

    private static <T> void output(String prompt, T obj) {
        System.out.println(prompt + ":");
        System.out.println(obj);
        System.out.println();
    }
}
