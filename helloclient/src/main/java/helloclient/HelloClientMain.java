package helloclient;

import com.learnwebservices.services.hello.HelloEndpoint;
import com.learnwebservices.services.hello.HelloEndpointService;
import com.learnwebservices.services.hello.HelloRequest;
import com.learnwebservices.services.hello.HelloResponse;

import java.net.URL;

public class HelloClientMain {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://www.learnwebservices.com/services/hello?wsdl");
        HelloEndpointService service = new HelloEndpointService(url);
        HelloEndpoint endpoint = service.getHelloEndpointPort();

        HelloRequest request = new HelloRequest();
        request.setName("István");

        HelloResponse response = endpoint.sayHello(request);
        System.out.println(response.getMessage());
    }
}
