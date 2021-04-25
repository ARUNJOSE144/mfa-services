
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
 * <p>Java class for RewardPointsDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RewardPointsDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="data" type="{http://common.serviceDTO.service.imp.sixdee.com/xsd}Data" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rewardPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="rewardPointsCategory" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="statusPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="subscriberNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="volume" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RewardPointsDTO", propOrder = {
    "channel",
    "data",
    "pin",
    "rewardPoints",
    "rewardPointsCategory",
    "statusPoints",
    "subscriberNumber",
    "timestamp",
    "transactionID",
    "volume"
})
public class RewardPointsDTO {

    @XmlElementRef(name = "channel", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> channel;
    @XmlElement(nillable = true)
    protected List<Data> data;
    protected Integer pin;
    @XmlElementRef(name = "rewardPoints", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> rewardPoints;
    @XmlElementRef(name = "rewardPointsCategory", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> rewardPointsCategory;
    @XmlElementRef(name = "statusPoints", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> statusPoints;
    @XmlElementRef(name = "subscriberNumber", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subscriberNumber;
    @XmlElementRef(name = "timestamp", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timestamp;
    @XmlElementRef(name = "transactionID", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transactionID;
    @XmlElementRef(name = "volume", namespace = "http://req.serviceDTO.service.imp.sixdee.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> volume;

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
     * Gets the value of the rewardPoints property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getRewardPoints() {
        return rewardPoints;
    }

    /**
     * Sets the value of the rewardPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setRewardPoints(JAXBElement<Double> value) {
        this.rewardPoints = value;
    }

    /**
     * Gets the value of the rewardPointsCategory property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getRewardPointsCategory() {
        return rewardPointsCategory;
    }

    /**
     * Sets the value of the rewardPointsCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setRewardPointsCategory(JAXBElement<Integer> value) {
        this.rewardPointsCategory = value;
    }

    /**
     * Gets the value of the statusPoints property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getStatusPoints() {
        return statusPoints;
    }

    /**
     * Sets the value of the statusPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setStatusPoints(JAXBElement<Double> value) {
        this.statusPoints = value;
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
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransactionID(JAXBElement<String> value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setVolume(JAXBElement<Double> value) {
        this.volume = value;
    }

}
