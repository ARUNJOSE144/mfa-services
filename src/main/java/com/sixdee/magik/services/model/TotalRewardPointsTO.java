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
@Table(name = "DB_LOY_REWD_POINTS_STATUS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TotalRewardPointsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TotalRewardPointsTO")
	@TableGenerator(name = "TotalRewardPointsTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "TOT_POINTS")
	private String totPoints;

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

	public String getTotPoints() {
		return totPoints;
	}

	public void setTotPoints(String totPoints) {
		this.totPoints = totPoints;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "TotalRewardPointsTO [id=" + id + ", date=" + date + ", totPoints=" + totPoints + ", createDateUI="
				+ createDateUI + "]";
	}

}
