package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(serviceName = "OrderGojekService")
public class OrderGojek {
    @WebMethod(operationName = "checkExpiryTime")
    public boolean checkExpiryTime(String token) {
        //implementasi cek ke identity service
        // true means udh expired
        return false;
    }
}
