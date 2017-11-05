
package example;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the example package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckExpiryTimeResponse_QNAME = new QName("http://example/", "checkExpiryTimeResponse");
    private final static QName _CheckExpiryTime_QNAME = new QName("http://example/", "checkExpiryTime");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: example
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckExpiryTimeResponse }
     * 
     */
    public CheckExpiryTimeResponse createCheckExpiryTimeResponse() {
        return new CheckExpiryTimeResponse();
    }

    /**
     * Create an instance of {@link CheckExpiryTime }
     * 
     */
    public CheckExpiryTime createCheckExpiryTime() {
        return new CheckExpiryTime();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckExpiryTimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "checkExpiryTimeResponse")
    public JAXBElement<CheckExpiryTimeResponse> createCheckExpiryTimeResponse(CheckExpiryTimeResponse value) {
        return new JAXBElement<CheckExpiryTimeResponse>(_CheckExpiryTimeResponse_QNAME, CheckExpiryTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckExpiryTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "checkExpiryTime")
    public JAXBElement<CheckExpiryTime> createCheckExpiryTime(CheckExpiryTime value) {
        return new JAXBElement<CheckExpiryTime>(_CheckExpiryTime_QNAME, CheckExpiryTime.class, null, value);
    }

}
