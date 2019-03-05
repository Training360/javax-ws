package xpath;

import org.junit.Test;

import java.util.List;

public class XpathCatalogServiceTest {

    @Test
    public void testListTitles() {
        List<String> names = new XpathCatalogService().listTitles(
                XpathCatalogServiceTest.class.getResourceAsStream("/catalog-ns.xml"));
        System.out.println(names);
    }
}
