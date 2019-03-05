package catalog;

import generated.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Main {

    public static void main(String[] args)
    throws Exception{

        Catalog catalog = new Catalog();
        Catalog.Book book = new Catalog.Book();
        book.setTitle("Java and XML");
        book.setIsbn10("XXX");
        catalog.getBook().add(book);

        JAXBContext context = JAXBContext.newInstance(Catalog.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(catalog, System.out);
    }
}
