package com.sixdee.magik.services.util;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Param {
	
	public String name;
	public String value;
	private String count;
	public String samplingName;
	public String nodeName;
	public String endNode;
	public MultiParam MultiParam;
	@XStreamImplicit(itemFieldName="MULTI-PARAM")
	public ArrayList<MultiParam> multiParams;
	public ArrayList<Detail> multiDetails;
	public ArrayList<Detail> multiValue;
	
	
	public ArrayList<Detail> getMultiValue() {
		return multiValue;
	}
	public void setMultiValue(ArrayList<Detail> multiValue) {
		this.multiValue = multiValue;
	}
	public MultiParam getMultiParam() {
		return MultiParam;
	}
	public void setMultiParam(MultiParam multiParam) {
		MultiParam = multiParam;
	}
	
	/**
	 * @return the samplingName
	 */
	public String getSamplingName() {
		return samplingName;
	}
	/**
	 * @param samplingName the samplingName to set
	 */
	public void setSamplingName(String samplingName) {
		this.samplingName = samplingName;
	}
	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * @return the endNode
	 */
	public String getEndNode() {
		return endNode;
	}
	/**
	 * @param endNode the endNode to set
	 */
	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public ArrayList<Detail> getMultiDetails() {
		return multiDetails;
	}
	public void setMultiDetails(ArrayList<Detail> multiDetails) {
		this.multiDetails = multiDetails;
	}
	public ArrayList<MultiParam> getMultiParams() {
		return multiParams;
	}
	public void setMultiParams(ArrayList<MultiParam> multiParams) {
		this.multiParams = multiParams;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Param [name=" + name + ", value=" + value + ", count=" + count
				+ ", samplingName=" + samplingName + ", nodeName=" + nodeName
				+ ", endNode=" + endNode + ", MultiParam=" + MultiParam
				+ ", multiParams=" + multiParams + ", multiDetails="
				+ multiDetails + ", multiValue=" + multiValue + "]";
	}
	
}
