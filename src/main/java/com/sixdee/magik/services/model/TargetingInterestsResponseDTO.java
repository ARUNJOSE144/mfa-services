package com.sixdee.magik.services.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TARGETING_INTERESTS_MASTER")
@DynamicUpdate(true)
public class TargetingInterestsResponseDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TargetingInterestsResponseDTO")
	@TableGenerator(name = "TargetingInterestsResponseDTO")
	@Column(name = "ID")
	private int seq_id;
	
	@Column(name="FB_ID")
	private String id;

	@Column(name = "NAME_VAL")
	private String name;

	@Column(name = "TYPE_VAL")
	private String type;

	@Column(name="MEDIA_TYPE")
	private String mediaType;
	
	@Transient
	private String audience_size;
	@Transient
	private int lifecycle;
	@Transient
	private List<String> path;
	
	@Transient
	private List<TargetingInterestsResponseDTO> data;
	

	public int getSeq_id() {
		return seq_id;
	}

	public void setSeq_id(int seq_id) {
		this.seq_id = seq_id;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getAudience_size() {
		return audience_size;
	}

	public void setAudience_size(String audience_size) {
		this.audience_size = audience_size;
	}

	public int getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(int lifecycle) {
		this.lifecycle = lifecycle;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public List<TargetingInterestsResponseDTO> getData() {
		return data;
	}

	public void setData(List<TargetingInterestsResponseDTO> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TargetingInterestsResponseDTO [seq_id=" + seq_id + ", id=" + id + ", name=" + name + ", type=" + type
				+ ", mediaType=" + mediaType + ", audience_size=" + audience_size + ", lifecycle=" + lifecycle
				+ ", path=" + path + ", data=" + data + "]";
	}

	
	
	}
