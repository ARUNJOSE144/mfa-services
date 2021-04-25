
package com.sixdee.imp.service.servicedto.req.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.sixdee.imp.service.servicedto.common.xsd.Data;


/**
 * <p>Java class for PackageDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountValidationCredentials" type="{http://req.serviceDTO.service.imp.sixdee.com/xsd}AccountValidationCredentialsDTO" minOccurs="0"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="data" type="{http://common.serviceDTO.service.imp.sixdee.com/xsd}Data" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="languageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subscriberNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transcationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageDTO", propOrder = {
    "accountValidationCredentials",
    "channel",
    "data",
    "languageId",
    "offerType",
    "pin",
    "subscriberNumber",
    "timestamp",
    "transcationId"
})
public class PackageDTO {

    @XmlElementRef(name = "accountValidationCredentials", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<AccountValidationCredentialsDTO> accountValidationCredentials;
    @XmlElementRef(name = "channel", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> channel;
    @XmlElement(nillable = true)
    protected List<Data> data;
    @XmlElementRef(name = "languageId", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> languageId;
    @XmlElementRef(name = "offerType", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> offerType;
    protected Integer pin;
    @XmlElementRef(name = "subscriberNumber", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subscriberNumber;
    @XmlElementRef(name = "timestamp", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timestamp;
    @XmlElementRef(name = "transcationId", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transcationId;

    /**
     * Gets the value of the accountValidationCredentials property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AccountValidationCredentialsDTO }{@code >}
     *     
     */
    public JAXBElement<AccountValidationCredentialsDTO> getAccountValidationCredentials() {
        return accountValidationCredentials;
    }

    /**
     * Sets the value of the accountValidationCredentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AccountValidationCredentialsDTO }{@code >}
     *     
     */
    public void setAccountValidationCredentials(JAXBElement<AccountValidationCredentialsDTO> value) {
        this.accountValidationCredentials = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChannel(JAXBElement<String> value) {
        this.channel = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the data property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Data }
     * 
     * 
     */
    public List<Data> getData() {
        if (data == null) {
            data = new ArrayList<Data>();
        }
        return this.data;
    }

    /**
     * Gets the value of the languageId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLanguageId() {
        return languageId;
    }

    /**
     * Sets the value of the languageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLanguageId(JAXBElement<String> value) {
        this.languageId = value;
    }

    /**
     * Gets the value of the offerType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOfferType() {
        return offerType;
    }

    /**
     * Sets the value of the offerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOfferType(JAXBElement<String> value) {
        this.offerType = value;
    }

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPin(Integer value) {
        this.pin = value;
    }

    /**
     * Gets the value of the subscriberNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubscriberNumber() {
        return subscriberNumber;
    }

    /**
     * Sets the value of the subscriberNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubscriberNumber(JAXBElement<String> value) {
        this.subscriberNumber = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestamp(JAXBElement<String> value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the transcationId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTranscationId() {
        return transcationId;
    }

    /**
     * Sets the value of the transcationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTranscationId(JAXBElement<String> value) {
        this.transcationId = value;
    }

}
