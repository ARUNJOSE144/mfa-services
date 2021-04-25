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
public class GetLanguages {
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GetLanguages")
    @TableGenerator(name = "GetLanguages", allocationSize = 1)
    @Column(name = "ID")
	public String langId;
	
	@Column(name = "LANGUAGE")
	public String langName;
	
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
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
