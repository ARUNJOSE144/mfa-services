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
@Table(name="RE_SAMPLING_TYPES")
@SuppressWarnings("serial")

public class SamplingTypesTO implements Serializable{
	/**
	 * @author : Rajesh
	 * @category Sampling Nodes TO
	 */
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="SamplingTypesTO")
	@TableGenerator( name="SamplingTypesTO",  allocationSize=1 )
	@Column(name = "ID")
	private int id;
	
	@Column(name = "SAMPLING_TYPE")
	private String category;
	
	@Column(name = "XML_VALUE")
	private String xmlValue;
	
	@Column(name = "KEY")
	private int key;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getXmlValue() {
		return xmlValue;
	}
	public void setXmlValue(String xmlValue) {
		this.xmlValue = xmlValue;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	

}
