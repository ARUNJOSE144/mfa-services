package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "CIRCLES")
@SuppressWarnings("serial")
public class CircleTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CircleTO")
	@TableGenerator(name = "CircleTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "CIRCLES")
	private String circles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCircles() {
		return circles;
	}

	public void setCircles(String circles) {
		this.circles = circles;
	}	
	


}
