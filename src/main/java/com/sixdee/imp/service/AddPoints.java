
package com.sixdee.imp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sixdee.imp.service.servicedto.req.xsd.AddPointsDTO;


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
 *         &lt;element name="addPointsDTO" type="{http://req.serviceDTO.service.imp.sixdee.com/xsd}AddPointsDTO" minOccurs="0"/>
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
    "addPointsDTO"
})
@XmlRootElement(name = "addPoints")
public class AddPoints {

    @XmlElementRef(name = "addPointsDTO", namespace = "http://service.imp.sixdee.com", type = JAXBElement.class, required = false)
    protected JAXBElement<AddPointsDTO> addPointsDTO;

    /**
     * Gets the value of the addPointsDTO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddPointsDTO }{@code >}
     *     
     */
    public JAXBElement<AddPointsDTO> getAddPointsDTO() {
        return addPointsDTO;
    }

    /**
     * Sets the value of the addPointsDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddPointsDTO }{@code >}
     *     
     */
    public void setAddPointsDTO(JAXBElement<AddPointsDTO> value) {
        this.addPointsDTO = value;
    }

}
