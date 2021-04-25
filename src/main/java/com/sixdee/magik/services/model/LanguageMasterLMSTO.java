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
@Table(name = "LMS_CNFG_LANGUAGE_MASTER")
public class LanguageMasterLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LanguageMasterLMSTO")
	@TableGenerator(name = "LanguageMasterLMSTO", allocationSize = 1)
	@Column(name = "LANGUAGEID")
	private int languageId;
	
	
	@Column(name = "LANGUAGE")
	private String language;


	public int getLanguageId() {
		return languageId;
	}


	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}

}
