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
@Table(name = "RPT_MTD_LMTD")
public class MTDLMTDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MTDLMTDTO")
	@TableGenerator(name = "MTDLMTDTO", allocationSize = 1)
	@Column(name = "ID")
	private Integer autoId;
	
	/*@OneToOne
	@JoinColumn(name="CAMP_ID")
	private CampaignDefMasterTO campMasterTO;*/
	
	@Column(name = "CAMP_NAME")
	private String campName;
		
	@Column(name = "CAMPAIGN_CATEGORY")
	private String campCategory;
	
	@Column(name = "BROADCAST_CATEGORY")
	private String broadcastCategory;
	
	@Column(name = "LMTD_TG_SENT")
	private Integer lmtdTGSent;
	
	@Column(name = "LMTD_TG_CONVERSIONS")
	private Integer lmtdTGConversions;
	
	@Column(name = "LMTD_TG_CONV_PERC")
	private Double lmtdTGConvPerc;
	
	@Column(name = "LMTD_CG_SENT")
	private Integer lmtdCGSent;
	
	@Column(name = "LMTD_CG_CONVERSIONS")
	private Integer lmtdCGConversions;
	
	@Column(name = "LMTD_CG_CONV_PERC")
	private Double lmtdCGConvPerc;
	
	@Column(name = "LMTD_NET_CONV_PERC")
	private Integer lmtdNetConvPerc;
	
	@Column(name = "MTD_TG_SENT")
	private Integer mtdTGSent;
	
	@Column(name = "MTD_TG_CONVERSIONS")
	private Integer mtdTGConversions;
	
	@Column(name = "MTD_TG_CONV_PERC")
	private Double mtdTGConvPerc;
	
	@Column(name = "MTD_CG_SENT")
	private Integer mtdCGSent;
	
	@Column(name = "MTD_CG_CONVERSIONS")
	private Integer mtdCGConversions;
	
	@Column(name = "MTD_CG_CONV_PERC")
	private Double mtdCGConvPerc;
	
	@Column(name = "MTD_NET_CONV_PERC")
	private Double mtdNetConvPerc;
	
	@Column(name = "INCREASE_TG_SENT")
	private Double increaseTGSent;
	
	@Column(name = "INCREASE_TG_CONVERSIONS")
	private Double increaseTGConversions;
	
	@Column(name = "INCREASE_CG_SENT")
	private Double increaseCGSent;
	
	@Column(name = "INCREASE_CG_CONVERSIONS")
	private Double increaseCGConversions;
	
	@Column(name = "CIRCLE")
	private String circle;

	public Integer getAutoId() {
		return autoId;
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}

	/*public CampaignDefMasterTO getCampMasterTO() {
		return campMasterTO;
	}

	public void setCampMasterTO(CampaignDefMasterTO campMasterTO) {
		this.campMasterTO = campMasterTO;
	}
*/
	public String getCampCategory() {
		return campCategory;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public void setCampCategory(String campCategory) {
		this.campCategory = campCategory;
	}

	public String getBroadcastCategory() {
		return broadcastCategory;
	}

	public void setBroadcastCategory(String broadcastCategory) {
		this.broadcastCategory = broadcastCategory;
	}

	public Integer getLmtdTGSent() {
		return lmtdTGSent;
	}

	public void setLmtdTGSent(Integer lmtdTGSent) {
		this.lmtdTGSent = lmtdTGSent;
	}

	public Integer getLmtdTGConversions() {
		return lmtdTGConversions;
	}

	public void setLmtdTGConversions(Integer lmtdTGConversions) {
		this.lmtdTGConversions = lmtdTGConversions;
	}

	public Double getLmtdTGConvPerc() {
		return lmtdTGConvPerc;
	}

	public void setLmtdTGConvPerc(Double lmtdTGConvPerc) {
		this.lmtdTGConvPerc = lmtdTGConvPerc;
	}

	public Integer getLmtdCGSent() {
		return lmtdCGSent;
	}

	public void setLmtdCGSent(Integer lmtdCGSent) {
		this.lmtdCGSent = lmtdCGSent;
	}

	public Integer getLmtdCGConversions() {
		return lmtdCGConversions;
	}

	public void setLmtdCGConversions(Integer lmtdCGConversions) {
		this.lmtdCGConversions = lmtdCGConversions;
	}

	public Double getLmtdCGConvPerc() {
		return lmtdCGConvPerc;
	}

	public void setLmtdCGConvPerc(Double lmtdCGConvPerc) {
		this.lmtdCGConvPerc = lmtdCGConvPerc;
	}

	public Integer getLmtdNetConvPerc() {
		return lmtdNetConvPerc;
	}

	public void setLmtdNetConvPerc(Integer lmtdNetConvPerc) {
		this.lmtdNetConvPerc = lmtdNetConvPerc;
	}

	public Integer getMtdTGSent() {
		return mtdTGSent;
	}

	public void setMtdTGSent(Integer mtdTGSent) {
		this.mtdTGSent = mtdTGSent;
	}

	public Integer getMtdTGConversions() {
		return mtdTGConversions;
	}

	public void setMtdTGConversions(Integer mtdTGConversions) {
		this.mtdTGConversions = mtdTGConversions;
	}

	public Double getMtdTGConvPerc() {
		return mtdTGConvPerc;
	}

	public void setMtdTGConvPerc(Double mtdTGConvPerc) {
		this.mtdTGConvPerc = mtdTGConvPerc;
	}

	public Integer getMtdCGSent() {
		return mtdCGSent;
	}

	public void setMtdCGSent(Integer mtdCGSent) {
		this.mtdCGSent = mtdCGSent;
	}

	public Integer getMtdCGConversions() {
		return mtdCGConversions;
	}

	public void setMtdCGConversions(Integer mtdCGConversions) {
		this.mtdCGConversions = mtdCGConversions;
	}

	public Double getMtdCGConvPerc() {
		return mtdCGConvPerc;
	}

	public void setMtdCGConvPerc(Double mtdCGConvPerc) {
		this.mtdCGConvPerc = mtdCGConvPerc;
	}

	public Double getMtdNetConvPerc() {
		return mtdNetConvPerc;
	}

	public void setMtdNetConvPerc(Double mtdNetConvPerc) {
		this.mtdNetConvPerc = mtdNetConvPerc;
	}

	public Double getIncreaseTGSent() {
		return increaseTGSent;
	}

	public void setIncreaseTGSent(Double increaseTGSent) {
		this.increaseTGSent = increaseTGSent;
	}

	public Double getIncreaseTGConversions() {
		return increaseTGConversions;
	}

	public void setIncreaseTGConversions(Double increaseTGConversions) {
		this.increaseTGConversions = increaseTGConversions;
	}

	public Double getIncreaseCGSent() {
		return increaseCGSent;
	}

	public void setIncreaseCGSent(Double increaseCGSent) {
		this.increaseCGSent = increaseCGSent;
	}

	public Double getIncreaseCGConversions() {
		return increaseCGConversions;
	}

	public void setIncreaseCGConversions(Double increaseCGConversions) {
		this.increaseCGConversions = increaseCGConversions;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	
	
	
}
