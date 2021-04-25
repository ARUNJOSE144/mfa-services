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
@Table(name = "DB_CMP_ROI_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ROI_Total_RevenueTO {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ROI_Total_RevenueTO")
	@TableGenerator(name = "ROI_Total_RevenueTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "INCRIMENTAL_REVENUE")
	private String incrimentalRevenue;

	@Column(name = "NET_REVENUE")
	private String netRevenue;

	@Column(name = "TOTAL_COST")
	private String totalCost;

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

	public String getIncrimentalRevenue() {
		return incrimentalRevenue;
	}

	public void setIncrimentalRevenue(String incrimentalRevenue) {
		this.incrimentalRevenue = incrimentalRevenue;
	}

	public String getNetRevenue() {
		return netRevenue;
	}

	public void setNetRevenue(String netRevenue) {
		this.netRevenue = netRevenue;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "ROI_Total_RevenueTO [id=" + id + ", date=" + date + ", incrimentalRevenue=" + incrimentalRevenue
				+ ", netRevenue=" + netRevenue + ", totalCost=" + totalCost + ", createDateUI=" + createDateUI + "]";
	}

}
