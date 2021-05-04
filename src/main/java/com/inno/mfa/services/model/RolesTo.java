package com.inno.mfa.services.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

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
@Data
@Entity
@Table(name = "ROLE_MASTER")
public class RolesTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int roleId;

	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH,
	 * CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST }, targetEntity =
	 * FeatureMasterTo.class)
	 * 
	 * @JoinTable(name = "ROLES_PERMISSIONS", joinColumns = @JoinColumn(name =
	 * "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "FEATURE_ID"))
	 */
	//private Set<RolePermissionsTo> featureList;

	@Column(name = "ROLE_NAME", nullable = false)
	private String roleName;

	@Column(name = "CREATED_DATE", nullable = false)
	private Timestamp createdDate;

	@Column(name = "ROLE_DESC", nullable = true)
	private String description;

	@Transient
	private int[] featureIds;

}