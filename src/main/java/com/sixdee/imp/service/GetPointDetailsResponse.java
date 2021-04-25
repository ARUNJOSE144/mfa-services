
package com.sixdee.imp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sixdee.imp.service.servicedto.resp.xsd.PointInfoDTO;


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
 *         &lt;element name="return" type="{http://resp.serviceDTO.service.imp.sixdee.com/xsd}PointInfoDTO" minOccurs="0"/>
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
    "_return"
})
@XmlRootElement(name = "getPointDetailsResponse")
public class GetPointDetailsResponse {

    @XmlElementRef(name = "return", namespace = "http://service.imp.sixdee.com", type = JAXBElement.class, required = false)
    protected JAXBElement<PointInfoDTO> _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PointInfoDTO }{@code >}
     *     
     */
    public JAXBElement<PointInfoDTO> getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PointInfoDTO }{@code >}
     *     
     */
    public void setReturn(JAXBElement<PointInfoDTO> value) {
        this._return = value;
    }

}
