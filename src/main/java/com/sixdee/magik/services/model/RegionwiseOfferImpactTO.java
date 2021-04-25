package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@SuppressWarnings("serial")
@Entity
@Table(name = "RPT_REGIONWISE_OFFER_IMPACT")
public class RegionwiseOfferImpactTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RegionwiseOfferImpactTO")
	@TableGenerator(name = "RegionwiseOfferImpactTO", allocationSize = 1)
	@Column(name = "ID")
	private Integer autoId;
		
	@Column(name = "USD")
	private Integer usd;
	
	@Column(name = "COUNT")
	private Integer count;
	
	@Column(name = "NET_ARPU_PRE")
	private Integer netArpuPre;
	
	@Column(name = "NET_ARPU_POST")
	private Integer netArpuPost;
	
	@Column(name = "NET_ARPU_GROWTH")
	private Double netArpuGrowth;
	
	@Column(name = "DECREMENT_PRE")
	private Integer decrementPre;
	
	@Column(name = "DECREMENT_POST")
	private Integer decrementPost;
	
	@Column(name = "DECREMENT_GROWTH")
	private Double decrementGrowth;
	
	@Column(name = "OG_MOU_PRE")
	private Integer ogMouPre;
	
	@Column(name = "OG_MOU_POST")
	private Integer ogMouPost;
	
	@Column(name = "OG_MOU_GROWTH")
	private Double ogMouGrowth;
	
	@Column(name = "DATA_USAGE_PRE")
	private Integer dataUsagePre;
	
	@Column(name = "DATA_USAGE_POST")
	private Integer dataUsagePost;
	
	@Column(name = "DATA_USAGE_GROWTH")
	private Double dataUsageGrowth;
	
	@Column(name = "MONTH")
	private String month;
	
	@Column(name = "CIRCLE")
	private String circle;

	public Integer getAutoId() {
		return autoId;
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}

	public Integer getUsd() {
		return usd;
	}

	public void setUsd(Integer usd) {
		this.usd = usd;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getNetArpuPre() {
		return netArpuPre;
	}

	public void setNetArpuPre(Integer netArpuPre) {
		this.netArpuPre = netArpuPre;
	}

	public Integer getNetArpuPost() {
		return netArpuPost;
	}

	public void setNetArpuPost(Integer netArpuPost) {
		this.netArpuPost = netArpuPost;
	}

	public Double getNetArpuGrowth() {
		return netArpuGrowth;
	}

	public void setNetArpuGrowth(Double netArpuGrowth) {
		this.netArpuGrowth = netArpuGrowth;
	}

	public Integer getDecrementPre() {
		return decrementPre;
	}

	public void setDecrementPre(Integer decrementPre) {
		this.decrementPre = decrementPre;
	}

	public Integer getDecrementPost() {
		return decrementPost;
	}

	public void setDecrementPost(Integer decrementPost) {
		this.decrementPost = decrementPost;
	}

	public Double getDecrementGrowth() {
		return decrementGrowth;
	}

	public void setDecrementGrowth(Double decrementGrowth) {
		this.decrementGrowth = decrementGrowth;
	}

	public Integer getOgMouPre() {
		return ogMouPre;
	}

	public void setOgMouPre(Integer ogMouPre) {
		this.ogMouPre = ogMouPre;
	}

	public Integer getOgMouPost() {
		return ogMouPost;
	}

	public void setOgMouPost(Integer ogMouPost) {
		this.ogMouPost = ogMouPost;
	}

	public Double getOgMouGrowth() {
		return ogMouGrowth;
	}

	public void setOgMouGrowth(Double ogMouGrowth) {
		this.ogMouGrowth = ogMouGrowth;
	}

	public Integer getDataUsagePre() {
		return dataUsagePre;
	}

	public void setDataUsagePre(Integer dataUsagePre) {
		this.dataUsagePre = dataUsagePre;
	}

	public Integer getDataUsagePost() {
		return dataUsagePost;
	}

	public void setDataUsagePost(Integer dataUsagePost) {
		this.dataUsagePost = dataUsagePost;
	}

	public Double getDataUsageGrowth() {
		return dataUsageGrowth;
	}

	public void setDataUsageGrowth(Double dataUsageGrowth) {
		this.dataUsageGrowth = dataUsageGrowth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	
	
	
}
