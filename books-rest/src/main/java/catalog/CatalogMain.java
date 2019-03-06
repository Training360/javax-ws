package catalog;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class CatalogMain {

    public static void main(String[] args) {
        ResourceConfig rc = new ResourceConfig().packages("catalog");

        GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8087/"), rc);


    }
}
