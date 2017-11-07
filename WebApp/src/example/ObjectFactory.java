
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

    private final static QName _HidePassanger_QNAME = new QName("http://example/", "hidePassanger");
    private final static QName _HidePassangerResponse_QNAME = new QName("http://example/", "hidePassangerResponse");
    private final static QName _CheckExpiryTimeResponse_QNAME = new QName("http://example/", "checkExpiryTimeResponse");
    private final static QName _GetDriverHistoryResponse_QNAME = new QName("http://example/", "getDriverHistoryResponse");
    private final static QName _CheckExpiryTime_QNAME = new QName("http://example/", "checkExpiryTime");
    private final static QName _HideDriverResponse_QNAME = new QName("http://example/", "hideDriverResponse");
    private final static QName _ClassNotFoundException_QNAME = new QName("http://example/", "ClassNotFoundException");
    private final static QName _IllegalAccessException_QNAME = new QName("http://example/", "IllegalAccessException");
    private final static QName _InstantiationException_QNAME = new QName("http://example/", "InstantiationException");
    private final static QName _GetDriverHistory_QNAME = new QName("http://example/", "getDriverHistory");
    private final static QName _HideDriver_QNAME = new QName("http://example/", "hideDriver");
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
     * Create an instance of {@link GetDriverHistoryResponse }
     * 
     */
    public GetDriverHistoryResponse createGetDriverHistoryResponse() {
        return new GetDriverHistoryResponse();
    }

    /**
     * Create an instance of {@link CheckExpiryTime }
     * 
     */
    public CheckExpiryTime createCheckExpiryTime() {
        return new CheckExpiryTime();
    }

    /**
     * Create an instance of {@link HideDriverResponse }
     * 
     */
    public HideDriverResponse createHideDriverResponse() {
        return new HideDriverResponse();
    }

    /**
     * Create an instance of {@link HidePassanger }
     * 
     */
    public HidePassanger createHidePassanger() {
        return new HidePassanger();
    }

    /**
     * Create an instance of {@link HidePassangerResponse }
     * 
     */
    public HidePassangerResponse createHidePassangerResponse() {
        return new HidePassangerResponse();
    }

    /**
     * Create an instance of {@link GetDriverHistory }
     * 
     */
    public GetDriverHistory createGetDriverHistory() {
        return new GetDriverHistory();
    }

    /**
     * Create an instance of {@link HideDriver }
     * 
     */
    public HideDriver createHideDriver() {
        return new HideDriver();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link ClassNotFoundException }
     * 
     */
    public ClassNotFoundException createClassNotFoundException() {
        return new ClassNotFoundException();
    }

    /**
     * Create an instance of {@link IllegalAccessException }
     * 
     */
    public IllegalAccessException createIllegalAccessException() {
        return new IllegalAccessException();
    }

    /**
     * Create an instance of {@link InstantiationException }
     * 
     */
    public InstantiationException createInstantiationException() {
        return new InstantiationException();
    }

    /**
     * Create an instance of {@link Throwable }
     * 
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link UserDriverHistory }
     * 
     */
    public UserDriverHistory createUserDriverHistory() {
        return new UserDriverHistory();
    }

    /**
     * Create an instance of {@link StackTraceElement }
     * 
     */
    public StackTraceElement createStackTraceElement() {
        return new StackTraceElement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HidePassanger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "hidePassanger")
    public JAXBElement<HidePassanger> createHidePassanger(HidePassanger value) {
        return new JAXBElement<HidePassanger>(_HidePassanger_QNAME, HidePassanger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HidePassangerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "hidePassangerResponse")
    public JAXBElement<HidePassangerResponse> createHidePassangerResponse(HidePassangerResponse value) {
        return new JAXBElement<HidePassangerResponse>(_HidePassangerResponse_QNAME, HidePassangerResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDriverHistoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getDriverHistoryResponse")
    public JAXBElement<GetDriverHistoryResponse> createGetDriverHistoryResponse(GetDriverHistoryResponse value) {
        return new JAXBElement<GetDriverHistoryResponse>(_GetDriverHistoryResponse_QNAME, GetDriverHistoryResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link HideDriverResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "hideDriverResponse")
    public JAXBElement<HideDriverResponse> createHideDriverResponse(HideDriverResponse value) {
        return new JAXBElement<HideDriverResponse>(_HideDriverResponse_QNAME, HideDriverResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "ClassNotFoundException")
    public JAXBElement<ClassNotFoundException> createClassNotFoundException(ClassNotFoundException value) {
        return new JAXBElement<ClassNotFoundException>(_ClassNotFoundException_QNAME, ClassNotFoundException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link InstantiationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "InstantiationException")
    public JAXBElement<InstantiationException> createInstantiationException(InstantiationException value) {
        return new JAXBElement<InstantiationException>(_InstantiationException_QNAME, InstantiationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDriverHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getDriverHistory")
    public JAXBElement<GetDriverHistory> createGetDriverHistory(GetDriverHistory value) {
        return new JAXBElement<GetDriverHistory>(_GetDriverHistory_QNAME, GetDriverHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HideDriver }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "hideDriver")
    public JAXBElement<HideDriver> createHideDriver(HideDriver value) {
        return new JAXBElement<HideDriver>(_HideDriver_QNAME, HideDriver.class, null, value);
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
