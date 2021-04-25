package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * @author ramesh.cheerla
 *
 */

@Entity
@Table(name = "RE_GEO_LOCATION_MASTER_IN")
@SuppressWarnings("serial")
public class GeoLocationsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GeoLocationsTO")
	@TableGenerator(name = "GeoLocationsTO", allocationSize = 1)
	@Column(name = "LOCATION_ID")
	private int id;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "LOCATION_COORDINATES")
	private String mapJson;

	@Column(name = "PARENT_LOCATION_ID")
	private int parentId;

	@Column(name = "LOCATION_TYPE_ID")
	private int category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getMapJson() {
		return mapJson;
	}

	public void setMapJson(String mapJson) {
		this.mapJson = mapJson;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "GeoLocationsTO [id=" + id + ", locationName=" + locationName + ", mapJson=" + mapJson + ", parentId="
				+ parentId + ", category=" + category + "]";
	}

}
