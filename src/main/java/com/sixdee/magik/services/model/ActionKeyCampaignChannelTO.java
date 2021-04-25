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
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */
@Entity
@Table(name = "AK_CAMPAIGN_CHANNEL")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ActionKeyCampaignChannelTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionKeyCampaignChannelTO")
	@TableGenerator(name = "ActionKeyCampaignChannelTO", allocationSize = 1)
	@Column(name = "ID")
	private String id;
	@Column(name = "NAME")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		return "ActionKeyCampaignChannelTO [id=" + id + ", name=" + name + "]";
	}

}
