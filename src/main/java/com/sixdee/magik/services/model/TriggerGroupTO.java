package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@Entity
@Table(name = "RE_TRIGGER_GROUPS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TriggerGroupTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TriggerGroupTO")
	@TableGenerator(name = "TriggerGroupTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;
	
	@Column(name = "Name")
	private String name;

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

	
}
