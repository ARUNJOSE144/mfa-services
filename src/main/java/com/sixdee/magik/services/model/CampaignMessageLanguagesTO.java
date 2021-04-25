package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author Nakhil Kurian
 * @Date : December, 2020
 */
@Entity
@Table(name = "CMP_MSG_LANGUAGES")
public class CampaignMessageLanguagesTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignMessageLanguagesTO")
	@TableGenerator(name = "CampaignMessageLanguagesTO", allocationSize = 1)
	@Column(name = "ID")
	private String autoId;

	@Column(name = "LANGUAGE")
	private String languages;
	
	@Column(name = "ABBREVATION")
	private String abbrevation;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getAbbrevation() {
		return abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

	@Override
	public String toString() {
		return "CampaignMessageLanguagesTO [autoId=" + autoId + ", languages=" + languages + ", abbrevation="
				+ abbrevation + "]";
	}

	

}
