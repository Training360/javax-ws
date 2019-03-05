package stax;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

public class StaxCatalogServiceTest {

    @Test
    public void testParse() {
        StaxCatalogService catalogService = new StaxCatalogService();
        List<Book> books = catalogService.parseWithCursor(StaxCatalogService.class
                .getResourceAsStream("/catalog-ns.xml"));

//        System.out.println(books);
        assertEquals("059610149X", books.get(0).getIsbn10());
        assertEquals("Pro XML Development with Java Technology",
                books.get(1).getTitle());
    }

    @Test
    public void testParseWithIterator() {
        StaxCatalogService catalogService = new StaxCatalogService();
        List<Book> books = catalogService.parseWithIterator(StaxCatalogService.class
                .getResourceAsStream("/catalog-ns.xml"));

//        System.out.println(books);
        assertEquals("059610149X", books.get(0).getIsbn10());
        assertEquals("Pro XML Development with Java Technology",
                books.get(1).getTitle());
    }

    @Test
    public void testWrite() {
        List<Book> books = List.of(new Book("Java and XML", "059610149X"),
                new Book("Pro XML Development with Java Technology","1590597060"));
        String xml = new StaxCatalogService().writeToXml(books);

        System.out.println(xml);

        assertThat(xml, isIdenticalTo(StaxCatalogServiceTest.class.getResourceAsStream("/expected-catalog.xml"))
                .ignoreWhitespace());

        assertThat(xml, hasXPath("/catalog/book[@isbn10 = '059610149X']/title",
                equalTo("Java and XML")));
    }
}
