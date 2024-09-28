package com.inno.mfa.services.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Entity
@Table(name = "QUESTION_MASTER")
public class QuestionMasterTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "QUESTION_KEYS", nullable = false)
	private String key;

	@Column(name = "QUESTION", nullable = true)
	private String question;

	@Column(name = "ANSWER", nullable = true)
	private String answer;

	@Column(name = "QUESTION_FROM", nullable = true)
	private Integer questionFrom;

	@Column(name = "CREATED_TIME", nullable = true)
	private Date createdTime;

	@Column(name = "SOFT_DELETE", nullable = true)
	private int softDelete;

	@Column(name = "SUBJECT")
	private int subjectId;

	@Column(name = "BOOKMARK")
	private int bookmark;

	@Transient
	List<MultipartFile> files;

	@Transient
	private int havingAnswer;

	@Transient
	private int rowCount;

	@Transient
	private int page;

	@Transient
	private List subjectIds;

	@Transient
	private List questionsFromIds;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getQuestionFrom() {
		return questionFrom;
	}

	public void setQuestionFrom(Integer questionFrom) {
		this.questionFrom = questionFrom;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(int softDelete) {
		this.softDelete = softDelete;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getBookmark() {
		return bookmark;
	}

	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public int getHavingAnswer() {
		return havingAnswer;
	}

	public void setHavingAnswer(int havingAnswer) {
		this.havingAnswer = havingAnswer;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(List subjectIds) {
		this.subjectIds = subjectIds;
	}

	public List getQuestionsFromIds() {
		return questionsFromIds;
	}

	public void setQuestionsFromIds(List questionsFromIds) {
		this.questionsFromIds = questionsFromIds;
	}

}