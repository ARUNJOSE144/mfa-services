
package com.sixdee.imp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sixdee.imp.service.servicedto.req.xsd.RewardPointsDTO;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rewardPointsDTO" type="{http://req.serviceDTO.service.imp.sixdee.com/xsd}RewardPointsDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rewardPointsDTO"
})
@XmlRootElement(name = "rewardPointsCalculation")
public class RewardPointsCalculation {

    @XmlElementRef(name = "rewardPointsDTO", namespace = "http://service.imp.sixdee.com", type = JAXBElement.class, required = false)
    protected JAXBElement<RewardPointsDTO> rewardPointsDTO;

    /**
     * Gets the value of the rewardPointsDTO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RewardPointsDTO }{@code >}
     *     
     */
    public JAXBElement<RewardPointsDTO> getRewardPointsDTO() {
        return rewardPointsDTO;
    }

    /**
     * Sets the value of the rewardPointsDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RewardPointsDTO }{@code >}
     *     
     */
    public void setRewardPointsDTO(JAXBElement<RewardPointsDTO> value) {
        this.rewardPointsDTO = value;
    }

}
