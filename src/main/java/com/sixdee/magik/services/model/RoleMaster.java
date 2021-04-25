package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.google.api.client.util.DateTime;

@Entity
@Table(name = "MFS_ROLE_MASTER")
public class RoleMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int roleId;

	@Column(name = "ROLE_NAME", nullable = false)
	private String roleName;

	@Column(name = "CREATED_DATE", updatable = false)
	private Date createDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "ROLE_DESC")
	private String description;

	@Transient
	private List<RolePermission> permissions;

	@Transient
	private String createDateUI;

	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH,
	 * CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST }, targetEntity =
	 * FeatureMaster.class)
	 * 
	 * @JoinTable(name = "MFS_ROLES_PERMISSIONS", joinColumns = @JoinColumn(name =
	 * "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "FEATURE_ID")) private
	 * Set<FeatureMaster> featureList;
	 * 
	 * @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles") private
	 * Set<DesignationHierarchy> desigList;
	 */

	@Transient
	private List<FeatureMaster> features;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RolePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<RolePermission> permissions) {
		this.permissions = permissions;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<FeatureMaster> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureMaster> features) {
		this.features = features;
	}
	/*
	 * 
	 * 
	 * public Set<FeatureMaster> getFeatureList() { return featureList; }
	 * 
	 * public void setFeatureList(Set<FeatureMaster> featureList) { this.featureList
	 * = featureList; }
	 * 
	 * public Set<DesignationHierarchy> getDesigList() { return desigList; }
	 * 
	 * public void setDesigList(Set<DesignationHierarchy> desigList) {
	 * this.desigList = desigList; }
	 */

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "RoleMaster [roleId=" + roleId + ", roleName=" + roleName + ", createDate=" + createDate + ", createdBy="
				+ createdBy + ", description=" + description + ", permissions=" + permissions + ", createDateUI="
				+ createDateUI + ", features=" + features + "]";
	}

}