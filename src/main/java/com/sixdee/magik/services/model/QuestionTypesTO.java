package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@Entity
@Table(name = "QUESTION_TYPES")
public class QuestionTypesTO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuestionTypesTO")
	@TableGenerator(name = "QuestionTypesTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	
	@Column(name = "TYPE_ID")
	private String typeId;
	
	@Column(name = "TYPE_DESCRIPTION")
	private String typeDescrpition;
	
	@CreationTimestamp
	@Column(name = "CREATE_DATETIME",nullable = false, updatable = false)
	private Date createdatetime;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeDescrpition() {
		return typeDescrpition;
	}

	public void setTypeDescrpition(String typeDescrpition) {
		this.typeDescrpition = typeDescrpition;
	}

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	
	

}
