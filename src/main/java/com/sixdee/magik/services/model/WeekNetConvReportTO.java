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
@Table(name = "RPT_WEEKON_NET_BASE")
public class WeekNetConvReportTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WeekNetConvReportTO")
	@TableGenerator(name = "WeekNetConvReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "NetConversion")
	private String netconversion;
	
	
	@Column(name = "WEEK1")
	private Double week1;
	
	
	@Column(name = "WEEK2")
	private Double week2;
	
	@Column(name = "WEEK3")
	private Double week3;
	
	@Column(name = "WEEK4")
	private Double week4;
	
	@Column(name = "W2W1")
	private Double w2w1;
	
	@Column(name = "W3W2")
	private Double w3w2;
	
	@Column(name = "W4W3")
	private Double w4w3;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getNetconversion() {
		return netconversion;
	}

	public void setNetconversion(String netconversion) {
		this.netconversion = netconversion;
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

	public Double getW2w1() {
		return w2w1;
	}

	public void setW2w1(Double w2w1) {
		this.w2w1 = w2w1;
	}

		public Double getW3w2() {
		return w3w2;
	}

	public void setW3w2(Double w3w2) {
		this.w3w2 = w3w2;
	}

	public Double getW4w3() {
		return w4w3;
	}

	public void setW4w3(Double w4w3) {
		this.w4w3 = w4w3;
	}
	

}
