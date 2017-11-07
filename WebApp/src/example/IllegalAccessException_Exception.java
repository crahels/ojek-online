
package example;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "IllegalAccessException", targetNamespace = "http://example/")
public class IllegalAccessException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private IllegalAccessException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public IllegalAccessException_Exception(String message, IllegalAccessException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public IllegalAccessException_Exception(String message, IllegalAccessException faultInfo, java.lang.Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: example.IllegalAccessException
     */
    public IllegalAccessException getFaultInfo() {
        return faultInfo;
    }

}
