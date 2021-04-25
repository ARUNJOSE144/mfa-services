package com.sixdee.magik.services.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
public class AttachedFileTransientTO {

	
	private String fileType;
	private String encodedFile_STRING;
	private String fileName;
	private String mimeType;
	
	
	
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getEncodedFile_STRING() {
		return encodedFile_STRING;
	}
	public void setEncodedFile_STRING(String encodedFile_STRING) {
		this.encodedFile_STRING = encodedFile_STRING;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	
	

	
	
	
	
	
}
