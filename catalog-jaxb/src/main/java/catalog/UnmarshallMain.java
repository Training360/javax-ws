package catalog;

import catalog.entities.Book;
import catalog.entities.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UnmarshallMain {

    public static void main(String[] args) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Catalog.class, Book.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();

        Catalog catalog = (Catalog) unmarshaller
                .unmarshal(UnmarshallMain.class.getResourceAsStream("/catalog.xml"));

        catalog.getBooks().stream().map(Book::getTitle).forEach(System.out::println);

    }
}
