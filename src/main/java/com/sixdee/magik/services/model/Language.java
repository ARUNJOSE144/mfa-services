package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "LANGUAGE")
@JsonInclude(Include.NON_NULL)
public class Language {
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Language")
    @TableGenerator(name = "Language", allocationSize = 1)
    @Column(name = "ID")
	public int langId;
	
	@Column(name = "LANGUAGE")
	public String langName;
	
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
	
	@Override
	public String toString() {
		return "GetLanguages [langId=" + langId + ", langName=" + langName + "]";
	}
	
    	

}