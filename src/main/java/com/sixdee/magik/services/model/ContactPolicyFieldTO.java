package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="NCP_FIELD_DETAIL")
@SuppressWarnings("serial")
public class ContactPolicyFieldTO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="ContactPolicyFieldTO")
	@TableGenerator(name="ContactPolicyFieldTO",allocationSize=1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "CATEGORY_FIELD")
	private String categoryField;
	
	@Column(name = "CATEGORY_TYPE")
	private int categoryType;
	
	
	@Column(name = "DESCRIPTION")
	private String description;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCategoryField() {
		return categoryField;
	}


	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}


	public int getCategoryType() {
		return categoryType;
	}


	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	


}
