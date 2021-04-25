package com.sixdee.magik.services.model;

import java.io.Serializable;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
public class PrePostAnalyticsDataTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String type;
	private String cgCount;
	private String tgCount;
	private String cgConversionCount;
	private String tgConversionCount;
	private String postcgCount;
	private String posttgCount;
	private String postcgConversionCount;
	private String posttgConversionCount;
	private String color;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCgCount() {
		return cgCount;
	}

	public void setCgCount(String cgCount) {
		this.cgCount = cgCount;
	}

	public String getTgCount() {
		return tgCount;
	}

	public void setTgCount(String tgCount) {
		this.tgCount = tgCount;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCgConversionCount() {
		return cgConversionCount;
	}

	public void setCgConversionCount(String cgConversionCount) {
		this.cgConversionCount = cgConversionCount;
	}

	public String getTgConversionCount() {
		return tgConversionCount;
	}

	public void setTgConversionCount(String tgConversionCount) {
		this.tgConversionCount = tgConversionCount;
	}

	public String getPostcgCount() {
		return postcgCount;
	}

	public void setPostcgCount(String postcgCount) {
		this.postcgCount = postcgCount;
	}

	public String getPosttgCount() {
		return posttgCount;
	}

	public void setPosttgCount(String posttgCount) {
		this.posttgCount = posttgCount;
	}

	public String getPostcgConversionCount() {
		return postcgConversionCount;
	}

	public void setPostcgConversionCount(String postcgConversionCount) {
		this.postcgConversionCount = postcgConversionCount;
	}

	public String getPosttgConversionCount() {
		return posttgConversionCount;
	}

	public void setPosttgConversionCount(String posttgConversionCount) {
		this.posttgConversionCount = posttgConversionCount;
	}

	@Override
	public String toString() {
		return "PrePostAnalyticsDataTO [type=" + type + ", cgCount=" + cgCount + ", tgCount=" + tgCount
				+ ", cgConversionCount=" + cgConversionCount + ", tgConversionCount=" + tgConversionCount
				+ ", postcgCount=" + postcgCount + ", posttgCount=" + posttgCount + ", postcgConversionCount="
				+ postcgConversionCount + ", posttgConversionCount=" + posttgConversionCount + ", color=" + color + "]";
	}

}
