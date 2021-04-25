package com.sixdee.magik.services.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SamplingNDTO {

	private int id;
	private String category;
	private int key;
	private String xmlValue;
	private String nodeType;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String value;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String order;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String splitValue;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String kpis;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String tg;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String cg;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String[] selectedProfilesList;

	private String loc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getXmlValue() {
		return xmlValue;
	}

	public void setXmlValue(String xmlValue) {
		this.xmlValue = xmlValue;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getSplitValue() {
		return splitValue;
	}

	public void setSplitValue(String splitValue) {
		this.splitValue = splitValue;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getKpis() {
		return kpis;
	}

	public void setKpis(String kpis) {
		this.kpis = kpis;
	}

	public String getTg() {
		return tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	public String getCg() {
		return cg;
	}

	public void setCg(String cg) {
		this.cg = cg;
	}

	public String[] getSelectedProfilesList() {
		return selectedProfilesList;
	}

	public void setSelectedProfilesList(String[] selectedProfilesList) {
		this.selectedProfilesList = selectedProfilesList;
	}

	@Override
	public String toString() {
		return "SamplingNDTO [id=" + id + ", category=" + category + ", key=" + key + ", xmlValue=" + xmlValue
				+ ", nodeType=" + nodeType + ", value=" + value + ", order=" + order + ", splitValue=" + splitValue
				+ ", kpis=" + kpis + ", loc=" + loc + "]";
	}

}
