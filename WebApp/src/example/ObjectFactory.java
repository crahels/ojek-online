
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

    private final static QName _ExpiryTimeResponse_QNAME = new QName("http://example/", "expiryTimeResponse");
    private final static QName _ExpiryTime_QNAME = new QName("http://example/", "expiryTime");
    private final static QName _IOException_QNAME = new QName("http://example/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://example/", "ParseException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: example
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExpiryTime }
     * 
     */
    public ExpiryTime createExpiryTime() {
        return new ExpiryTime();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link ExpiryTimeResponse }
     * 
     */
    public ExpiryTimeResponse createExpiryTimeResponse() {
        return new ExpiryTimeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpiryTimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "expiryTimeResponse")
    public JAXBElement<ExpiryTimeResponse> createExpiryTimeResponse(ExpiryTimeResponse value) {
        return new JAXBElement<ExpiryTimeResponse>(_ExpiryTimeResponse_QNAME, ExpiryTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpiryTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "expiryTime")
    public JAXBElement<ExpiryTime> createExpiryTime(ExpiryTime value) {
        return new JAXBElement<ExpiryTime>(_ExpiryTime_QNAME, ExpiryTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

}
