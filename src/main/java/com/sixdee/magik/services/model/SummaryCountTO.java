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
@Table(name = "DB_GAM_SUMMARY_COUNT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SummaryCountTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SummaryCountTO")
	@TableGenerator(name = "SummaryCountTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "REWARD")
	private String reward;

	@Column(name = "POINTS_COUNT")
	private int pointsType;

	@Column(name = "TYPE")
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public int getPointsType() {
		return pointsType;
	}

	public void setPointsType(int pointsType) {
		this.pointsType = pointsType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SummaryCountTO [id=" + id + ", reward=" + reward + ", pointsType=" + pointsType + ", type=" + type
				+ "]";
	}

}
