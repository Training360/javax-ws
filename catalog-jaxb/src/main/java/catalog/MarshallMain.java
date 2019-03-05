package catalog;

import catalog.entities.Book;
import catalog.entities.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.List;

public class MarshallMain {

    public static void main(String[] args) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Catalog.class, Book.class);
        Marshaller marshaller = ctx.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE);

        Catalog catalog = new Catalog(List.of(
                new Book("Java and XML", "x111", 1999),
                new Book("Java and JAXB", "x112", 2011),
                new Book("Java and Java", "x113", 2010),
                new Book("Java and Spring", "x114", 2010)
        ));

        marshaller.marshal(catalog, System.out);

    }
}
