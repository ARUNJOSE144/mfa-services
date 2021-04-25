package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */

@Entity
@Table(name = "CAMPAIGN_TYPE")
@SuppressWarnings("serial")
public class CampaignTypeTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignTypeTO")
	@TableGenerator(name = "CampaignTypeTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TEXT_COLOR")
	private String textColor;

	@Column(name = "BACKGROUND_COLOR")
	private String backgroundcolor;

	@Column(name = "BORDER_COLOR")
	private String borderColor;

	@Column(name = "ICON")
	private String icon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getBackgroundcolor() {
		return backgroundcolor;
	}

	public void setBackgroundcolor(String backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

}
