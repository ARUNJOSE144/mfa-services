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
@Table(name = "`DB_LOY_NEW_ENROL_ACTIV_INFO`")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NewEnrollmentsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "NewEnrollmentsTO")
	@TableGenerator(name = "NewEnrollmentsTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "S2G")
	private int s2g;

	@Column(name = "G2P")
	private int g2p;

	@Column(name = "MIG_TREND")
	private int mig_trend;

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

	public int getS2g() {
		return s2g;
	}

	public void setS2g(int s2g) {
		this.s2g = s2g;
	}

	public int getG2p() {
		return g2p;
	}

	public void setG2p(int g2p) {
		this.g2p = g2p;
	}

	public int getMig_trend() {
		return mig_trend;
	}

	public void setMig_trend(int mig_trend) {
		this.mig_trend = mig_trend;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "NewEnrollmentsTO [id=" + id + ", date=" + date + ", s2g=" + s2g + ", g2p=" + g2p + ", mig_trend="
				+ mig_trend + ", createDateUI=" + createDateUI + "]";
	}

}
