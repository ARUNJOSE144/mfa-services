package com.sixdee.magik.services.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RE_QUARANTINE_BLACKLIST_OLD")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuarantineBlackListTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuarantineBlackListTO")
	@TableGenerator(name = "QuarantineBlackListTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_DETAILS_ID")
	private int blackListPolicyDetailsid;

	@Column(name = "UPLOAD_TYPE")
	private String uploadtype;

	@Column(name = "MSISDN")
	private String msisdn;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Transient
	private String msisdnList[];

	@Transient
	private String isFromFile;

	public String getIsFromFile() {
		return isFromFile;
	}

	public void setIsFromFile(String isFromFile) {
		this.isFromFile = isFromFile;
	}

	public int getId() {
		return id;
	}

	public int getBlackListPolicyDetailsid() {
		return blackListPolicyDetailsid;
	}

	public void setBlackListPolicyDetailsid(int blackListPolicyDetailsid) {
		this.blackListPolicyDetailsid = blackListPolicyDetailsid;
	}

	public String getUploadtype() {
		return uploadtype;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public void setUploadtype(String uploadtype) {
		this.uploadtype = uploadtype;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getMsisdnList() {
		return msisdnList;
	}

	public void setMsisdnList(String[] msisdnList) {
		this.msisdnList = msisdnList;
	}

	@Override
	public String toString() {
		return "QuarantineBlackListTO [id=" + id + ", blackListPolicyDetailsid=" + blackListPolicyDetailsid
				+ ", uploadtype=" + uploadtype + ", msisdn=" + msisdn + ", fileName=" + fileName + ", msisdnList="
				+ Arrays.toString(msisdnList) + ", isFromFile=" + isFromFile + "]";
	}

}