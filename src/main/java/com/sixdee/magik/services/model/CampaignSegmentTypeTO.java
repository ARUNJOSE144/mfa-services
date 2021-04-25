package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author Rajesh
 * @Date : Feb, 2018
 *
 */

@Entity
@Table(name = "CAMPAIGN_SEG_TYPES")
@SuppressWarnings("serial")
public class CampaignSegmentTypeTO implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignSegmentTypeTO")
	@TableGenerator(name = "CampaignSegmentTypeTO", allocationSize = 1)
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
}
