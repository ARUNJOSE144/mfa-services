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
 * @author Arun
 * @Date : Augest, 2019
 *
 */
@Entity
@Table(name = "RE_WHAT_IF_VALUES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WhatIfBasicTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WhatIfBasicTO")
	@TableGenerator(name = "WhatIfBasicTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "MIN_VALUE")
	private int minValue;

	@Column(name = "MAX_VALUE")
	private int maxValue;

	@Column(name = "VALUE")
	private int value;

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

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "WhatIfBasicTO [Id=" + Id + ", name=" + name + ", minValue=" + minValue + ", maxValue=" + maxValue
				+ ", value=" + value + "]";
	}

}
