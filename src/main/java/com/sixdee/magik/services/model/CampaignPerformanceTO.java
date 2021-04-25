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
@Table(name = "DB_CMP_CMP_UNIQUE_DELIVERY_RESPONDER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CampaignPerformanceTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignPerformanceTO")
	@TableGenerator(name = "CampaignPerformanceTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Transient
	private String createDateUI;

	@Column(name = "UNIQUE_TARGETED")
	private String uniqueTargeted;

	@Column(name = "UNIQUE_DELIVERED")
	private String uniqueDelivered;

	@Column(name = "UNIQUE_RESPONDED")
	private String uniqueReponded;

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

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public String getUniqueTargeted() {
		return uniqueTargeted;
	}

	public void setUniqueTargeted(String uniqueTargeted) {
		this.uniqueTargeted = uniqueTargeted;
	}

	public String getUniqueDelivered() {
		return uniqueDelivered;
	}

	public void setUniqueDelivered(String uniqueDelivered) {
		this.uniqueDelivered = uniqueDelivered;
	}

	public String getUniqueReponded() {
		return uniqueReponded;
	}

	public void setUniqueReponded(String uniqueReponded) {
		this.uniqueReponded = uniqueReponded;
	}

	@Override
	public String toString() {
		return "CampaignPerformanceTO [id=" + id + ", date=" + date + ", createDateUI=" + createDateUI
				+ ", uniqueTargeted=" + uniqueTargeted + ", uniqueDelivered=" + uniqueDelivered + ", uniqueReponded="
				+ uniqueReponded + "]";
	}

}
