package dom;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CatalogServiceTest {

    @Test
    public void testParse() {
        CatalogService catalogService = new CatalogService();
        List<Book> books = catalogService.parse(CatalogServiceTest.class
                .getResourceAsStream("/catalog.xml"));

//        System.out.println(books);
        assertEquals("059610149X", books.get(0).getIsbn10());
        assertEquals("Pro XML Development with Java Technology",
                books.get(1).getTitle());
    }

    @Test
    public void testWrite() {
        List<Book> books = List.of(new Book("Java and XML", "059610149X"),
                new Book("Pro XML Development with Java Technology","1590597060"));
        String xml = new CatalogService().writeToXml(books);
        System.out.println(xml);
    }
}
