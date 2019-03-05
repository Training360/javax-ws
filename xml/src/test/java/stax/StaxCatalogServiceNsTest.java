package stax;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isIdenticalTo;
import static org.xmlunit.matchers.CompareMatcher.isSimilarTo;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

public class StaxCatalogServiceNsTest {



    @Test
    public void testWrite() {
        List<Book> books = List.of(new Book("Java and XML", "059610149X"),
                new Book("Pro XML Development with Java Technology","1590597060"));
        String xml = new StaxCatalogServiceNs().writeToXml(books);

        System.out.println(xml);

        assertThat(xml, isSimilarTo(
                StaxCatalogServiceNsTest.class.getResourceAsStream("/expected-catalog-ns.xml"))
                .ignoreWhitespace());

//        assertThat(xml, hasXPath("/catalog/book[@isbn10 = '059610149X']/title",
//                equalTo("Java and XML")));
    }
}
