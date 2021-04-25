package com.sixdee.magik.services.model;

import javax.persistence.Entity;

public class QuestionFormDetailsTO {
	
	private String index;
	private String question;
	private int type;
	private String multiplechoice_desc;
	private String shortansw_desc;
	private String paragraph_desc;
	private String checkbox_desc;
	private String dropdown_desc;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMultiplechoice_desc() {
		return multiplechoice_desc;
	}
	public void setMultiplechoice_desc(String multiplechoice_desc) {
		this.multiplechoice_desc = multiplechoice_desc;
	}
	public String getShortansw_desc() {
		return shortansw_desc;
	}
	public void setShortansw_desc(String shortansw_desc) {
		this.shortansw_desc = shortansw_desc;
	}
	public String getParagraph_desc() {
		return paragraph_desc;
	}
	public void setParagraph_desc(String paragraph_desc) {
		this.paragraph_desc = paragraph_desc;
	}
	public String getCheckbox_desc() {
		return checkbox_desc;
	}
	public void setCheckbox_desc(String checkbox_desc) {
		this.checkbox_desc = checkbox_desc;
	}
	public String getDropdown_desc() {
		return dropdown_desc;
	}
	public void setDropdown_desc(String dropdown_desc) {
		this.dropdown_desc = dropdown_desc;
	}
	
	
	

}
