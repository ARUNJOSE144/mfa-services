package com.sixdee.magik.services.model;
/**
 * @author amal.a.s
 * @Date : June, 2020
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TARGETING_AUDIENCE_MAPPING")
public class TargetingAudienceMappingDTO {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TargetingAudienceMappingDTO")
	@TableGenerator(name = "TargetingAudienceMappingDTO")
	private int id;

	@Column(name = "ATTRIBUTE_NAME")
	private String attributeName;

	@Column(name = "ATTRIBUTE_VALUE")
	private String attributeValue;

	@Column(name = "ATTRIBUTE_TYPE")
	private String attributeType;

	public TargetingAudienceMappingDTO(String attributeName, String attributeValue, String attributeType,
			TargetingAudienceMasterDTO targetingMaster) {
		super();
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.attributeType = attributeType;
		this.targetingMaster = targetingMaster;
	}

	@ManyToOne
	@JoinColumn(name = "TARGET_ID", nullable = false)
	private TargetingAudienceMasterDTO targetingMaster;

	public TargetingAudienceMappingDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public TargetingAudienceMasterDTO getTargetingMaster() {
		return targetingMaster;
	}

	public void setTargetingMaster(TargetingAudienceMasterDTO targetingMaster) {
		this.targetingMaster = targetingMaster;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

}
