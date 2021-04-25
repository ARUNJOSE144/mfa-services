package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="TARGETING_LANGUAGE_MASTER")
@JsonInclude(Include.NON_NULL)
public class TargetingLanguageMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="TargetingLanguageMaster")
	@TableGenerator(name="TargetingLanguageMaster")
	@Column(name="ID")
	private int id;
	
	@Column(name="KEY_VAL")
	private String key;
	
	@Column(name="NAME_VAL")
	private String value;
	
	@Column(name="LANGUAGE_CODE")
	private String languageCode;

	@Column(name="MEDIA_TYPE")
	private String mediaType;
	
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public String toString() {
		return "TargetingLanguageMaster [id=" + id + ", key=" + key + ", value=" + value + ", languageCode="
				+ languageCode + ", mediaType=" + mediaType + "]";
	}


	
	
}
