package stax;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StaxCatalogServiceTest {

    @Test
    public void testParse() {
        StaxCatalogService catalogService = new StaxCatalogService();
        List<Book> books = catalogService.parseWithCursor(StaxCatalogService.class
                .getResourceAsStream("/catalog.xml"));

//        System.out.println(books);
        assertEquals("059610149X", books.get(0).getIsbn10());
        assertEquals("Pro XML Development with Java Technology",
                books.get(1).getTitle());
    }

    @Test
    public void testParseWithIterator() {
        StaxCatalogService catalogService = new StaxCatalogService();
        List<Book> books = catalogService.parseWithIterator(StaxCatalogService.class
                .getResourceAsStream("/catalog.xml"));

//        System.out.println(books);
        assertEquals("059610149X", books.get(0).getIsbn10());
        assertEquals("Pro XML Development with Java Technology",
                books.get(1).getTitle());
    }
}
