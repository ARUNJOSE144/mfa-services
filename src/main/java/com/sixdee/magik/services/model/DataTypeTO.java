package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ramesh.cheerla
 * @Date : November, 2018
 *
 */

@Entity
@Table(name = "RE_DATA_TYPES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DataTypeTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DataTypeTO")
	@TableGenerator(name = "DataTypeTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
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
