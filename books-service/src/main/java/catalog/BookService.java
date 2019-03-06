package catalog;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class BookService {

    @WebMethod
    public List<Book> findAllBooks() {
        return List.of(
                new Book("Java and SOAP", "x1234"),
                new Book("Java and WSDL", "x1234"),
                new Book("Java and UDDI", "x1234"),
                new Book("Java and ME", "x1234"),
                new Book("Java and learnwebservices", "x1234"),
                new Book("Java and Java", "x1234"),
                new Book("Java and And", "x1234")
                );
    }
}
