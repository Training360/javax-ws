package catalog;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogClientMain {

    public static void main(String[] args) {
        List<Book> books =
                ClientBuilder.newClient()
                .target("http://localhost:8087/books")
                .request(
                           MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Book>>(){});
        System.out.println(books.get(0).getClass());
        System.out.println(books);
        books.stream().forEach(b -> System.out.println(b.getTitle()));
    }
}
