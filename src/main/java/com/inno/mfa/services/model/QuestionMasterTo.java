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
@Data
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

}