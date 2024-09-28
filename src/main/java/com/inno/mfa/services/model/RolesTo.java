package com.inno.mfa.services.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Entity
@Table(name = "ROLE_MASTER")
public class RolesTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int roleId;

	@Column(name = "ROLE_NAME", nullable = false)
	private String roleName;

	@Column(name = "CREATED_DATE", nullable = false)
	private Timestamp createdDate;

	@Column(name = "ROLE_DESC", nullable = true)
	private String description;

	@Transient
	private int[] featureIds;

	@Transient
	private List<RolePermissionsTo> featureList;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int[] getFeatureIds() {
		return featureIds;
	}

	public void setFeatureIds(int[] featureIds) {
		this.featureIds = featureIds;
	}

	public List<RolePermissionsTo> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<RolePermissionsTo> featureList) {
		this.featureList = featureList;
	}

	
}