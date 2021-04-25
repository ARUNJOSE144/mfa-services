
package com.sixdee.imp.service.servicedto.resp.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sixdee.imp.service.servicedto.resp.xsd package. 
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

    private final static QName _ResponseDTOStatusDescription_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "statusDescription");
    private final static QName _ResponseDTOTranscationId_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "transcationId");
    private final static QName _ResponseDTOStatusCode_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "statusCode");
    private final static QName _ResponseDTOTimestamp_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "timestamp");
    private final static QName _ResponseCodeDTOResponseCode_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "responseCode");
    private final static QName _PackageDetailsDTOOfferType_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "offerType");
    private final static QName _PackageDetailsDTOSquareImagePath_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "squareImagePath");
    private final static QName _PackageDetailsDTOIconImagePath_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "iconImagePath");
    private final static QName _PackageDetailsDTOEndDate_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "endDate");
    private final static QName _PackageDetailsDTOPackageName_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "packageName");
    private final static QName _PackageDetailsDTOPackageID_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "packageID");
    private final static QName _PackageDetailsDTOTypeName_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "typeName");
    private final static QName _PackageDetailsDTOInfo_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "info");
    private final static QName _PackageDetailsDTOTermsAndConditions_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "termsAndConditions");
    private final static QName _PackageDetailsDTOStartDate_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "startDate");
    private final static QName _PackageDetailsDTOCategory_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "category");
    private final static QName _PackageDetailsDTORectangleImagePath_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "rectangleImagePath");
    private final static QName _PointInfoDetailsDTOExpiryDate_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "expiryDate");
    private final static QName _PointInfoDetailsDTOPoints_QNAME = new QName("http://resp.serviceDTO.service.imp.sixdee.com/xsd", "points");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sixdee.imp.service.servicedto.resp.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseCodeDTO }
     * 
     */
    public ResponseCodeDTO createResponseCodeDTO() {
        return new ResponseCodeDTO();
    }

    /**
     * Create an instance of {@link PackageInfoDTO }
     * 
     */
    public PackageInfoDTO createPackageInfoDTO() {
        return new PackageInfoDTO();
    }

    /**
     * Create an instance of {@link ResponseDTO }
     * 
     */
    public ResponseDTO createResponseDTO() {
        return new ResponseDTO();
    }

    /**
     * Create an instance of {@link PointInfoDetailsDTO }
     * 
     */
    public PointInfoDetailsDTO createPointInfoDetailsDTO() {
        return new PointInfoDetailsDTO();
    }

    /**
     * Create an instance of {@link PointInfoDTO }
     * 
     */
    public PointInfoDTO createPointInfoDTO() {
        return new PointInfoDTO();
    }

    /**
     * Create an instance of {@link PackageDetailsDTO }
     * 
     */
    public PackageDetailsDTO createPackageDetailsDTO() {
        return new PackageDetailsDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "statusDescription", scope = ResponseDTO.class)
    public JAXBElement<String> createResponseDTOStatusDescription(String value) {
        return new JAXBElement<String>(_ResponseDTOStatusDescription_QNAME, String.class, ResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "transcationId", scope = ResponseDTO.class)
    public JAXBElement<String> createResponseDTOTranscationId(String value) {
        return new JAXBElement<String>(_ResponseDTOTranscationId_QNAME, String.class, ResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "statusCode", scope = ResponseDTO.class)
    public JAXBElement<String> createResponseDTOStatusCode(String value) {
        return new JAXBElement<String>(_ResponseDTOStatusCode_QNAME, String.class, ResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "timestamp", scope = ResponseDTO.class)
    public JAXBElement<String> createResponseDTOTimestamp(String value) {
        return new JAXBElement<String>(_ResponseDTOTimestamp_QNAME, String.class, ResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "responseCode", scope = ResponseCodeDTO.class)
    public JAXBElement<String> createResponseCodeDTOResponseCode(String value) {
        return new JAXBElement<String>(_ResponseCodeDTOResponseCode_QNAME, String.class, ResponseCodeDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "offerType", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOOfferType(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOOfferType_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "squareImagePath", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOSquareImagePath(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOSquareImagePath_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "iconImagePath", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOIconImagePath(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOIconImagePath_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "endDate", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOEndDate(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOEndDate_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "packageName", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOPackageName(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOPackageName_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "packageID", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOPackageID(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOPackageID_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "typeName", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOTypeName(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOTypeName_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "info", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOInfo(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOInfo_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "termsAndConditions", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOTermsAndConditions(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOTermsAndConditions_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "startDate", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOStartDate(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOStartDate_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "category", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTOCategory(String value) {
        return new JAXBElement<String>(_PackageDetailsDTOCategory_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "rectangleImagePath", scope = PackageDetailsDTO.class)
    public JAXBElement<String> createPackageDetailsDTORectangleImagePath(String value) {
        return new JAXBElement<String>(_PackageDetailsDTORectangleImagePath_QNAME, String.class, PackageDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "expiryDate", scope = PointInfoDetailsDTO.class)
    public JAXBElement<String> createPointInfoDetailsDTOExpiryDate(String value) {
        return new JAXBElement<String>(_PointInfoDetailsDTOExpiryDate_QNAME, String.class, PointInfoDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resp.serviceDTO.service.imp.sixdee.com/xsd", name = "points", scope = PointInfoDetailsDTO.class)
    public JAXBElement<String> createPointInfoDetailsDTOPoints(String value) {
        return new JAXBElement<String>(_PointInfoDetailsDTOPoints_QNAME, String.class, PointInfoDetailsDTO.class, value);
    }

}
