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
@Table(name = "RPT_INCREMENTAL_REVENUE")
public class IncrementalRevenueTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "IncrementalRevenueTO")
	@TableGenerator(name = "IncrementalRevenueTO", allocationSize = 1)
	@Column(name = "ID")
	private Integer autoId;
	
	@Column(name="ARPU")
	private String arpu;
		
	@Column(name = "TOTAL_SENT")
	private Integer totalSent;
	
	@Column(name = "UNIQUE_TARGETTED_SUBS")
	private Integer uniqueTargettedSubs;
	
	@Column(name = "SMS_SENT_TARGET_SUBS")
	private Double smsSentTargetSubs;
	
	@Column(name = "TOTAL_CONVERTED")
	private Integer totalConverted;
	
	@Column(name = "UNIQUE_CONVERTED")
	private Integer uniqueConverted;
	
	@Column(name = "CONV_PERC")
	private Double convPerc;
	
	@Column(name = "CONV_PERC_UNIQUE_TG_SUBS")
	private Double convPercUniqueTGSubs;
	
	@Column(name = "INCREMENTAL_REVENUE")
	private Integer incrementalRevenue;
	
	@Column(name = "INCREMENTAL_REVENUE_PER_SUBS")
	private Integer incrementalRevenuePerSubs;
	
	@Column(name = "MONTH")
	private String month;
	
	@Column(name = "SEGMENT")
	private String segment;

	public Integer getAutoId() {
		return autoId;
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}

	public String getArpu() {
		return arpu;
	}

	public void setArpu(String arpu) {
		this.arpu = arpu;
	}

	public Integer getTotalSent() {
		return totalSent;
	}

	public void setTotalSent(Integer totalSent) {
		this.totalSent = totalSent;
	}

	public Integer getUniqueTargettedSubs() {
		return uniqueTargettedSubs;
	}

	public void setUniqueTargettedSubs(Integer uniqueTargettedSubs) {
		this.uniqueTargettedSubs = uniqueTargettedSubs;
	}

	public Double getSmsSentTargetSubs() {
		return smsSentTargetSubs;
	}

	public void setSmsSentTargetSubs(Double smsSentTargetSubs) {
		this.smsSentTargetSubs = smsSentTargetSubs;
	}

	public Integer getTotalConverted() {
		return totalConverted;
	}

	public void setTotalConverted(Integer totalConverted) {
		this.totalConverted = totalConverted;
	}

	public Integer getUniqueConverted() {
		return uniqueConverted;
	}

	public void setUniqueConverted(Integer uniqueConverted) {
		this.uniqueConverted = uniqueConverted;
	}

	public Double getConvPerc() {
		return convPerc;
	}

	public void setConvPerc(Double convPerc) {
		this.convPerc = convPerc;
	}

	public Double getConvPercUniqueTGSubs() {
		return convPercUniqueTGSubs;
	}

	public void setConvPercUniqueTGSubs(Double convPercUniqueTGSubs) {
		this.convPercUniqueTGSubs = convPercUniqueTGSubs;
	}

	public Integer getIncrementalRevenue() {
		return incrementalRevenue;
	}

	public void setIncrementalRevenue(Integer incrementalRevenue) {
		this.incrementalRevenue = incrementalRevenue;
	}

	public Integer getIncrementalRevenuePerSubs() {
		return incrementalRevenuePerSubs;
	}

	public void setIncrementalRevenuePerSubs(Integer incrementalRevenuePerSubs) {
		this.incrementalRevenuePerSubs = incrementalRevenuePerSubs;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}
		
	
	
}
