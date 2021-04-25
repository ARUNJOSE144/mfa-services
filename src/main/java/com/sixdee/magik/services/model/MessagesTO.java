package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Entity
@Table(name = "RE_MESSAGES")
@SuppressWarnings("serial")
public class MessagesTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MessagesTO")
	@TableGenerator(name = "MessagesTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "MESSAGE")
	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
