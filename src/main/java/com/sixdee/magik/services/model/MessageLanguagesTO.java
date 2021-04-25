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
 * @Date : December, 2020
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_LANGUAGES")
public class MessageLanguagesTO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MessageLanguagesTO")
	@TableGenerator(name = "MessageLanguagesTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	
	@Column(name = "LANGUAGE")
	private String languages;


	public int getAutoId() {
		return autoId;
	}


	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}


	public String getLanguages() {
		return languages;
	}


	public void setLanguages(String languages) {
		this.languages = languages;
	}
	
	
	
	

}
