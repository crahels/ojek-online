
package example;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrderGojek", targetNamespace = "http://example/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OrderGojek {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<example.Driver>
     * @throws IllegalAccessException_Exception
     * @throws ParseException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPreferredDrivers", targetNamespace = "http://example/", className = "example.GetPreferredDrivers")
    @ResponseWrapper(localName = "getPreferredDriversResponse", targetNamespace = "http://example/", className = "example.GetPreferredDriversResponse")
    @Action(input = "http://example/OrderGojek/getPreferredDriversRequest", output = "http://example/OrderGojek/getPreferredDriversResponse", fault = {
        @FaultAction(className = IllegalAccessException_Exception.class, value = "http://example/OrderGojek/getPreferredDrivers/Fault/IllegalAccessException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://example/OrderGojek/getPreferredDrivers/Fault/ParseException")
    })
    public List<Driver> getPreferredDrivers(
        @WebParam(name = "arg0", targetNamespace = "")
        boolean arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4)
        throws IllegalAccessException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @throws IllegalAccessException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "addOrderToDatabase", targetNamespace = "http://example/", className = "example.AddOrderToDatabase")
    @ResponseWrapper(localName = "addOrderToDatabaseResponse", targetNamespace = "http://example/", className = "example.AddOrderToDatabaseResponse")
    @Action(input = "http://example/OrderGojek/addOrderToDatabaseRequest", output = "http://example/OrderGojek/addOrderToDatabaseResponse", fault = {
        @FaultAction(className = IllegalAccessException_Exception.class, value = "http://example/OrderGojek/addOrderToDatabase/Fault/IllegalAccessException")
    })
    public void addOrderToDatabase(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        int arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        String arg5)
        throws IllegalAccessException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkExpiryTime", targetNamespace = "http://example/", className = "example.CheckExpiryTime")
    @ResponseWrapper(localName = "checkExpiryTimeResponse", targetNamespace = "http://example/", className = "example.CheckExpiryTimeResponse")
    @Action(input = "http://example/OrderGojek/checkExpiryTimeRequest", output = "http://example/OrderGojek/checkExpiryTimeResponse")
    public boolean checkExpiryTime(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
