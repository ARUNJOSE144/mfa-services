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
@Table(name = "RPT_REGIONWISE_ROI")
public class RegionwiseROITO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RegionwiseROITO")
	@TableGenerator(name = "RegionwiseROITO", allocationSize = 1)
	@Column(name = "ID")
	private Integer autoId;
		
	@Column(name = "GROSS_ARPU_USD")
	private Double grossArpuUsd;
	
	@Column(name = "NET_ARPU_USD")
	private Double netArpuUsd;
	
	@Column(name = "SPEND_USD")
	private Double spendUsd;
	
	@Column(name = "TOTAL_OG_MOU")
	private Double totalOGMou;
	
	@Column(name = "TOTAL_OG_REV")
	private Double totalOGRev;
	
	@Column(name = "VOICE_DOU")
	private Double voiceDou;
	
	@Column(name = "DATA_MB")
	private Double dataMB;
	
	@Column(name = "DATA_ARPU")
	private Double dataArpu;
	
	@Column(name = "RECHARGES")
	private Double recharges;
	
	@Column(name = "CG_PERC")
	private Double cgPerc;
	
	@Column(name = "CONV_PERC")
	private Double convPerc;
		
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

	public Double getGrossArpuUsd() {
		return grossArpuUsd;
	}

	public void setGrossArpuUsd(Double grossArpuUsd) {
		this.grossArpuUsd = grossArpuUsd;
	}

	public Double getNetArpuUsd() {
		return netArpuUsd;
	}

	public void setNetArpuUsd(Double netArpuUsd) {
		this.netArpuUsd = netArpuUsd;
	}

	public Double getSpendUsd() {
		return spendUsd;
	}

	public void setSpendUsd(Double spendUsd) {
		this.spendUsd = spendUsd;
	}

	public Double getTotalOGMou() {
		return totalOGMou;
	}

	public void setTotalOGMou(Double totalOGMou) {
		this.totalOGMou = totalOGMou;
	}

	public Double getTotalOGRev() {
		return totalOGRev;
	}

	public void setTotalOGRev(Double totalOGRev) {
		this.totalOGRev = totalOGRev;
	}

	public Double getVoiceDou() {
		return voiceDou;
	}

	public void setVoiceDou(Double voiceDou) {
		this.voiceDou = voiceDou;
	}

	public Double getDataMB() {
		return dataMB;
	}

	public void setDataMB(Double dataMB) {
		this.dataMB = dataMB;
	}

	public Double getDataArpu() {
		return dataArpu;
	}

	public void setDataArpu(Double dataArpu) {
		this.dataArpu = dataArpu;
	}

	public Double getRecharges() {
		return recharges;
	}

	public void setRecharges(Double recharges) {
		this.recharges = recharges;
	}

	public Double getCgPerc() {
		return cgPerc;
	}

	public void setCgPerc(Double cgPerc) {
		this.cgPerc = cgPerc;
	}

	public Double getConvPerc() {
		return convPerc;
	}

	public void setConvPerc(Double convPerc) {
		this.convPerc = convPerc;
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
