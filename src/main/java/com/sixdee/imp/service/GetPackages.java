
package com.sixdee.imp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.sixdee.imp.service.servicedto.req.xsd.PackageDTO;


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
 *         &lt;element name="packageDTO" type="{http://req.serviceDTO.service.imp.sixdee.com/xsd}PackageDTO" minOccurs="0"/>
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
    "packageDTO"
})
@XmlRootElement(name = "getPackages")
public class GetPackages {

    @XmlElementRef(name = "packageDTO", namespace = "http://service.imp.sixdee.com", type = JAXBElement.class, required = false)
    protected JAXBElement<PackageDTO> packageDTO;

    /**
     * Gets the value of the packageDTO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PackageDTO }{@code >}
     *     
     */
    public JAXBElement<PackageDTO> getPackageDTO() {
        return packageDTO;
    }

    /**
     * Sets the value of the packageDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PackageDTO }{@code >}
     *     
     */
    public void setPackageDTO(JAXBElement<PackageDTO> value) {
        this.packageDTO = value;
    }

}
