package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "RPT_SHORT_SUMMARY")
public class ShortSummaryReportTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ShortSummaryReportTO")
	@TableGenerator(name = "ShortSummaryReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "CAMPAIGN")
	private String campaign;
	
	
	@Column(name = "WEEK1")
	private Double week1;
	
	
	@Column(name = "WEEK2")
	private Double week2;
	
	@Column(name = "WEEK3")
	private Double week3;
	
	@Column(name = "WEEK4")
	private Double week4;
	
	@Column(name = "CONV_W1")
	private Double conv_w1;
	
	@Column(name = "CONV_PERC_W1")
	private Double conv_per_w1;
	
	@Column(name = "CONV_W2")
	private Double conv_w2;
	
	@Column(name = "CONV_PERC_W2")
	private Double conv_per_w2;
	
	@Column(name = "CONV_W3")
	private Double conv_w3;
	
	@Column(name = "CONV_PERC_W3")
	private Double conv_per_w3;
	
	
	@Column(name = "CONV_W4")
	private Double conv_w4;
	
	@Column(name = "CONV_PERC_W4")
	private Double conv_per_w4;
	
	@Column(name = "WEEK1_NC")
	private Double week1_nc;
	
	  @Column(name = "WEEK2_NC")
	  private Double week2_nc;
	  
	  
	  @Column(name = "WEEK3_NC")
	  private Double week3_nc;
	  
	  
	  @Column(name = "WEEK4_NC")
	  private Double week4_nc;


	public int getAutoId() {
		return autoId;
	}


	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}


	public String getCampaign() {
		return campaign;
	}


	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public Double getWeek1() {
		return week1;
	}


	public void setWeek1(Double week1) {
		this.week1 = week1;
	}


	public Double getWeek2() {
		return week2;
	}


	public void setWeek2(Double week2) {
		this.week2 = week2;
	}


	public Double getWeek3() {
		return week3;
	}


	public void setWeek3(Double week3) {
		this.week3 = week3;
	}


	public Double getWeek4() {
		return week4;
	}


	public void setWeek4(Double week4) {
		this.week4 = week4;
	}


	public Double getConv_w1() {
		return conv_w1;
	}


	public void setConv_w1(Double conv_w1) {
		this.conv_w1 = conv_w1;
	}


	public Double getConv_per_w1() {
		return conv_per_w1;
	}


	public void setConv_per_w1(Double conv_per_w1) {
		this.conv_per_w1 = conv_per_w1;
	}


	public Double getConv_w2() {
		return conv_w2;
	}


	public void setConv_w2(Double conv_w2) {
		this.conv_w2 = conv_w2;
	}


	public Double getConv_per_w2() {
		return conv_per_w2;
	}


	public void setConv_per_w2(Double conv_per_w2) {
		this.conv_per_w2 = conv_per_w2;
	}


	public Double getConv_w3() {
		return conv_w3;
	}


	public void setConv_w3(Double conv_w3) {
		this.conv_w3 = conv_w3;
	}


	public Double getConv_per_w3() {
		return conv_per_w3;
	}


	public void setConv_per_w3(Double conv_per_w3) {
		this.conv_per_w3 = conv_per_w3;
	}


	public Double getConv_w4() {
		return conv_w4;
	}


	public void setConv_w4(Double conv_w4) {
		this.conv_w4 = conv_w4;
	}


	public Double getConv_per_w4() {
		return conv_per_w4;
	}


	public void setConv_per_w4(Double conv_per_w4) {
		this.conv_per_w4 = conv_per_w4;
	}


	public Double getWeek1_nc() {
		return week1_nc;
	}


	public void setWeek1_nc(Double week1_nc) {
		this.week1_nc = week1_nc;
	}


	public Double getWeek2_nc() {
		return week2_nc;
	}


	public void setWeek2_nc(Double week2_nc) {
		this.week2_nc = week2_nc;
	}


	public Double getWeek3_nc() {
		return week3_nc;
	}


	public void setWeek3_nc(Double week3_nc) {
		this.week3_nc = week3_nc;
	}


	public Double getWeek4_nc() {
		return week4_nc;
	}


	public void setWeek4_nc(Double week4_nc) {
		this.week4_nc = week4_nc;
	}
	

}
