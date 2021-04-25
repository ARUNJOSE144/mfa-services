package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MFS_FEATURE_MASTER")
public class FeatureMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FEATURE_ID", nullable = false)
	private Integer featureId;

	@Column(name = "FEATURE_NAME", nullable = false)
	private String featurName;

	@Column(name = "MODULE_ID", nullable = false)
	private Integer moduleId;

	@Column(name = "IS_DEFAULT", nullable = false)
	private short isDefault;
	
	
	

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public String getFeaturName() {
		return featurName;
	}

	public void setFeaturName(String featurName) {
		this.featurName = featurName;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(short isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "FeatureMaster [featureId=" + featureId + ", featurName=" + featurName + ", moduleId=" + moduleId
				+ ", isDefault=" + isDefault + "]";
	}

   

}