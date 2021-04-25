package com.sixdee.magik.services.model;
/**
 * @author amal.a.s
 * @Date : June, 2020
 *
 */

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="TARGETING_AUDIENCE_MASTER")
@DynamicUpdate(true)
public class TargetingAudienceMasterDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="TargetingAudienceMasterDTO")
	@TableGenerator( name="TargetingAudienceMasterDTO")
	@Column(name="ID")
	private int targetId;
	
	@Column(name="TARGET_NAME")
	private String targetName;
	
	@Column(name="TARGET_DESCRIPTION")
	private String targetDesc;
	
	@Column(name="MEDIA_TYPE")
	private String mediaType; 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "targetingMaster")
	private List<TargetingAudienceMappingDTO> mappingDetails;

	public TargetingAudienceMasterDTO() {}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetDesc() {
		return targetDesc;
	}

	public void setTargetDesc(String targetDesc) {
		this.targetDesc = targetDesc;
	}

	public List<TargetingAudienceMappingDTO> getMappingDetails() {
		return mappingDetails;
	}

	public void setMappingDetails(List<TargetingAudienceMappingDTO> mappingDetails) {
		this.mappingDetails = mappingDetails;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	
	
	
}
