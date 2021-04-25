package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Rajesh
 * @Date : January, 2019
 *
 */
@Entity
@Table(name = "APPLICATION_PROPERTIES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicationPropertyTO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ApplicationPropertyTO")
	@TableGenerator(name = "ApplicationPropertyTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String desc;
	
	@Column(name = "STATUS")
	private int status;

	@Transient
	private ArrayList<ApplicationPropertyTO> propertiesList;
	
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<ApplicationPropertyTO> getPropertiesList() {
		return propertiesList;
	}

	public void setPropertiesList(ArrayList<ApplicationPropertyTO> propertiesList) {
		this.propertiesList = propertiesList;
	}
	
	
	

}
