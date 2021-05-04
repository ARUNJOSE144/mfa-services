package com.inno.mfa.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
@Data
@Entity
@Table(name = "FEATURE_MASTER")
public class FeatureMasterTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FEATURE_ID")
	private int featureId;

	@Column(name = "FEATURE_NAME", nullable = false)
	private String featurName;

	@Column(name = "MODULE_ID")
	private int moduleId;

	@Column(name = "IS_DEFAULT")
	private int isDefault;

}