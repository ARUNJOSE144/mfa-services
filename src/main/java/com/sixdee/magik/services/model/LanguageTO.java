package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Entity
@Table(name = "LANGUAGE")
public class LanguageTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LanguageTO")
	@TableGenerator(name = "LanguageTO", allocationSize = 1)
	@Column(name = "ID")
	private int languageID;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATION_DATE")
	private Date CREATION_DATE;

	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCREATION_DATE() {
		return CREATION_DATE;
	}

	public void setCREATION_DATE(Date cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}

}
