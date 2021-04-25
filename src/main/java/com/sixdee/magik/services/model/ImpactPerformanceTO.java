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
@Table(name = "RPT_IMPACT_PERFORMANCE")
public class ImpactPerformanceTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ImpactPerformanceTO")
	@TableGenerator(name = "ImpactPerformanceTO", allocationSize = 1)
	@Column(name = "ID")
	private Integer autoId;
	
	/*@OneToOne
	@JoinColumn(name="CAMP_ID")
	private CampaignDefMasterTO campMasterTO;*/
	
	@Column(name = "CAMP_NAME")
	private String campName;
		
	@Column(name = "TG")
	private Integer tg;
	
	@Column(name = "TG_CONV")
	private Integer tgConv;
	
	@Column(name = "TG_CONV_PERC")
	private Integer tgConvPerc;
	
	@Column(name = "CG")
	private Integer cg;
	
	@Column(name = "CG_CONV")
	private Integer cgConv;
	
	@Column(name = "CG_CONV_PERC")
	private Double cgConvPerc;
	
	@Column(name = "NET_CONV_PERC")
	private Integer netConvPerc;
	
	@Column(name = "DOUBLE_DELTA_ARPU")
	private Double doubleDeltaArpu;
	
	@Column(name = "IMPACT_ARPU")
	private Integer impactArpu;
	
	@Column(name = "DOUBLE_DELTA_VOICE_ARPU")
	private Double doubleDeltaVoiceArpu;
	
	@Column(name = "IMPACT_VOICE_ARPU")
	private Integer impactVoiceArpu;
	
	@Column(name = "DOUBLE_DELTA_DATA_ARPU")
	private Double doubleDeltadataArpu;
	
	@Column(name = "IMPACT_DATA_ARPU")
	private Integer impactDataArpu;
	
	@Column(name = "DOUBLE_DELTA_BUNDLE_ARPU")
	private Double doubleDeltaBundleArpu;
	
	@Column(name = "IMPACT_BUNDLE_ARPU")
	private Integer impactBundleArpu;
	
	@Column(name = "DOUBLE_DELTA_VAS_ARPU")
	private Double doubleDeltaVasArpu;
	
	@Column(name = "IMPACT_VAS_ARPU")
	private Integer impactVasArpu;
	
	@Column(name = "DOUBLE_DELTA_ONNET_ARPU")
	private Double doubleDeltaOnnetArpu;
	
	@Column(name = "IMPACT_ONNET_ARPU")
	private Integer impactOnnetArpu;
	
	@Column(name = "DOUBLE_DELTA_ONNET_MOU")
	private Double doubleDeltaOnnetMou;
	
	@Column(name = "IMPACT_ONNET_MOU")
	private Integer impactOnnetMou;
	
	@Column(name = "DOUBLE_DELTA_OFFNET_ARPU")
	private Double doubleDeltaOffnetArpu;
	
	
	@Column(name = "IMPACT_OFFNET_ARPU")
	private Integer impactOffnetArpu;
	
	@Column(name = "DOUBLE_DELTA_OFFNET_MOU")
	private Double doubleDeltaOffnetMou;
	
	@Column(name = "IMPACT_OFFNET_MOU")
	private Integer impactOffnetMou;
	
	@Column(name = "DOUBLE_DELTA_DATA_USAGE")
	private Double doubleDeltaDataUsage;
	
	@Column(name = "IMPACT_DATA_USAGE")
	private Integer impactDataUsage;

	@Column(name = "DOUBLE_DELTA_REG_DAYS")
	private Double doubleDeltaRegDays;
	
	@Column(name = "IMPACT_REG_DAYS")
	private Integer impactRegDays;
	
	@Column(name = "DOUBLE_DELTA_RECHARGE")
	private Double doubleDeltaRecharge;
	
	@Column(name = "IMPACT_RECHARGE")
	private Integer impactRecharge;
	
	@Column(name = "MONTH")
	private String month;

	public Integer getAutoId() {
		return autoId;
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}

	public Integer getTg() {
		return tg;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public void setTg(Integer tg) {
		this.tg = tg;
	}

	public Integer getTgConv() {
		return tgConv;
	}

	public void setTgConv(Integer tgConv) {
		this.tgConv = tgConv;
	}

	public Integer getTgConvPerc() {
		return tgConvPerc;
	}

	public void setTgConvPerc(Integer tgConvPerc) {
		this.tgConvPerc = tgConvPerc;
	}

	public Integer getCg() {
		return cg;
	}

	public void setCg(Integer cg) {
		this.cg = cg;
	}

	public Integer getCgConv() {
		return cgConv;
	}

	public void setCgConv(Integer cgConv) {
		this.cgConv = cgConv;
	}

	public Double getCgConvPerc() {
		return cgConvPerc;
	}

	public void setCgConvPerc(Double cgConvPerc) {
		this.cgConvPerc = cgConvPerc;
	}

	public Integer getNetConvPerc() {
		return netConvPerc;
	}

	public void setNetConvPerc(Integer netConvPerc) {
		this.netConvPerc = netConvPerc;
	}

	public Double getDoubleDeltaArpu() {
		return doubleDeltaArpu;
	}

	public void setDoubleDeltaArpu(Double doubleDeltaArpu) {
		this.doubleDeltaArpu = doubleDeltaArpu;
	}

	public Integer getImpactArpu() {
		return impactArpu;
	}

	public void setImpactArpu(Integer impactArpu) {
		this.impactArpu = impactArpu;
	}

	public Double getDoubleDeltaVoiceArpu() {
		return doubleDeltaVoiceArpu;
	}

	public void setDoubleDeltaVoiceArpu(Double doubleDeltaVoiceArpu) {
		this.doubleDeltaVoiceArpu = doubleDeltaVoiceArpu;
	}

	public Integer getImpactVoiceArpu() {
		return impactVoiceArpu;
	}

	public void setImpactVoiceArpu(Integer impactVoiceArpu) {
		this.impactVoiceArpu = impactVoiceArpu;
	}

	public Double getDoubleDeltadataArpu() {
		return doubleDeltadataArpu;
	}

	public void setDoubleDeltadataArpu(Double doubleDeltadataArpu) {
		this.doubleDeltadataArpu = doubleDeltadataArpu;
	}

	public Integer getImpactDataArpu() {
		return impactDataArpu;
	}

	public void setImpactDataArpu(Integer impactDataArpu) {
		this.impactDataArpu = impactDataArpu;
	}

	public Double getDoubleDeltaBundleArpu() {
		return doubleDeltaBundleArpu;
	}

	public void setDoubleDeltaBundleArpu(Double doubleDeltaBundleArpu) {
		this.doubleDeltaBundleArpu = doubleDeltaBundleArpu;
	}

	public Integer getImpactBundleArpu() {
		return impactBundleArpu;
	}

	public void setImpactBundleArpu(Integer impactBundleArpu) {
		this.impactBundleArpu = impactBundleArpu;
	}

	public Double getDoubleDeltaVasArpu() {
		return doubleDeltaVasArpu;
	}

	public void setDoubleDeltaVasArpu(Double doubleDeltaVasArpu) {
		this.doubleDeltaVasArpu = doubleDeltaVasArpu;
	}

	public Integer getImpactVasArpu() {
		return impactVasArpu;
	}

	public void setImpactVasArpu(Integer impactVasArpu) {
		this.impactVasArpu = impactVasArpu;
	}

	public Double getDoubleDeltaOnnetArpu() {
		return doubleDeltaOnnetArpu;
	}

	public void setDoubleDeltaOnnetArpu(Double doubleDeltaOnnetArpu) {
		this.doubleDeltaOnnetArpu = doubleDeltaOnnetArpu;
	}

	public Integer getImpactOnnetArpu() {
		return impactOnnetArpu;
	}

	public void setImpactOnnetArpu(Integer impactOnnetArpu) {
		this.impactOnnetArpu = impactOnnetArpu;
	}

	public Double getDoubleDeltaOnnetMou() {
		return doubleDeltaOnnetMou;
	}

	public void setDoubleDeltaOnnetMou(Double doubleDeltaOnnetMou) {
		this.doubleDeltaOnnetMou = doubleDeltaOnnetMou;
	}

	public Integer getImpactOnnetMou() {
		return impactOnnetMou;
	}

	public void setImpactOnnetMou(Integer impactOnnetMou) {
		this.impactOnnetMou = impactOnnetMou;
	}

	public Double getDoubleDeltaOffnetArpu() {
		return doubleDeltaOffnetArpu;
	}

	public void setDoubleDeltaOffnetArpu(Double doubleDeltaOffnetArpu) {
		this.doubleDeltaOffnetArpu = doubleDeltaOffnetArpu;
	}

	public Integer getImpactOffnetArpu() {
		return impactOffnetArpu;
	}

	public void setImpactOffnetArpu(Integer impactOffnetArpu) {
		this.impactOffnetArpu = impactOffnetArpu;
	}

	public Double getDoubleDeltaOffnetMou() {
		return doubleDeltaOffnetMou;
	}

	public void setDoubleDeltaOffnetMou(Double doubleDeltaOffnetMou) {
		this.doubleDeltaOffnetMou = doubleDeltaOffnetMou;
	}

	public Integer getImpactOffnetMou() {
		return impactOffnetMou;
	}

	public void setImpactOffnetMou(Integer impactOffnetMou) {
		this.impactOffnetMou = impactOffnetMou;
	}

	public Double getDoubleDeltaDataUsage() {
		return doubleDeltaDataUsage;
	}

	public void setDoubleDeltaDataUsage(Double doubleDeltaDataUsage) {
		this.doubleDeltaDataUsage = doubleDeltaDataUsage;
	}

	public Integer getImpactDataUsage() {
		return impactDataUsage;
	}

	public void setImpactDataUsage(Integer impactDataUsage) {
		this.impactDataUsage = impactDataUsage;
	}

	public Double getDoubleDeltaRegDays() {
		return doubleDeltaRegDays;
	}

	public void setDoubleDeltaRegDays(Double doubleDeltaRegDays) {
		this.doubleDeltaRegDays = doubleDeltaRegDays;
	}

	public Integer getImpactRegDays() {
		return impactRegDays;
	}

	public void setImpactRegDays(Integer impactRegDays) {
		this.impactRegDays = impactRegDays;
	}

	public Double getDoubleDeltaRecharge() {
		return doubleDeltaRecharge;
	}

	public void setDoubleDeltaRecharge(Double doubleDeltaRecharge) {
		this.doubleDeltaRecharge = doubleDeltaRecharge;
	}

	public Integer getImpactRecharge() {
		return impactRecharge;
	}

	public void setImpactRecharge(Integer impactRecharge) {
		this.impactRecharge = impactRecharge;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	
	
}
