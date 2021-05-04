package com.inno.mfa.services.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Priority;
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
@Table(name = "MODULE_MASTER")
public class ModuleMasterTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MODULE_ID")
	private int moduleId;

	@Column(name = "NAME", nullable = false)
	private String moduleName;

	@Transient
	private List<FeatureMasterTo> features;

}