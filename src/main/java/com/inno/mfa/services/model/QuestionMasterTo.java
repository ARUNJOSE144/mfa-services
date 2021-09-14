package com.inno.mfa.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	@Column(name = "CREATED_TIME", nullable = true)
	private Date createdTime;

}