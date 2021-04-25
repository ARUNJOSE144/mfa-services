package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Entity
@Table(name = "DB_LOY_LOY_POINTS_EARN_RDMD")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EarnedRedeemptionTO {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EarnedRedeemptionTO")
	@TableGenerator(name = "EarnedRedeemptionTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "POINTS")
	private String points;

	@Column(name = "SUBCOUNT")
	private String subCount;

	@Transient
	private String createDateUI;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getSubCount() {
		return subCount;
	}

	public void setSubCount(String subCount) {
		this.subCount = subCount;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "EarnedRedeemptionTO [id=" + id + ", date=" + date + ", points=" + points + ", subCount=" + subCount
				+ ", createDateUI=" + createDateUI + "]";
	}

}
