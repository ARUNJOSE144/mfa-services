package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "X_NOTIFIER_TONE_DETAILS")
public class AppXNotifierToneTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AppXNotifierToneTO")
	@TableGenerator(name = "AppXNotifierToneTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name="LANGUAGE_ID")
	private int languageId;
	
	@Column(name="TONE_ID")
	private int toneId;

	@Column(name="LANGUAGE_ABB")
	private String langAbbrevation;

	@Column(name="IMAGE_URL")
	private String imageUrl;
	
	@Column(name="TONE_URL")
	private String toneUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getToneId() {
		return toneId;
	}

	public void setToneId(int toneId) {
		this.toneId = toneId;
	}

	public String getLangAbbrevation() {
		return langAbbrevation;
	}

	public void setLangAbbrevation(String langAbbrevation) {
		this.langAbbrevation = langAbbrevation;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getToneUrl() {
		return toneUrl;
	}

	public void setToneUrl(String toneUrl) {
		this.toneUrl = toneUrl;
	}

	@Override
	public String toString() {
		return "AppXNotifierToneTO [id=" + id + ", languageId=" + languageId + ", toneId=" + toneId
				+ ", langAbbrevation=" + langAbbrevation + ", imageUrl=" + imageUrl + ", toneUrl=" + toneUrl + "]";
	}
	
	
	
}
