package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@Entity
@Table(name = "PRE_POST_ANALYTICS_REPORTS")
@SuppressWarnings("serial")
public class PrePostAnalyticsReportsTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PrePostAnalyticsReportsTO")
	@TableGenerator(name = "PrePostAnalyticsReportsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "CAMPAIGN_NAME")
	private String campaignName;

	@Column(name = "CAMPAIGN_TYPE")
	private String campaignType;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DATE_FIELD")
	private String dateField;

	@Column(name = "TG_COUNT")
	private String tgCount;

	@Column(name = "CG_COUNT")
	private String cgCount;

	@Column(name = "CAMPAIGN_ID")
	private int campaignId;

	@Column(name = "CAMPAIGN_TYPE_ID")
	private int campaignTypeId;

	@Column(name = "KPI")
	private String kpi;

	@Column(name = "TG_CONVERSION_COUNT")
	private String tgConversionCount;

	@Column(name = "CG_CONVERSION_COUNT")
	private String cgConversionCount;

	@Column(name = "POST_TG_COUNT")
	private String postTG;

	@Column(name = "POST_CG_COUNT")
	private String postCG;

	@Column(name = "POST_TG_CONVERSION_COUNT")
	private String postTGConversion;

	@Column(name = "POST_CG_CONVERSION_COUNT")
	private String postCGConversion;

	@Transient
	private int selectedKpiId;

	/*
	 * @Transient private Date campaignPeriod;
	 * 
	 * @Transient private Date preAnalysisPeriod;
	 */
	@Transient
	private Date campaignStartDate;

	@Transient
	private Date preAnalysisStartDate;

	@Transient
	private Date preAnalysisEndDate;

	@Transient
	private Date postAnalysisStartDate;

	@Transient
	private Date postAnalysisEndDate;

	@Transient
	private ArrayList<PrePostAnalyticsDataTO> analyticsDataList;

	@Transient
	private LinkedHashMap<String, String> preDataMap;

	@Transient
	private LinkedHashMap<String, String> postDataMap;

	@Transient
	private LinkedHashMap<String, String> totalDataMap;

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

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateField() {
		return dateField;
	}

	public void setDateField(String dateField) {
		this.dateField = dateField;
	}

	public String getTgCount() {
		return tgCount;
	}

	public void setTgCount(String tgCount) {
		this.tgCount = tgCount;
	}

	public String getCgCount() {
		return cgCount;
	}

	public void setCgCount(String cgCount) {
		this.cgCount = cgCount;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public int getCampaignTypeId() {
		return campaignTypeId;
	}

	public void setCampaignTypeId(int campaignTypeId) {
		this.campaignTypeId = campaignTypeId;
	}

	/*
	 * public Date getCampaignPeriod() { return campaignPeriod; }
	 * 
	 * public void setCampaignPeriod(Date campaignPeriod) { this.campaignPeriod =
	 * campaignPeriod; }
	 * 
	 * public Date getPreAnalysisPeriod() { return preAnalysisPeriod; }
	 * 
	 * public void setPreAnalysisPeriod(Date preAnalysisPeriod) {
	 * this.preAnalysisPeriod = preAnalysisPeriod; }
	 */

	public Date getCampaignStartDate() {
		return campaignStartDate;
	}

	public void setCampaignStartDate(Date campaignStartDate) {
		this.campaignStartDate = campaignStartDate;
	}

	public ArrayList<PrePostAnalyticsDataTO> getAnalyticsDataList() {
		return analyticsDataList;
	}

	public void setAnalyticsDataList(ArrayList<PrePostAnalyticsDataTO> analyticsDataList) {
		this.analyticsDataList = analyticsDataList;
	}

	public Date getPreAnalysisStartDate() {
		return preAnalysisStartDate;
	}

	public void setPreAnalysisStartDate(Date preAnalysisStartDate) {
		this.preAnalysisStartDate = preAnalysisStartDate;
	}

	public Date getPreAnalysisEndDate() {
		return preAnalysisEndDate;
	}

	public void setPreAnalysisEndDate(Date preAnalysisEndDate) {
		this.preAnalysisEndDate = preAnalysisEndDate;
	}

	public Date getPostAnalysisStartDate() {
		return postAnalysisStartDate;
	}

	public void setPostAnalysisStartDate(Date postAnalysisStartDate) {
		this.postAnalysisStartDate = postAnalysisStartDate;
	}

	public Date getPostAnalysisEndDate() {
		return postAnalysisEndDate;
	}

	public void setPostAnalysisEndDate(Date postAnalysisEndDate) {
		this.postAnalysisEndDate = postAnalysisEndDate;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public LinkedHashMap<String, String> getPreDataMap() {
		return preDataMap;
	}

	public void setPreDataMap(LinkedHashMap<String, String> preDataMap) {
		this.preDataMap = preDataMap;
	}

	public LinkedHashMap<String, String> getPostDataMap() {
		return postDataMap;
	}

	public void setPostDataMap(LinkedHashMap<String, String> postDataMap) {
		this.postDataMap = postDataMap;
	}

	public LinkedHashMap<String, String> getTotalDataMap() {
		return totalDataMap;
	}

	public void setTotalDataMap(LinkedHashMap<String, String> totalDataMap) {
		this.totalDataMap = totalDataMap;
	}

	public int getSelectedKpiId() {
		return selectedKpiId;
	}

	public void setSelectedKpiId(int selectedKpiId) {
		this.selectedKpiId = selectedKpiId;
	}

	public String getTgConversionCount() {
		return tgConversionCount;
	}

	public void setTgConversionCount(String tgConversionCount) {
		this.tgConversionCount = tgConversionCount;
	}

	public String getCgConversionCount() {
		return cgConversionCount;
	}

	public void setCgConversionCount(String cgConversionCount) {
		this.cgConversionCount = cgConversionCount;
	}

	public String getPostTG() {
		return postTG;
	}

	public void setPostTG(String postTG) {
		this.postTG = postTG;
	}

	public String getPostCG() {
		return postCG;
	}

	public void setPostCG(String postCG) {
		this.postCG = postCG;
	}

	public String getPostTGConversion() {
		return postTGConversion;
	}

	public void setPostTGConversion(String postTGConversion) {
		this.postTGConversion = postTGConversion;
	}

	public String getPostCGConversion() {
		return postCGConversion;
	}

	public void setPostCGConversion(String postCGConversion) {
		this.postCGConversion = postCGConversion;
	}

	@Override
	public String toString() {
		return "PrePostAnalyticsReportsTO [id=" + id + ", campaignName=" + campaignName + ", campaignType="
				+ campaignType + ", status=" + status + ", dateField=" + dateField + ", tgCount=" + tgCount
				+ ", cgCount=" + cgCount + ", campaignId=" + campaignId + ", campaignTypeId=" + campaignTypeId
				+ ", kpi=" + kpi + ", tgConversionCount=" + tgConversionCount + ", cgConversionCount="
				+ cgConversionCount + ", postTG=" + postTG + ", postCG=" + postCG + ", postTGConversion="
				+ postTGConversion + ", postCGConversion=" + postCGConversion + ", selectedKpiId=" + selectedKpiId
				+ ", campaignStartDate=" + campaignStartDate + ", preAnalysisStartDate=" + preAnalysisStartDate
				+ ", preAnalysisEndDate=" + preAnalysisEndDate + ", postAnalysisStartDate=" + postAnalysisStartDate
				+ ", postAnalysisEndDate=" + postAnalysisEndDate + ", analyticsDataList=" + analyticsDataList
				+ ", preDataMap=" + preDataMap + ", postDataMap=" + postDataMap + ", totalDataMap=" + totalDataMap
				+ "]";
	}

}
