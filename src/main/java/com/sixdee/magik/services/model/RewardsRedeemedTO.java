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
@Table(name = "DB_GAM_REWARDS_REDEEMED_COUNT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RewardsRedeemedTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RewardsRedeemedTO")
	@TableGenerator(name = "RewardsRedeemedTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "VALUE")
	private int value;

	@Column(name = "TYPE")
	private String type;

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RewardsRedeemedTO [id=" + id + ", category=" + category + ", value=" + value + ", type=" + type + "]";
	}

}
