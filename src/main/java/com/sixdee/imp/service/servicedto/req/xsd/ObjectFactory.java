
package com.sixdee.imp.service.servicedto.req.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sixdee.imp.service.servicedto.req.xsd package. 
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

    private final static QName _PackageDTOOfferType_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "offerType");
    private final static QName _PackageDTOChannel_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "channel");
    private final static QName _PackageDTOLanguageId_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "languageId");
    private final static QName _PackageDTOTranscationId_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "transcationId");
    private final static QName _PackageDTOAccountValidationCredentials_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "accountValidationCredentials");
    private final static QName _PackageDTOSubscriberNumber_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "subscriberNumber");
    private final static QName _PackageDTOTimestamp_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "timestamp");
    private final static QName _TransferPointsDTOPin_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "pin");
    private final static QName _TransferPointsDTOLanguageID_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "languageID");
    private final static QName _TransferPointsDTOTransactionID_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "transactionID");
    private final static QName _TransferPointsDTODestSubscriberNumber_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "destSubscriberNumber");
    private final static QName _RedeemDTOMoNumber_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "moNumber");
    private final static QName _RedeemDTOLineNumber_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "lineNumber");
    private final static QName _AddPointsDTOPointAdjustmentId_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "pointAdjustmentId");
    private final static QName _AddPointsDTOAccountNumber_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "accountNumber");
    private final static QName _AddPointsDTOUserName_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "userName");
    private final static QName _RewardPointsDTOVolume_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "volume");
    private final static QName _RewardPointsDTORewardPointsCategory_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "rewardPointsCategory");
    private final static QName _RewardPointsDTOStatusPoints_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "statusPoints");
    private final static QName _RewardPointsDTORewardPoints_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "rewardPoints");
    private final static QName _PointDetailsDTOFromDate_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "fromDate");
    private final static QName _PointDetailsDTOToDate_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "toDate");
    private final static QName _PointDetailsDTOTransactionId_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "transactionId");
    private final static QName _AccountValidationCredentialsDTOPassword_QNAME = new QName("http://req.serviceDTO.service.imp.sixdee.com/xsd", "password");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sixdee.imp.service.servicedto.req.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPointsDTO }
     * 
     */
    public AddPointsDTO createAddPointsDTO() {
        return new AddPointsDTO();
    }

    /**
     * Create an instance of {@link TransferPointsDTO }
     * 
     */
    public TransferPointsDTO createTransferPointsDTO() {
        return new TransferPointsDTO();
    }

    /**
     * Create an instance of {@link RedeemDTO }
     * 
     */
    public RedeemDTO createRedeemDTO() {
        return new RedeemDTO();
    }

    /**
     * Create an instance of {@link RewardPointsDTO }
     * 
     */
    public RewardPointsDTO createRewardPointsDTO() {
        return new RewardPointsDTO();
    }

    /**
     * Create an instance of {@link PackageDTO }
     * 
     */
    public PackageDTO createPackageDTO() {
        return new PackageDTO();
    }

    /**
     * Create an instance of {@link PointDetailsDTO }
     * 
     */
    public PointDetailsDTO createPointDetailsDTO() {
        return new PointDetailsDTO();
    }

    /**
     * Create an instance of {@link AccountValidationCredentialsDTO }
     * 
     */
    public AccountValidationCredentialsDTO createAccountValidationCredentialsDTO() {
        return new AccountValidationCredentialsDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "offerType", scope = PackageDTO.class)
    public JAXBElement<String> createPackageDTOOfferType(String value) {
        return new JAXBElement<String>(_PackageDTOOfferType_QNAME, String.class, PackageDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "channel", scope = PackageDTO.class)
    public JAXBElement<String> createPackageDTOChannel(String value) {
        return new JAXBElement<String>(_PackageDTOChannel_QNAME, String.class, PackageDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "languageId", scope = PackageDTO.class)
    public JAXBElement<String> createPackageDTOLanguageId(String value) {
        return new JAXBElement<String>(_PackageDTOLanguageId_QNAME, String.class, PackageDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "transcationId", scope = PackageDTO.class)
    public JAXBElement<String> createPackageDTOTranscationId(String value) {
        return new JAXBElement<String>(_PackageDTOTranscationId_QNAME, String.class, PackageDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountValidationCredentialsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "accountValidationCredentials", scope = PackageDTO.class)
    public JAXBElement<AccountValidationCredentialsDTO> createPackageDTOAccountValidationCredentials(AccountValidationCredentialsDTO value) {
        return new JAXBElement<AccountValidationCredentialsDTO>(_PackageDTOAccountValidationCredentials_QNAME, AccountValidationCredentialsDTO.class, PackageDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "subscriberNumber", scope = PackageDTO.class)
    public JAXBElement<String> createPackageDTOSubscriberNumber(String value) {
        return new JAXBElement<String>(_PackageDTOSubscriberNumber_QNAME, String.class, PackageDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "timestamp", scope = PackageDTO.class)
    public JAXBElement<String> createPackageDTOTimestamp(String value) {
        return new JAXBElement<String>(_PackageDTOTimestamp_QNAME, String.class, PackageDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "pin", scope = TransferPointsDTO.class)
    public JAXBElement<Integer> createTransferPointsDTOPin(Integer value) {
        return new JAXBElement<Integer>(_TransferPointsDTOPin_QNAME, Integer.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "channel", scope = TransferPointsDTO.class)
    public JAXBElement<String> createTransferPointsDTOChannel(String value) {
        return new JAXBElement<String>(_PackageDTOChannel_QNAME, String.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "languageID", scope = TransferPointsDTO.class)
    public JAXBElement<String> createTransferPointsDTOLanguageID(String value) {
        return new JAXBElement<String>(_TransferPointsDTOLanguageID_QNAME, String.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountValidationCredentialsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "accountValidationCredentials", scope = TransferPointsDTO.class)
    public JAXBElement<AccountValidationCredentialsDTO> createTransferPointsDTOAccountValidationCredentials(AccountValidationCredentialsDTO value) {
        return new JAXBElement<AccountValidationCredentialsDTO>(_PackageDTOAccountValidationCredentials_QNAME, AccountValidationCredentialsDTO.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "subscriberNumber", scope = TransferPointsDTO.class)
    public JAXBElement<String> createTransferPointsDTOSubscriberNumber(String value) {
        return new JAXBElement<String>(_PackageDTOSubscriberNumber_QNAME, String.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "transactionID", scope = TransferPointsDTO.class)
    public JAXBElement<String> createTransferPointsDTOTransactionID(String value) {
        return new JAXBElement<String>(_TransferPointsDTOTransactionID_QNAME, String.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "timestamp", scope = TransferPointsDTO.class)
    public JAXBElement<String> createTransferPointsDTOTimestamp(String value) {
        return new JAXBElement<String>(_PackageDTOTimestamp_QNAME, String.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "destSubscriberNumber", scope = TransferPointsDTO.class)
    public JAXBElement<String> createTransferPointsDTODestSubscriberNumber(String value) {
        return new JAXBElement<String>(_TransferPointsDTODestSubscriberNumber_QNAME, String.class, TransferPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "moNumber", scope = RedeemDTO.class)
    public JAXBElement<String> createRedeemDTOMoNumber(String value) {
        return new JAXBElement<String>(_RedeemDTOMoNumber_QNAME, String.class, RedeemDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "channel", scope = RedeemDTO.class)
    public JAXBElement<String> createRedeemDTOChannel(String value) {
        return new JAXBElement<String>(_PackageDTOChannel_QNAME, String.class, RedeemDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountValidationCredentialsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "accountValidationCredentials", scope = RedeemDTO.class)
    public JAXBElement<AccountValidationCredentialsDTO> createRedeemDTOAccountValidationCredentials(AccountValidationCredentialsDTO value) {
        return new JAXBElement<AccountValidationCredentialsDTO>(_PackageDTOAccountValidationCredentials_QNAME, AccountValidationCredentialsDTO.class, RedeemDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "transactionID", scope = RedeemDTO.class)
    public JAXBElement<String> createRedeemDTOTransactionID(String value) {
        return new JAXBElement<String>(_TransferPointsDTOTransactionID_QNAME, String.class, RedeemDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "timestamp", scope = RedeemDTO.class)
    public JAXBElement<String> createRedeemDTOTimestamp(String value) {
        return new JAXBElement<String>(_PackageDTOTimestamp_QNAME, String.class, RedeemDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "lineNumber", scope = RedeemDTO.class)
    public JAXBElement<String> createRedeemDTOLineNumber(String value) {
        return new JAXBElement<String>(_RedeemDTOLineNumber_QNAME, String.class, RedeemDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "pointAdjustmentId", scope = AddPointsDTO.class)
    public JAXBElement<String> createAddPointsDTOPointAdjustmentId(String value) {
        return new JAXBElement<String>(_AddPointsDTOPointAdjustmentId_QNAME, String.class, AddPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "accountNumber", scope = AddPointsDTO.class)
    public JAXBElement<String> createAddPointsDTOAccountNumber(String value) {
        return new JAXBElement<String>(_AddPointsDTOAccountNumber_QNAME, String.class, AddPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "userName", scope = AddPointsDTO.class)
    public JAXBElement<String> createAddPointsDTOUserName(String value) {
        return new JAXBElement<String>(_AddPointsDTOUserName_QNAME, String.class, AddPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "volume", scope = RewardPointsDTO.class)
    public JAXBElement<Double> createRewardPointsDTOVolume(Double value) {
        return new JAXBElement<Double>(_RewardPointsDTOVolume_QNAME, Double.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "rewardPointsCategory", scope = RewardPointsDTO.class)
    public JAXBElement<Integer> createRewardPointsDTORewardPointsCategory(Integer value) {
        return new JAXBElement<Integer>(_RewardPointsDTORewardPointsCategory_QNAME, Integer.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "channel", scope = RewardPointsDTO.class)
    public JAXBElement<String> createRewardPointsDTOChannel(String value) {
        return new JAXBElement<String>(_PackageDTOChannel_QNAME, String.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "subscriberNumber", scope = RewardPointsDTO.class)
    public JAXBElement<String> createRewardPointsDTOSubscriberNumber(String value) {
        return new JAXBElement<String>(_PackageDTOSubscriberNumber_QNAME, String.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "statusPoints", scope = RewardPointsDTO.class)
    public JAXBElement<Double> createRewardPointsDTOStatusPoints(Double value) {
        return new JAXBElement<Double>(_RewardPointsDTOStatusPoints_QNAME, Double.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "transactionID", scope = RewardPointsDTO.class)
    public JAXBElement<String> createRewardPointsDTOTransactionID(String value) {
        return new JAXBElement<String>(_TransferPointsDTOTransactionID_QNAME, String.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "timestamp", scope = RewardPointsDTO.class)
    public JAXBElement<String> createRewardPointsDTOTimestamp(String value) {
        return new JAXBElement<String>(_PackageDTOTimestamp_QNAME, String.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "rewardPoints", scope = RewardPointsDTO.class)
    public JAXBElement<Double> createRewardPointsDTORewardPoints(Double value) {
        return new JAXBElement<Double>(_RewardPointsDTORewardPoints_QNAME, Double.class, RewardPointsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "fromDate", scope = PointDetailsDTO.class)
    public JAXBElement<String> createPointDetailsDTOFromDate(String value) {
        return new JAXBElement<String>(_PointDetailsDTOFromDate_QNAME, String.class, PointDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "toDate", scope = PointDetailsDTO.class)
    public JAXBElement<String> createPointDetailsDTOToDate(String value) {
        return new JAXBElement<String>(_PointDetailsDTOToDate_QNAME, String.class, PointDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "channel", scope = PointDetailsDTO.class)
    public JAXBElement<String> createPointDetailsDTOChannel(String value) {
        return new JAXBElement<String>(_PackageDTOChannel_QNAME, String.class, PointDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "languageID", scope = PointDetailsDTO.class)
    public JAXBElement<String> createPointDetailsDTOLanguageID(String value) {
        return new JAXBElement<String>(_TransferPointsDTOLanguageID_QNAME, String.class, PointDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "subscriberNumber", scope = PointDetailsDTO.class)
    public JAXBElement<String> createPointDetailsDTOSubscriberNumber(String value) {
        return new JAXBElement<String>(_PackageDTOSubscriberNumber_QNAME, String.class, PointDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "transactionId", scope = PointDetailsDTO.class)
    public JAXBElement<String> createPointDetailsDTOTransactionId(String value) {
        return new JAXBElement<String>(_PointDetailsDTOTransactionId_QNAME, String.class, PointDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "timestamp", scope = PointDetailsDTO.class)
    public JAXBElement<String> createPointDetailsDTOTimestamp(String value) {
        return new JAXBElement<String>(_PackageDTOTimestamp_QNAME, String.class, PointDetailsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "password", scope = AccountValidationCredentialsDTO.class)
    public JAXBElement<String> createAccountValidationCredentialsDTOPassword(String value) {
        return new JAXBElement<String>(_AccountValidationCredentialsDTOPassword_QNAME, String.class, AccountValidationCredentialsDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", name = "userName", scope = AccountValidationCredentialsDTO.class)
    public JAXBElement<String> createAccountValidationCredentialsDTOUserName(String value) {
        return new JAXBElement<String>(_AddPointsDTOUserName_QNAME, String.class, AccountValidationCredentialsDTO.class, value);
    }

}
