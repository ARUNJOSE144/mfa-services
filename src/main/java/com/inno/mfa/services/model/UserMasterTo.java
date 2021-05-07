package com.inno.mfa.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
@Data
@Entity
@Table(name = "USER_MASTER")
public class UserMasterTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private RolesTo role;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	private String password = "admin123";

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "EMAIL_ID", nullable = false)
	private String emailId;

	@Column(name = "CONTACT_NUMBER", nullable = false)
	private String contactNumber;

	@Column(name = "WRONG_PWD_ATTEMPT", nullable = false)
	private int wrongPasswordAttempts;

	@Column(name = "STATUS", nullable = false)
	private int status;

	@Column(name = "ROLE_ID", nullable = false)
	private int roleId;

}