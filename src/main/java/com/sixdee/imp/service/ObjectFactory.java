
package com.sixdee.imp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.sixdee.imp.service.servicedto.req.xsd.AddPointsDTO;
import com.sixdee.imp.service.servicedto.req.xsd.PackageDTO;
import com.sixdee.imp.service.servicedto.req.xsd.PointDetailsDTO;
import com.sixdee.imp.service.servicedto.req.xsd.RedeemDTO;
import com.sixdee.imp.service.servicedto.req.xsd.RewardPointsDTO;
import com.sixdee.imp.service.servicedto.req.xsd.TransferPointsDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.PackageInfoDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.PointInfoDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.ResponseCodeDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.ResponseDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sixdee.imp.service package. 
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

    private final static QName _TransferLineResponseReturn_QNAME = new QName("http://service.imp.sixdee.com", "return");
    private final static QName _GetPackagesPackageDTO_QNAME = new QName("http://service.imp.sixdee.com", "packageDTO");
    private final static QName _AddPointsAddPointsDTO_QNAME = new QName("http://service.imp.sixdee.com", "addPointsDTO");
    private final static QName _GetPointDetailsPointDetailsDTO_QNAME = new QName("http://service.imp.sixdee.com", "pointDetailsDTO");
    private final static QName _TransferLineTransferLineDTO_QNAME = new QName("http://service.imp.sixdee.com", "transferLineDTO");
    private final static QName _TransferPointsTransferPointsDTO_QNAME = new QName("http://service.imp.sixdee.com", "transferPointsDTO");
    private final static QName _RedeemPointsRedeemDTO_QNAME = new QName("http://service.imp.sixdee.com", "redeemDTO");
    private final static QName _RewardPointsCalculationRewardPointsDTO_QNAME = new QName("http://service.imp.sixdee.com", "rewardPointsDTO");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sixdee.imp.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPoints }
     * 
     */
    public AddPoints createAddPoints() {
        return new AddPoints();
    }

    /**
     * Create an instance of {@link TransferPointsResponse }
     * 
     */
    public TransferPointsResponse createTransferPointsResponse() {
        return new TransferPointsResponse();
    }

    /**
     * Create an instance of {@link AddPointsResponse }
     * 
     */
    public AddPointsResponse createAddPointsResponse() {
        return new AddPointsResponse();
    }

    /**
     * Create an instance of {@link TransferLine }
     * 
     */
    public TransferLine createTransferLine() {
        return new TransferLine();
    }

    /**
     * Create an instance of {@link RewardPointsCalculationResponse }
     * 
     */
    public RewardPointsCalculationResponse createRewardPointsCalculationResponse() {
        return new RewardPointsCalculationResponse();
    }

    /**
     * Create an instance of {@link RedeemPoints }
     * 
     */
    public RedeemPoints createRedeemPoints() {
        return new RedeemPoints();
    }

    /**
     * Create an instance of {@link TransferLineResponse }
     * 
     */
    public TransferLineResponse createTransferLineResponse() {
        return new TransferLineResponse();
    }

    /**
     * Create an instance of {@link TransferPoints }
     * 
     */
    public TransferPoints createTransferPoints() {
        return new TransferPoints();
    }

    /**
     * Create an instance of {@link GetPackagesResponse }
     * 
     */
    public GetPackagesResponse createGetPackagesResponse() {
        return new GetPackagesResponse();
    }

    /**
     * Create an instance of {@link RewardPointsCalculation }
     * 
     */
    public RewardPointsCalculation createRewardPointsCalculation() {
        return new RewardPointsCalculation();
    }

    /**
     * Create an instance of {@link GetPackages }
     * 
     */
    public GetPackages createGetPackages() {
        return new GetPackages();
    }

    /**
     * Create an instance of {@link RedeemPointsResponse }
     * 
     */
    public RedeemPointsResponse createRedeemPointsResponse() {
        return new RedeemPointsResponse();
    }

    /**
     * Create an instance of {@link GetPointDetailsResponse }
     * 
     */
    public GetPointDetailsResponse createGetPointDetailsResponse() {
        return new GetPointDetailsResponse();
    }

    /**
     * Create an instance of {@link GetPointDetails }
     * 
     */
    public GetPointDetails createGetPointDetails() {
        return new GetPointDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "return", scope = TransferLineResponse.class)
    public JAXBElement<ResponseDTO> createTransferLineResponseReturn(ResponseDTO value) {
        return new JAXBElement<ResponseDTO>(_TransferLineResponseReturn_QNAME, ResponseDTO.class, TransferLineResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "return", scope = TransferPointsResponse.class)
    public JAXBElement<ResponseDTO> createTransferPointsResponseReturn(ResponseDTO value) {
        return new JAXBElement<ResponseDTO>(_TransferLineResponseReturn_QNAME, ResponseDTO.class, TransferPointsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackageDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "packageDTO", scope = GetPackages.class)
    public JAXBElement<PackageDTO> createGetPackagesPackageDTO(PackageDTO value) {
        return new JAXBElement<PackageDTO>(_GetPackagesPackageDTO_QNAME, PackageDTO.class, GetPackages.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPointsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "addPointsDTO", scope = AddPoints.class)
    public JAXBElement<AddPointsDTO> createAddPointsAddPointsDTO(AddPointsDTO value) {
        return new JAXBElement<AddPointsDTO>(_AddPointsAddPointsDTO_QNAME, AddPointsDTO.class, AddPoints.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointDetailsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "pointDetailsDTO", scope = GetPointDetails.class)
    public JAXBElement<PointDetailsDTO> createGetPointDetailsPointDetailsDTO(PointDetailsDTO value) {
        return new JAXBElement<PointDetailsDTO>(_GetPointDetailsPointDetailsDTO_QNAME, PointDetailsDTO.class, GetPointDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseCodeDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "return", scope = AddPointsResponse.class)
    public JAXBElement<ResponseCodeDTO> createAddPointsResponseReturn(ResponseCodeDTO value) {
        return new JAXBElement<ResponseCodeDTO>(_TransferLineResponseReturn_QNAME, ResponseCodeDTO.class, AddPointsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferPointsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "transferLineDTO", scope = TransferLine.class)
    public JAXBElement<TransferPointsDTO> createTransferLineTransferLineDTO(TransferPointsDTO value) {
        return new JAXBElement<TransferPointsDTO>(_TransferLineTransferLineDTO_QNAME, TransferPointsDTO.class, TransferLine.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferPointsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "transferPointsDTO", scope = TransferPoints.class)
    public JAXBElement<TransferPointsDTO> createTransferPointsTransferPointsDTO(TransferPointsDTO value) {
        return new JAXBElement<TransferPointsDTO>(_TransferPointsTransferPointsDTO_QNAME, TransferPointsDTO.class, TransferPoints.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "return", scope = RewardPointsCalculationResponse.class)
    public JAXBElement<ResponseDTO> createRewardPointsCalculationResponseReturn(ResponseDTO value) {
        return new JAXBElement<ResponseDTO>(_TransferLineResponseReturn_QNAME, ResponseDTO.class, RewardPointsCalculationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "return", scope = RedeemPointsResponse.class)
    public JAXBElement<ResponseDTO> createRedeemPointsResponseReturn(ResponseDTO value) {
        return new JAXBElement<ResponseDTO>(_TransferLineResponseReturn_QNAME, ResponseDTO.class, RedeemPointsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackageInfoDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "return", scope = GetPackagesResponse.class)
    public JAXBElement<PackageInfoDTO> createGetPackagesResponseReturn(PackageInfoDTO value) {
        return new JAXBElement<PackageInfoDTO>(_TransferLineResponseReturn_QNAME, PackageInfoDTO.class, GetPackagesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointInfoDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "return", scope = GetPointDetailsResponse.class)
    public JAXBElement<PointInfoDTO> createGetPointDetailsResponseReturn(PointInfoDTO value) {
        return new JAXBElement<PointInfoDTO>(_TransferLineResponseReturn_QNAME, PointInfoDTO.class, GetPointDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RedeemDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "redeemDTO", scope = RedeemPoints.class)
    public JAXBElement<RedeemDTO> createRedeemPointsRedeemDTO(RedeemDTO value) {
        return new JAXBElement<RedeemDTO>(_RedeemPointsRedeemDTO_QNAME, RedeemDTO.class, RedeemPoints.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RewardPointsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.imp.sixdee.com", name = "rewardPointsDTO", scope = RewardPointsCalculation.class)
    public JAXBElement<RewardPointsDTO> createRewardPointsCalculationRewardPointsDTO(RewardPointsDTO value) {
        return new JAXBElement<RewardPointsDTO>(_RewardPointsCalculationRewardPointsDTO_QNAME, RewardPointsDTO.class, RewardPointsCalculation.class, value);
    }

}
