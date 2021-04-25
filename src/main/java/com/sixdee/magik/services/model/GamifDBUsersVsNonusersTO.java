package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ramesh.cheerla
 * @Date : July, 2020
 *
 */

@Entity
@Table(name = "DB_GAM_USERS_NONUSERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class GamifDBUsersVsNonusersTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GamifDBUsersVsNonusersTO")
	@TableGenerator(name = "GamifDBUsersVsNonusersTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private String date;

	@Column(name = "USERS")
	private int users;

	@Column(name = "NON_USERS")
	private int nonUsers;

	@Column(name = "USER_PERCENTAGE")
	private int userPer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	public int getNonUsers() {
		return nonUsers;
	}

	public void setNonUsers(int nonUsers) {
		this.nonUsers = nonUsers;
	}

	public int getUserPer() {
		return userPer;
	}

	public void setUserPer(int userPer) {
		this.userPer = userPer;
	}

	@Override
	public String toString() {
		return "GamifDBUsersVsNonusersTO [id=" + id + ", date=" + date + ", users=" + users + ", nonUsers=" + nonUsers
				+ ", userPer=" + userPer + "]";
	}

}
