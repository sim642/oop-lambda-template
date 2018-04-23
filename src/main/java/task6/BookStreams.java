package task6;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * TASK 6.
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
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    /**
     * Returns books with at least 500 pages.
     */
    private static List<Book> thickBooks(List<Book> books) {
        // TODO: Implement
        return books.stream()
                .filter(book -> book.getPages() >= 500)
                .collect(Collectors.toList());
    }

    /**
     * Returns books that have the exact author.
     */
    private static List<Book> booksByFullName(List<Book> books, String author) {
        // TODO: Implement
        return books.stream()
                .filter(book -> book.getAuthors().contains(author))
                .collect(Collectors.toList());
    }

    /**
     * Returns true if all books have more than one author, or false otherwise.
     */
    private static boolean allManyAuthors(List<Book> books) {
        // TODO: Implement
        return books.stream()
                .allMatch(book -> book.getAuthors().size() > 1);
    }

    /**
     * Returns the total number of pages across all books.
     */
    private static int totalPages(List<Book> books) {
        // TODO: Implement
        // HINT: mapToInt, sum
        return books.stream()
                .mapToInt(Book::getPages)
                .sum();
    }

    /**
     * Returns books that have the partially given author.
     * Be case insensitive.
     */
    private static List<Book> booksByPartialName(List<Book> books, String partialAuthor) {
        // TODO HARD: Implement
        return books.stream()
                .filter(book -> book.getAuthors().stream() // stream within a stream
                        .anyMatch(author -> author.toLowerCase().contains(partialAuthor.toLowerCase()))
                )
                .collect(Collectors.toList());
    }

    /**
     * Returns the book with most authors.
     */
    private static Book bookWithMostAuthors(List<Book> books) {
        // TODO HARD: Implement
        // HINT: max
        return books.stream()
                .max((lhs, rhs) -> Integer.compare(lhs.getAuthors().size(), rhs.getAuthors().size()))
                //.max(Comparator.comparingInt(book -> book.getAuthors().size())) // IntelliJ suggests neater way
                .get();
    }

    /**
     * Returns a list of book authors in alphabetic order.
     * Should not contain duplicate names.
     */
    private static List<String> allAuthorsAlphabetic(List<Book> books) {
        // TODO HARD: Implement
        // HINT: flatMap
        return books.stream()
                .flatMap(book -> book.getAuthors().stream()) // stream within a stream
                .sorted()
                .distinct() // remove duplicates
                .collect(Collectors.toList());
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
        output("All have many authors", allManyAuthors(books));
        output("Total pages", totalPages(books));
        output("Books by \"bloch\"", booksByPartialName(books, "bloch"));
        output("Book with most authors", bookWithMostAuthors(books));
        output("All authors in alphabetic order", allAuthorsAlphabetic(books));
    }

    private static <T> void output(String prompt, T obj) {
        System.out.println(prompt + ":");
        System.out.println(obj);
        System.out.println();
    }
}
