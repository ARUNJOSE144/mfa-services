package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author: Arun Jose
 * @Date : March, 2021
 */
@Data
@Entity
@Table(name = "MFS_ROLE_MASTER")
public class RolesTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int roleId;

	@Column(name = "ROLE_NAME", nullable = false)
	private String roleName;

	

}