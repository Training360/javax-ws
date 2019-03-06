package catalog;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService
public class BookService {

    @WebMethod
    @WebResult(name = "book")
    @ResponseWrapper(localName = "catalog")
    @RequestWrapper(localName = "list-catalog")
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

    @WebMethod
    public void saveBook(@XmlElement(required = true) @WebParam(name = "book") Book book) {
        System.out.println(book.getTitle());
        System.out.println(book.getIsbn10());
    }
}
