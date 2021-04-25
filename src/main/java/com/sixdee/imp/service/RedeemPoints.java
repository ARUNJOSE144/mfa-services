
package com.sixdee.imp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sixdee.imp.service.servicedto.req.xsd.RedeemDTO;


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
 *         &lt;element name="redeemDTO" type="{http://req.serviceDTO.service.imp.sixdee.com/xsd}RedeemDTO" minOccurs="0"/>
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
    "redeemDTO"
})
@XmlRootElement(name = "redeemPoints")
public class RedeemPoints {

    @XmlElementRef(name = "redeemDTO", namespace = "http://service.imp.sixdee.com", type = JAXBElement.class, required = false)
    protected JAXBElement<RedeemDTO> redeemDTO;

    /**
     * Gets the value of the redeemDTO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RedeemDTO }{@code >}
     *     
     */
    public JAXBElement<RedeemDTO> getRedeemDTO() {
        return redeemDTO;
    }

    /**
     * Sets the value of the redeemDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RedeemDTO }{@code >}
     *     
     */
    public void setRedeemDTO(JAXBElement<RedeemDTO> value) {
        this.redeemDTO = value;
    }

}
