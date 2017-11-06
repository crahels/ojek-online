package classes;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "classes")
public class TestClass {
    @WebMethod(operationName = "sayHelloWorldFrom")
    public String sayHelloWorldFrom(String from) {
        String result = "Hello, world, from " + from;
        System.out.println(result);
        return result;
    }
}