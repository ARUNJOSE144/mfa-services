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
@Table(name = "DB_CMP_TOP_FIVE_CMP_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Top5CampaignTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Top5CampaignTO")
	@TableGenerator(name = "Top5CampaignTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "CAMPAIGN_NAME")
	private String campaignName;

	@Column(name = "NRR_VALUE")
	private String nrrVlaue;

	@Column(name = "DELIVERED_COUNT")
	private String deliveredCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getNrrVlaue() {
		return nrrVlaue;
	}

	public void setNrrVlaue(String nrrVlaue) {
		this.nrrVlaue = nrrVlaue;
	}

	public String getDeliveredCount() {
		return deliveredCount;
	}

	public void setDeliveredCount(String deliveredCount) {
		this.deliveredCount = deliveredCount;
	}

	@Override
	public String toString() {
		return "Top5CampaignTO [id=" + id + ", campaignName=" + campaignName + ", nrrVlaue=" + nrrVlaue
				+ ", deliveredCount=" + deliveredCount + "]";
	}

}
