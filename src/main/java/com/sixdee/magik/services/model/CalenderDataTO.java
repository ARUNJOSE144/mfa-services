package com.sixdee.magik.services.model;

/**
 * @author Ramesh Babu Cheerla
 * @Date : April, 2019
 *
 */

public class CalenderDataTO {

	private String id;
	private String campId;
	private String start;
	private String end;
	private String title;
	private String textColor;
	private String backgroundColor;
	private String borderColor;
	private String description;
	private String campSatrtDate;
	private String campEndDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCampSatrtDate() {
		return campSatrtDate;
	}

	public void setCampSatrtDate(String campSatrtDate) {
		this.campSatrtDate = campSatrtDate;
	}

	public String getCampEndDate() {
		return campEndDate;
	}

	public void setCampEndDate(String campEndDate) {
		this.campEndDate = campEndDate;
	}

}
