package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "MESSAGE_TEMPLATES")
@JsonInclude(Include.NON_NULL)
public class TemplateTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = "TemplateTO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TemplateTO")
	@Column(name = "TEMPLATE_ID")
	private int templateId;
	
	@Column(name = "TEMPLATE_NAME")
	private String templateName;
	
	@Column(name = "TEMPLATE_DESC")
	private String templateDesc;
	
	@Column(name = "CREATE_USER")
	private String userId;
	
	@Transient
	private String keyword;
	
	@Transient
	private List<TemplateTO> templates;


	public int getTemplateId() {
		return templateId;
	}


	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public String getTemplateDesc() {
		return templateDesc;
	}


	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public List<TemplateTO> getTemplates() {
		return templates;
	}


	public void setTemplates(List<TemplateTO> templates) {
		this.templates = templates;
	}
	
	
}
