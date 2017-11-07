
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
    private final static QName _IllegalAccessException_QNAME = new QName("http://example/", "IllegalAccessException");
    private final static QName _AddOrderToDatabaseResponse_QNAME = new QName("http://example/", "addOrderToDatabaseResponse");
    private final static QName _GetPreferredDriversResponse_QNAME = new QName("http://example/", "getPreferredDriversResponse");
    private final static QName _AddOrderToDatabase_QNAME = new QName("http://example/", "addOrderToDatabase");
    private final static QName _GetPreferredDrivers_QNAME = new QName("http://example/", "getPreferredDrivers");
    private final static QName _ParseException_QNAME = new QName("http://example/", "ParseException");

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
     * Create an instance of {@link AddOrderToDatabase }
     * 
     */
    public AddOrderToDatabase createAddOrderToDatabase() {
        return new AddOrderToDatabase();
    }

    /**
     * Create an instance of {@link GetPreferredDrivers }
     * 
     */
    public GetPreferredDrivers createGetPreferredDrivers() {
        return new GetPreferredDrivers();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link IllegalAccessException }
     * 
     */
    public IllegalAccessException createIllegalAccessException() {
        return new IllegalAccessException();
    }

    /**
     * Create an instance of {@link AddOrderToDatabaseResponse }
     * 
     */
    public AddOrderToDatabaseResponse createAddOrderToDatabaseResponse() {
        return new AddOrderToDatabaseResponse();
    }

    /**
     * Create an instance of {@link GetPreferredDriversResponse }
     * 
     */
    public GetPreferredDriversResponse createGetPreferredDriversResponse() {
        return new GetPreferredDriversResponse();
    }

    /**
     * Create an instance of {@link Driver }
     * 
     */
    public Driver createDriver() {
        return new Driver();
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IllegalAccessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "IllegalAccessException")
    public JAXBElement<IllegalAccessException> createIllegalAccessException(IllegalAccessException value) {
        return new JAXBElement<IllegalAccessException>(_IllegalAccessException_QNAME, IllegalAccessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrderToDatabaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "addOrderToDatabaseResponse")
    public JAXBElement<AddOrderToDatabaseResponse> createAddOrderToDatabaseResponse(AddOrderToDatabaseResponse value) {
        return new JAXBElement<AddOrderToDatabaseResponse>(_AddOrderToDatabaseResponse_QNAME, AddOrderToDatabaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreferredDriversResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getPreferredDriversResponse")
    public JAXBElement<GetPreferredDriversResponse> createGetPreferredDriversResponse(GetPreferredDriversResponse value) {
        return new JAXBElement<GetPreferredDriversResponse>(_GetPreferredDriversResponse_QNAME, GetPreferredDriversResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrderToDatabase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "addOrderToDatabase")
    public JAXBElement<AddOrderToDatabase> createAddOrderToDatabase(AddOrderToDatabase value) {
        return new JAXBElement<AddOrderToDatabase>(_AddOrderToDatabase_QNAME, AddOrderToDatabase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreferredDrivers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getPreferredDrivers")
    public JAXBElement<GetPreferredDrivers> createGetPreferredDrivers(GetPreferredDrivers value) {
        return new JAXBElement<GetPreferredDrivers>(_GetPreferredDrivers_QNAME, GetPreferredDrivers.class, null, value);
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
