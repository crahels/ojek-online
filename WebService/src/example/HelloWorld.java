package example;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(serviceName = "HelloWorld")
public class HelloWorld {
    @WebMethod(operationName = "sayHelloWorldFrom")
    public String sayHelloWorldFrom(String from) {
        String result = "Hello, world, from " + from;
        System.out.println(result);
        return result;
    }
    public static void main(String[] argv) {
        Object implementor = new HelloWorld ();
        String address = "http://localhost:8002/HelloWorld";
        Endpoint.publish(address, implementor);
    }
}
