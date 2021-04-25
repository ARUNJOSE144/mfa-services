package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "RE_GEO_LOCATION_MAPS")
@SuppressWarnings("serial")
public class GeoLocationMapsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GeoJsonTO")
	@TableGenerator(name = "GeoJsonTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "PARENT_ID")
	private int parentId;

	@Column(name = "MAP_JSON")
	private String mapJson;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "MODIFY_DATE")
	private Date modifyDate;

	@Column(name = "CATEGORY")
	private int category;

	@Column(name = "USER_ID")
	private String userId;
	
	

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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getMapJson() {
		return mapJson;
	}

	public void setMapJson(String mapJson) {
		this.mapJson = mapJson;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "GeoLocationMapsTO [id=" + id + ", locationName=" + locationName + ", parentId=" + parentId
				+ ", mapJson=" + mapJson + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", category="
				+ category + ", userId=" + userId + "]";
	}

	
}
