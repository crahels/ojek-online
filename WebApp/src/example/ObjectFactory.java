
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
    private final static QName _DeleteLocation_QNAME = new QName("http://example/", "deleteLocation");
    private final static QName _CheckExpiryTime_QNAME = new QName("http://example/", "checkExpiryTime");
    private final static QName _DeleteLocationResponse_QNAME = new QName("http://example/", "deleteLocationResponse");
    private final static QName _SaveLocationResponse_QNAME = new QName("http://example/", "saveLocationResponse");
    private final static QName _IllegalAccessException_QNAME = new QName("http://example/", "IllegalAccessException");
    private final static QName _GetUserLocationResponse_QNAME = new QName("http://example/", "getUserLocationResponse");
    private final static QName _AddLocationResponse_QNAME = new QName("http://example/", "addLocationResponse");
    private final static QName _GetUserLocation_QNAME = new QName("http://example/", "getUserLocation");
    private final static QName _SaveLocation_QNAME = new QName("http://example/", "saveLocation");
    private final static QName _AddLocation_QNAME = new QName("http://example/", "addLocation");

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
     * Create an instance of {@link DeleteLocation }
     * 
     */
    public DeleteLocation createDeleteLocation() {
        return new DeleteLocation();
    }

    /**
     * Create an instance of {@link CheckExpiryTime }
     * 
     */
    public CheckExpiryTime createCheckExpiryTime() {
        return new CheckExpiryTime();
    }

    /**
     * Create an instance of {@link AddLocationResponse }
     * 
     */
    public AddLocationResponse createAddLocationResponse() {
        return new AddLocationResponse();
    }

    /**
     * Create an instance of {@link GetUserLocation }
     * 
     */
    public GetUserLocation createGetUserLocation() {
        return new GetUserLocation();
    }

    /**
     * Create an instance of {@link SaveLocation }
     * 
     */
    public SaveLocation createSaveLocation() {
        return new SaveLocation();
    }

    /**
     * Create an instance of {@link AddLocation }
     * 
     */
    public AddLocation createAddLocation() {
        return new AddLocation();
    }

    /**
     * Create an instance of {@link DeleteLocationResponse }
     * 
     */
    public DeleteLocationResponse createDeleteLocationResponse() {
        return new DeleteLocationResponse();
    }

    /**
     * Create an instance of {@link SaveLocationResponse }
     * 
     */
    public SaveLocationResponse createSaveLocationResponse() {
        return new SaveLocationResponse();
    }

    /**
     * Create an instance of {@link IllegalAccessException }
     * 
     */
    public IllegalAccessException createIllegalAccessException() {
        return new IllegalAccessException();
    }

    /**
     * Create an instance of {@link GetUserLocationResponse }
     * 
     */
    public GetUserLocationResponse createGetUserLocationResponse() {
        return new GetUserLocationResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "deleteLocation")
    public JAXBElement<DeleteLocation> createDeleteLocation(DeleteLocation value) {
        return new JAXBElement<DeleteLocation>(_DeleteLocation_QNAME, DeleteLocation.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "deleteLocationResponse")
    public JAXBElement<DeleteLocationResponse> createDeleteLocationResponse(DeleteLocationResponse value) {
        return new JAXBElement<DeleteLocationResponse>(_DeleteLocationResponse_QNAME, DeleteLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "saveLocationResponse")
    public JAXBElement<SaveLocationResponse> createSaveLocationResponse(SaveLocationResponse value) {
        return new JAXBElement<SaveLocationResponse>(_SaveLocationResponse_QNAME, SaveLocationResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getUserLocationResponse")
    public JAXBElement<GetUserLocationResponse> createGetUserLocationResponse(GetUserLocationResponse value) {
        return new JAXBElement<GetUserLocationResponse>(_GetUserLocationResponse_QNAME, GetUserLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "addLocationResponse")
    public JAXBElement<AddLocationResponse> createAddLocationResponse(AddLocationResponse value) {
        return new JAXBElement<AddLocationResponse>(_AddLocationResponse_QNAME, AddLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "getUserLocation")
    public JAXBElement<GetUserLocation> createGetUserLocation(GetUserLocation value) {
        return new JAXBElement<GetUserLocation>(_GetUserLocation_QNAME, GetUserLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "saveLocation")
    public JAXBElement<SaveLocation> createSaveLocation(SaveLocation value) {
        return new JAXBElement<SaveLocation>(_SaveLocation_QNAME, SaveLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example/", name = "addLocation")
    public JAXBElement<AddLocation> createAddLocation(AddLocation value) {
        return new JAXBElement<AddLocation>(_AddLocation_QNAME, AddLocation.class, null, value);
    }

}
