package com.sixdee.magik.services.model;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OptionTransientTO {
	
	//@JsonProperty("label")	
	 private String label;
	
	//@JsonProperty("value")
	private String value;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
