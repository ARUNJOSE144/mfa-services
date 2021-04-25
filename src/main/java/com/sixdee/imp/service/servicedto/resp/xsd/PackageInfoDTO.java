
package com.sixdee.imp.service.servicedto.resp.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageInfoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PackageInfoDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://resp.serviceDTO.service.imp.sixdee.com/xsd}ResponseDTO">
 *       &lt;sequence>
 *         &lt;element name="packages" type="{http://resp.serviceDTO.service.imp.sixdee.com/xsd}PackageDetailsDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageInfoDTO", propOrder = {
    "packages"
})
public class PackageInfoDTO
    extends ResponseDTO
{

    @XmlElement(nillable = true)
    protected List<PackageDetailsDTO> packages;

    /**
     * Gets the value of the packages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageDetailsDTO }
     * 
     * 
     */
    public List<PackageDetailsDTO> getPackages() {
        if (packages == null) {
            packages = new ArrayList<PackageDetailsDTO>();
        }
        return this.packages;
    }

}
