
package example;

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
@WebService(name = "HelloWorld", targetNamespace = "http://example/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloWorld {


    /**
     * 
     * @param arg0
     * @return
     *     returns int
     * @throws IOException_Exception
     * @throws ParseException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "expiryTime", targetNamespace = "http://example/", className = "example.ExpiryTime")
    @ResponseWrapper(localName = "expiryTimeResponse", targetNamespace = "http://example/", className = "example.ExpiryTimeResponse")
    @Action(input = "http://example/HelloWorld/expiryTimeRequest", output = "http://example/HelloWorld/expiryTimeResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://example/HelloWorld/expiryTime/Fault/IOException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://example/HelloWorld/expiryTime/Fault/ParseException")
    })
    public int expiryTime(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws IOException_Exception, ParseException_Exception
    ;

}
