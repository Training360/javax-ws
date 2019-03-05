package sax;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SaxCatalogServiceTest {

    @Test
    public void testParse() {
        SaxCatalogService catalogService = new SaxCatalogService();
        List<Book> books = catalogService.parse(SaxCatalogServiceTest.class
                .getResourceAsStream("/catalog-ns2.xml"));

//        System.out.println(books);
        assertEquals("059610149X", books.get(0).getIsbn10());
        assertEquals("Pro XML Development with Java Technology",
                books.get(1).getTitle());
    }
}
