package com.sixdee.magik.services.model;

import java.util.Date;

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
 * @Date : November, 2020
 *
 */
@Entity
@Table(name = "DB_CMP_HEADER_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CampaignResponseTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignResponseTO")
	@TableGenerator(name = "CampaignResponseTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COUNT")
	private String count;

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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CampaignResponseTO [id=" + id + ", name=" + name + ", count=" + count + "]";
	}

}
