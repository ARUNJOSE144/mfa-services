
package com.sixdee.imp.service.servicedto.resp.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PointInfoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PointInfoDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://resp.serviceDTO.service.imp.sixdee.com/xsd}ResponseDTO">
 *       &lt;sequence>
 *         &lt;element name="rewardPoints" type="{http://resp.serviceDTO.service.imp.sixdee.com/xsd}PointInfoDetailsDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statusPoints" type="{http://resp.serviceDTO.service.imp.sixdee.com/xsd}PointInfoDetailsDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointInfoDTO", propOrder = {
    "rewardPoints",
    "statusPoints"
})
public class PointInfoDTO
    extends ResponseDTO
{

    @XmlElement(nillable = true)
    protected List<PointInfoDetailsDTO> rewardPoints;
    @XmlElement(nillable = true)
    protected List<PointInfoDetailsDTO> statusPoints;

    /**
     * Gets the value of the rewardPoints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rewardPoints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRewardPoints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PointInfoDetailsDTO }
     * 
     * 
     */
    public List<PointInfoDetailsDTO> getRewardPoints() {
        if (rewardPoints == null) {
            rewardPoints = new ArrayList<PointInfoDetailsDTO>();
        }
        return this.rewardPoints;
    }

    /**
     * Gets the value of the statusPoints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statusPoints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatusPoints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PointInfoDetailsDTO }
     * 
     * 
     */
    public List<PointInfoDetailsDTO> getStatusPoints() {
        if (statusPoints == null) {
            statusPoints = new ArrayList<PointInfoDetailsDTO>();
        }
        return this.statusPoints;
    }

}
