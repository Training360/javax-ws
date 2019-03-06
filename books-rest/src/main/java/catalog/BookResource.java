package catalog;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GET
    @Path("{isbn10}")
    public Response findBookByIsbn10(@PathParam("isbn10") String isbn10) {

        Optional<Book> book =
        books.stream().filter(b -> b.getIsbn10().equals(isbn10)).findFirst()
                ;
        if (book.isPresent()) {
            return Response.ok(book.get()).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).entity(new StatusMessage("Not found")).build();
        }

    }

}
