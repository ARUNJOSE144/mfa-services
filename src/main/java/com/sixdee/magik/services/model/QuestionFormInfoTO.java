package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "QUESTION_INFO_TABLE")
public class QuestionFormInfoTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuestionFormInfoTO")
	@TableGenerator(name = "QuestionFormInfoTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	
	@ManyToOne
	@JoinColumn(name="QNAME_ID")
	private QuestionFormTO questionFormTO = new QuestionFormTO();
	
	@Column(name = "QUESTION")
	private String question;
	
	
	@Column(name = "TYPE_ID")
	private int typeId;
	
	@Column(name = "DESCRPTION")
	private String descrption;
	
	@CreationTimestamp
	@Column(name = "CREATION_DATE",nullable = false, updatable = false)
	private Date createTime;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	

	public QuestionFormTO getQuestionFormTO() {
		return questionFormTO;
	}

	public void setQuestionFormTO(QuestionFormTO questionFormTO) {
		this.questionFormTO = questionFormTO;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
}
