
package com.sixdee.imp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sixdee.imp.service.servicedto.req.xsd.TransferPointsDTO;


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
 *         &lt;element name="transferLineDTO" type="{http://req.serviceDTO.service.imp.sixdee.com/xsd}TransferPointsDTO" minOccurs="0"/>
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
    "transferLineDTO"
})
@XmlRootElement(name = "transferLine")
public class TransferLine {

    @XmlElementRef(name = "transferLineDTO", namespace = "http://service.imp.sixdee.com", type = JAXBElement.class, required = false)
    protected JAXBElement<TransferPointsDTO> transferLineDTO;

    /**
     * Gets the value of the transferLineDTO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TransferPointsDTO }{@code >}
     *     
     */
    public JAXBElement<TransferPointsDTO> getTransferLineDTO() {
        return transferLineDTO;
    }

    /**
     * Sets the value of the transferLineDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TransferPointsDTO }{@code >}
     *     
     */
    public void setTransferLineDTO(JAXBElement<TransferPointsDTO> value) {
        this.transferLineDTO = value;
    }

}
