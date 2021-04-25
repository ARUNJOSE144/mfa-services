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
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Entity
@Table(name = "DB_GAM_LOYALITY_SEGMENT_USERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LoyaltyCaategoryWiseGamificationTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LoyaltyCaategoryWiseGamificationTO")
	@TableGenerator(name = "LoyaltyCaategoryWiseGamificationTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "USERS")
	private int users;

	@Column(name = "NON_USERS")
	private int nonUsers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	@Override
	public String toString() {
		return "LoyaltyCaategoryWiseGamificationTO [id=" + id + ", category=" + category + ", users=" + users
				+ ", nonUsers=" + nonUsers + "]";
	}

}
