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
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@Entity
@Table(name = "DB_CMP_ARPU_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ARPUSegmentsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ARPUSegmentsTO")
	@TableGenerator(name = "ARPUSegmentsTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "TITLE")
	private String name;

	@Column(name = "VALUE")
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ARPUSegmentsTO [id=" + id + ", name=" + name + ", value=" + value + "]";
	}

}
