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
@Table(name = "DB_CMP_OVERALL_CMP_PUSH_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OverAllCampaignPushTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "OverAllCampaignPushTO")
	@TableGenerator(name = "OverAllCampaignPushTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "PUSHED_COUNT")
	private String pushedCount;

	@Column(name = "DELIVERED_COUNT")
	private String deliveredCount;

	@Column(name = "DELIVERY_PERCENTAGE")
	private String percentage;
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

	public String getPushedCount() {
		return pushedCount;
	}

	public void setPushedCount(String pushedCount) {
		this.pushedCount = pushedCount;
	}

	public String getDeliveredCount() {
		return deliveredCount;
	}

	public void setDeliveredCount(String deliveredCount) {
		this.deliveredCount = deliveredCount;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "OverAllCampaignPushTO [id=" + id + ", date=" + date + ", pushedCount=" + pushedCount
				+ ", deliveredCount=" + deliveredCount + ", percentage=" + percentage + ", createDateUI=" + createDateUI
				+ "]";
	}

}
