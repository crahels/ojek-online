
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
@WebService(name = "HistoryGojek", targetNamespace = "http://example/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HistoryGojek {


    /**
     * 
     * @param arg0
     * @return
     *     returns int
     * @throws ParseException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "expiryTime", targetNamespace = "http://example/", className = "example.ExpiryTime")
    @ResponseWrapper(localName = "expiryTimeResponse", targetNamespace = "http://example/", className = "example.ExpiryTimeResponse")
    @Action(input = "http://example/HistoryGojek/expiryTimeRequest", output = "http://example/HistoryGojek/expiryTimeResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://example/HistoryGojek/expiryTime/Fault/IOException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://example/HistoryGojek/expiryTime/Fault/ParseException")
    })
    public int expiryTime(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws IOException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<example.UserDriverHistory>
     * @throws ClassNotFoundException_Exception
     * @throws IllegalAccessException_Exception
     * @throws InstantiationException_Exception
     * @throws ParseException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPassengerHistory", targetNamespace = "http://example/", className = "example.GetPassengerHistory")
    @ResponseWrapper(localName = "getPassengerHistoryResponse", targetNamespace = "http://example/", className = "example.GetPassengerHistoryResponse")
    @Action(input = "http://example/HistoryGojek/getPassengerHistoryRequest", output = "http://example/HistoryGojek/getPassengerHistoryResponse", fault = {
        @FaultAction(className = IllegalAccessException_Exception.class, value = "http://example/HistoryGojek/getPassengerHistory/Fault/IllegalAccessException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://example/HistoryGojek/getPassengerHistory/Fault/ParseException"),
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://example/HistoryGojek/getPassengerHistory/Fault/ClassNotFoundException"),
        @FaultAction(className = InstantiationException_Exception.class, value = "http://example/HistoryGojek/getPassengerHistory/Fault/InstantiationException")
    })
    public List<UserDriverHistory> getPassengerHistory(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0)
        throws ClassNotFoundException_Exception, IllegalAccessException_Exception, InstantiationException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws IllegalAccessException_Exception
     * @throws ParseException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hidePassanger", targetNamespace = "http://example/", className = "example.HidePassanger")
    @ResponseWrapper(localName = "hidePassangerResponse", targetNamespace = "http://example/", className = "example.HidePassangerResponse")
    @Action(input = "http://example/HistoryGojek/hidePassangerRequest", output = "http://example/HistoryGojek/hidePassangerResponse", fault = {
        @FaultAction(className = IllegalAccessException_Exception.class, value = "http://example/HistoryGojek/hidePassanger/Fault/IllegalAccessException"),
        @FaultAction(className = IOException_Exception.class, value = "http://example/HistoryGojek/hidePassanger/Fault/IOException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://example/HistoryGojek/hidePassanger/Fault/ParseException")
    })
    public boolean hidePassanger(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1)
        throws IOException_Exception, IllegalAccessException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws IllegalAccessException_Exception
     * @throws IOException_Exception
     * @throws ParseException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hideDriver", targetNamespace = "http://example/", className = "example.HideDriver")
    @ResponseWrapper(localName = "hideDriverResponse", targetNamespace = "http://example/", className = "example.HideDriverResponse")
    @Action(input = "http://example/HistoryGojek/hideDriverRequest", output = "http://example/HistoryGojek/hideDriverResponse", fault = {
        @FaultAction(className = IllegalAccessException_Exception.class, value = "http://example/HistoryGojek/hideDriver/Fault/IllegalAccessException"),
        @FaultAction(className = IOException_Exception.class, value = "http://example/HistoryGojek/hideDriver/Fault/IOException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://example/HistoryGojek/hideDriver/Fault/ParseException")
    })
    public boolean hideDriver(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1)
        throws IOException_Exception, IllegalAccessException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<example.UserDriverHistory>
     * @throws ClassNotFoundException_Exception
     * @throws IllegalAccessException_Exception
     * @throws InstantiationException_Exception
     * @throws ParseException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getDriverHistory", targetNamespace = "http://example/", className = "example.GetDriverHistory")
    @ResponseWrapper(localName = "getDriverHistoryResponse", targetNamespace = "http://example/", className = "example.GetDriverHistoryResponse")
    @Action(input = "http://example/HistoryGojek/getDriverHistoryRequest", output = "http://example/HistoryGojek/getDriverHistoryResponse", fault = {
        @FaultAction(className = IllegalAccessException_Exception.class, value = "http://example/HistoryGojek/getDriverHistory/Fault/IllegalAccessException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://example/HistoryGojek/getDriverHistory/Fault/ParseException"),
        @FaultAction(className = ClassNotFoundException_Exception.class, value = "http://example/HistoryGojek/getDriverHistory/Fault/ClassNotFoundException"),
        @FaultAction(className = InstantiationException_Exception.class, value = "http://example/HistoryGojek/getDriverHistory/Fault/InstantiationException")
    })
    public List<UserDriverHistory> getDriverHistory(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0)
        throws ClassNotFoundException_Exception, IllegalAccessException_Exception, InstantiationException_Exception, ParseException_Exception
    ;

}
