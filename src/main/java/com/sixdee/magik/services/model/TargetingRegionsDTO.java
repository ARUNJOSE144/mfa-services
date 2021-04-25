package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TARGETING_REGIONS_MASTER")
@DynamicUpdate(true)
public class TargetingRegionsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TargetingRegionsDTO")
	@TableGenerator(name = "TargetingRegionsDTO")
	@Column(name = "ID")
	private int id;

	@Column(name = "KEY_VAL")
	private String key;

	@Column(name = "NAME_VAL")
	private String name;

	@Column(name = "TYPE_VAL")
	private String type;

	@Column(name = "COUNTRY_CODE")
	private String country_code;

	@Column(name = "SUPPORTS_REGION")
	private String supports_region;

	@Column(name = "SUPPORTS_CITY")
	private String supports_city;

	@Column(name = "MEDIA_TYPE")
	private String mediaType;

	@Column(name = "COUNTRY_NAME")
	private String country_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getSupports_region() {
		return supports_region;
	}

	public void setSupports_region(String supports_region) {
		this.supports_region = supports_region;
	}

	public String getSupports_city() {
		return supports_city;
	}

	public void setSupports_city(String supports_city) {
		this.supports_city = supports_city;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	@Override
	public String toString() {
		return "TargetingRegionsDTO [id=" + id + ", key=" + key + ", name=" + name + ", type=" + type
				+ ", country_code=" + country_code + ", supports_region=" + supports_region + ", supports_city="
				+ supports_city + ", mediaType=" + mediaType + ", country_name=" + country_name + "]";
	}

}
