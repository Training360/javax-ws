package catalog;

import javax.xml.ws.Endpoint;

public class BookCatalogMain {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8086/books", new BookService());
    }
}
