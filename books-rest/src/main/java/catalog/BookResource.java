package catalog;

import java.util.ArrayList;
import java.util.List;

public class BookResource {

    private static final List<Book> books =
            new ArrayList<>(List.of(
                    new Book("Java and REST", "x1"),
                    new Book("Java and XML", "x2")
            ));

    public List<Book> listBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
