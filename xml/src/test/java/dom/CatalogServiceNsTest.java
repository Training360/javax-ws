package dom;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;
import static org.xmlunit.matchers.CompareMatcher.isSimilarTo;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

public class CatalogServiceNsTest {

    CatalogServiceNs catalogService = new CatalogServiceNs();

    @Test
    public void testParse() {
        List<Book> books = catalogService.parse(CatalogServiceNsTest.class
                .getResourceAsStream("/catalog-ns2.xml"));

//        System.out.println(books);
        assertEquals("059610149X", books.get(0).getIsbn10());
        assertEquals("Pro XML Development with Java Technology",
                books.get(1).getTitle());
    }

    @Test
    public void testWrite() {
        List<Book> books = List.of(new Book("Java and XML", "059610149X"),
                new Book("Pro XML Development with Java Technology","1590597060"));
        String xml = catalogService.writeToXml(books);

        System.out.println(xml);

        assertThat(xml, isSimilarTo(
                CatalogServiceNsTest.class.getResourceAsStream("/expected-catalog-ns.xml"))
                .ignoreWhitespace());

        assertThat(xml, hasXPath("/catalog/book[@isbn10 = '059610149X']/title",
                equalTo("Java and XML")));
    }
}
