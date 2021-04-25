package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "LANGUAGE")
public class MobileAppLanguagesTO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SMSLanguagesTO")
	@TableGenerator(name = "SMSLanguagesTO", allocationSize = 1)
	
	@Column(name = "ID")
	private int langId;

	@Column(name = "LANGUAGE")
	private String langName;

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}
	
	
}
