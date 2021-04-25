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
 * @Date : September, 2019
 *
 */

@Entity
@Table(name = "CAMPAIGN_CHANNEL")
@SuppressWarnings("serial")
public class CampaignChannelTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignChannelTO")
	@TableGenerator(name = "CampaignChannelTO", allocationSize = 1)
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
