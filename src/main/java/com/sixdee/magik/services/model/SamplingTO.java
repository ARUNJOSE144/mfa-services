package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="RE_SAMPLING")
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class SamplingTO implements Serializable{
	
	/**
	 * @author : Rajesh
	 * @category Sampling  TO
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="SamplingTO")
	@TableGenerator( name="SamplingTO",  allocationSize=1 )
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAME")
	private String samplingName;
	
	@Column(name = "NODE_DATA")
	private String nodeData;
	
	@Column(name = "LINKED_DATA")
	private String linkedData;
	
	@Column(name = "SEGMENT_DATA")
	private String segmentData;
	
	@Column(name = "CREATE_DATE",updatable = false)
	private Date createDate;
	
	@Transient
	private String strCreateDate;
	
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "STRATIFIED_DATA")
	private String stratifiedData;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSamplingName() {
		return samplingName;
	}
	public void setSamplingName(String samplingName) {
		this.samplingName = samplingName;
	}
	public String getNodeData() {
		return nodeData;
	}
	public void setNodeData(String nodeData) {
		this.nodeData = nodeData;
	}
	public String getLinkedData() {
		return linkedData;
	}
	public void setLinkedData(String linkedData) {
		this.linkedData = linkedData;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSegmentData() {
		return segmentData;
	}
	public void setSegmentData(String segmentData) {
		this.segmentData = segmentData;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStratifiedData() {
		return stratifiedData;
	}
	public void setStratifiedData(String stratifiedData) {
		this.stratifiedData = stratifiedData;
	}
	public String getStrCreateDate() {
		return strCreateDate;
	}
	public void setStrCreateDate(String strCreateDate) {
		this.strCreateDate = strCreateDate;
	}
	@Override
	public String toString() {
		return "SamplingTO [id=" + id + ", samplingName=" + samplingName
				+ ", nodeData=" + nodeData + ", linkedData=" + linkedData
				+ ", segmentData=" + segmentData + ", createDate=" + createDate
				+ ", strCreateDate=" + strCreateDate + ", userId=" + userId
				+ ", stratifiedData=" + stratifiedData + "]";
	}
	
	

}
