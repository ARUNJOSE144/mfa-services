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

@Entity
@Table(name = "RE_ACTION_FILE_STATUS")
@SuppressWarnings("serial")
public class ActionFileTO implements Serializable {

	/**
	 * 		
	 */
	private static final long serialVersionUID = 7160417344797683482L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionFileTO")
	@TableGenerator(name = "ActionFileTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "SCHEDULE_ID")
	private int scheduledId;

	@Column(name = "ACTION_ID")
	private String actionId;

	@Column(name = "ACTION_FILE_NAME")
	private String actionFileName;

	@Column(name = "SCHEDULE_NAME")
	private String scheduleName;

	@Column(name = "FILE_STATUS")
	private char fileStatus;

	@Column(name = "COUNTS")
	private String counts;

	@Column(name = "ACTION_NAME")
	private String actionName;

	@Column(name = "EXECUTION_LEVEL")
	private int executionLevel;

	@Column(name = "SAMPLING_OUTPUT")
	private String samplingOutput;

	@Column(name = "EXECUTION_TIME")
	private Date executionTime;

	@Column(name = "FILE_IDENTIFIER")
	private char fileIdentifier;

	@Column(name = "DATA_TYPE")
	private char dataType;

	@Column(name = "PROCESS_TYPE")
	private char processType;

	@Column(name = "NODE_NAME")
	private String nodeName;

	@Column(name = "ANALYSIS_VALUE")
	private String analysisValue;

	@Transient
	private String ip;
	@Transient
	private String userName;
	@Transient
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScheduledId() {
		return scheduledId;
	}

	public void setScheduledId(int scheduledId) {
		this.scheduledId = scheduledId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionFileName() {
		return actionFileName;
	}

	public void setActionFileName(String actionFileName) {
		this.actionFileName = actionFileName;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public char getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(char fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public int getExecutionLevel() {
		return executionLevel;
	}

	public void setExecutionLevel(int executionLevel) {
		this.executionLevel = executionLevel;
	}

	public String getSamplingOutput() {
		return samplingOutput;
	}

	public void setSamplingOutput(String samplingOutput) {
		this.samplingOutput = samplingOutput;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public char getFileIdentifier() {
		return fileIdentifier;
	}

	public void setFileIdentifier(char fileIdentifier) {
		this.fileIdentifier = fileIdentifier;
	}

	public char getDataType() {
		return dataType;
	}

	public void setDataType(char dataType) {
		this.dataType = dataType;
	}

	public char getProcessType() {
		return processType;
	}

	public void setProcessType(char processType) {
		this.processType = processType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getAnalysisValue() {
		return analysisValue;
	}

	public void setAnalysisValue(String analysisValue) {
		this.analysisValue = analysisValue;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ActionFileTO [id=" + id + ", scheduledId=" + scheduledId + ", actionId=" + actionId
				+ ", actionFileName=" + actionFileName + ", scheduleName=" + scheduleName + ", fileStatus=" + fileStatus
				+ ", counts=" + counts + ", actionName=" + actionName + ", executionLevel=" + executionLevel
				+ ", samplingOutput=" + samplingOutput + ", executionTime=" + executionTime + ", fileIdentifier="
				+ fileIdentifier + ", dataType=" + dataType + ", processType=" + processType + ", nodeName=" + nodeName
				+ ", analysisValue=" + analysisValue + ", ip=" + ip + ", userName=" + userName + ", password="
				+ password + "]";
	}

}
