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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "SOCIAL_MEDIA_FILE_DETAILS")
@DynamicUpdate(true)
@JsonInclude(Include.NON_NULL)
public class SocialMediaFileDetailsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SocialMediaFileDetailsDTO")
	@TableGenerator(name = "SocialMediaFileDetailsDTO")
	@Column(name = "ID")
	private int id;
	
	@Column(name = "MEDIA_ID")
	private int mediaId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_TYPE")
	private String fileType;
	
	@Column(name = "MEDIA_TYPE")
	private String mediaType;

	@Transient
	private List<SocialMediaFileDetailsDTO> mediaFileList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public List<SocialMediaFileDetailsDTO> getMediaFileList() {
		return mediaFileList;
	}

	public void setMediaFileList(List<SocialMediaFileDetailsDTO> mediaFileList) {
		this.mediaFileList = mediaFileList;
	}

	@Override
	public String toString() {
		return "SocialMediaFileDetailsDTO [id=" + id + ", mediaId=" + mediaId + ", fileName=" + fileName + ", fileType="
				+ fileType + ", mediaType=" + mediaType + ", mediaFileList=" + mediaFileList + "]";
	}
	
	
	
}
