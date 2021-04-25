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
 * @Date : April, 2021
 *
 */
@Entity
@Table(name = "AK_CREDIT_TYPE ")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ActionKeyCreditTypeTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionKeyCreditTypeTO")
	@TableGenerator(name = "ActionKeyCreditTypeTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

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

	@Override
	public String toString() {
		return "ActionKeyCreditTypeTO [id=" + id + ", name=" + name + "]";
	}

}
