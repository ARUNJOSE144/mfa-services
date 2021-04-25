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
 * @category January 2021
 * 
 */
@Entity
@Table(name = "AK_BUNDLE_TYPE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class ActionKeyBundleTypeTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionKeyBundleTypeTO")
	@TableGenerator(name = "ActionKeyBundleTypeTO", allocationSize = 1)
	@Column(name = "ID")
	private String id;
	@Column(name = "TYPE")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ActionKeyBundleTypeTO [id=" + id + ", type=" + type + "]";
	}

}
