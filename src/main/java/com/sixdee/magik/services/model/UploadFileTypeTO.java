package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */


@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_UPLOAD_FILE_TYPE")
public class UploadFileTypeTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "UploadFileTypeTO")
	@TableGenerator(name = "UploadFileTypeTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	

	@Column(name = "UPLOAD_FILE_TYPE")
	private String uploadFileType;


	public int getAutoId() {
		return autoId;
	}


	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}


	public String getUploadFileType() {
		return uploadFileType;
	}


	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType;
	}
	

}
