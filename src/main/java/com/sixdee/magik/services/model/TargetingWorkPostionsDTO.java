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
@Table(name="TARGETING_WORK_POSTIONS")
@DynamicUpdate(true)
public class TargetingWorkPostionsDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="TargetingWorkPostionsDTO")
	@TableGenerator(name="TargetingWorkPostionsDTO")
	@Column(name="ID")
	private int seqId;
	
	@Column(name="FB_ID")
	private String id;
	
	@Column(name="NAME_VAL")
	private String name;
	
	@Column(name="TYPE_VAL")
	private String type;
	
	@Column(name="MEDIA_TYPE")
	private String mediaType;
	
	@Transient
	private List<String> path;
	
	@Transient
	private String description;

	@Transient
	private String audience_size;
	
	@Transient
	private String real_time_cluster;
	
	@Transient
	private List<TargetingWorkPostionsDTO> data;

	public int getSeqId() {
		return seqId;
	}

	public void setSeqId(int seqId) {
		this.seqId = seqId;
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

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAudience_size() {
		return audience_size;
	}

	public void setAudience_size(String audience_size) {
		this.audience_size = audience_size;
	}

	public String getReal_time_cluster() {
		return real_time_cluster;
	}

	public void setReal_time_cluster(String real_time_cluster) {
		this.real_time_cluster = real_time_cluster;
	}

	public List<TargetingWorkPostionsDTO> getData() {
		return data;
	}

	public void setData(List<TargetingWorkPostionsDTO> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TargetingWorkPostionsDTO [seqId=" + seqId + ", id=" + id + ", name=" + name + ", type=" + type
				+ ", mediaType=" + mediaType + ", path=" + path + ", description=" + description + ", audience_size="
				+ audience_size + ", real_time_cluster=" + real_time_cluster + ", data=" + data + "]";
	}

	
	
	
}
