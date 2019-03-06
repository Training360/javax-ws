package catalog;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path("books")
public class BookResource {

    private static final List<Book> books =
            new ArrayList<>(List.of(
                    new Book("Java and REST", "x1"),
                    new Book("Java and XML", "x2")
            ));

    @GET
    public List<Book> listBooks() {
        return books;
    }

    @POST
    public void addBook(Book book) {
        books.add(book);
    }

    @Path("{isbn10}")
    public Book findBookByIsbn10(@PathParam("isbn10") String isbn10) {
        return books.stream().filter(b -> b.getIsbn10().equals(isbn10)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book with isdb10 not found"));
    }

}
