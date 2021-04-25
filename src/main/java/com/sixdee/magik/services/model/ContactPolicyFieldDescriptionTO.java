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
@Table(name = "NCP_FIELD_DESCRIPTION")
@SuppressWarnings("serial")
public class ContactPolicyFieldDescriptionTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ContactPolicyFieldDescriptionTO")
	@TableGenerator(name = "ContactPolicyFieldDescriptionTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "CATEGORY_FIELD")
	private String categoryField;

	@Column(name = "CATEGORY_VALUE")
	private String categoryValue;

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

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

}
