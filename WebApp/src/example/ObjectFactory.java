
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

    private final static QName _GetDriverByID_QNAME = new QName("http://example/", "getDriverByID");
    private final static QName _GetDataUser_QNAME = new QName("http://example/", "getDataUser");
    private final static QName _GetDriverByIDResponse_QNAME = new QName("http://example/", "getDriverByIDResponse");
    private final static QName _IllegalAccessException_QNAME = new QName("http://example/", "IllegalAccessException");
    private final static QName _ParseException_QNAME = new QName("http://example/", "ParseException");
    private final static QName _GetDataUserResponse_QNAME = new QName("http://example/", "getDataUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: example
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDriverByIDResponse }
     * 
     */
    public GetDriverByIDResponse createGetDriverByIDResponse() {
        return new GetDriverByIDResponse();
    }

    /**
     * Create an instance of {@link GetDriverByID }
     * 
     */
    public GetDriverByID createGetDriverByID() {
        return new GetDriverByID();
    }

    /**
     * Create an instance of {@link GetDataUser }
     * 
     */
    public GetDataUser createGetDataUser() {
        return new GetDataUser();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link GetDataUserResponse }
     * 
     */
    public GetDataUserResponse createGetDataUserResponse() {
        return new GetDataUserResponse();
    }

    /**
     * Create an instance of {@link IllegalAccessException }
     * 
     */
    public IllegalAccessException createIllegalAccessException() {
        return new IllegalAccessException();
    }

    /**
     * Create an instance of {@link Driver }
     * 
     */
    public Driver createDriver() {
        return new Driver();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDriverByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getDriverByID")
    public JAXBElement<GetDriverByID> createGetDriverByID(GetDriverByID value) {
        return new JAXBElement<GetDriverByID>(_GetDriverByID_QNAME, GetDriverByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getDataUser")
    public JAXBElement<GetDataUser> createGetDataUser(GetDataUser value) {
        return new JAXBElement<GetDataUser>(_GetDataUser_QNAME, GetDataUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDriverByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getDriverByIDResponse")
    public JAXBElement<GetDriverByIDResponse> createGetDriverByIDResponse(GetDriverByIDResponse value) {
        return new JAXBElement<GetDriverByIDResponse>(_GetDriverByIDResponse_QNAME, GetDriverByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IllegalAccessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "IllegalAccessException")
    public JAXBElement<IllegalAccessException> createIllegalAccessException(IllegalAccessException value) {
        return new JAXBElement<IllegalAccessException>(_IllegalAccessException_QNAME, IllegalAccessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getDataUserResponse")
    public JAXBElement<GetDataUserResponse> createGetDataUserResponse(GetDataUserResponse value) {
        return new JAXBElement<GetDataUserResponse>(_GetDataUserResponse_QNAME, GetDataUserResponse.class, null, value);
    }

}
