package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MFS_MODULE_MASTER")
public class ModuleMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MODULE_ID")
	private Integer moduleId;

	@Column(name = "NAME", nullable = false)
	private String roleName;
	

	@Transient
	private List<FeatureMaster> featureList;

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<FeatureMaster> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<FeatureMaster> featureList) {
		this.featureList = featureList;
	}
	
	@Override
	public String toString() {
		return "RoleMaster [moduleId=" + moduleId + ", roleName=" + roleName + ", featureList=" + featureList + "]";
	}



}