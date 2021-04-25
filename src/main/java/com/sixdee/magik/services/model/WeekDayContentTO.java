package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "RE_WEEK_DAYS_CONTENT")
@SuppressWarnings("serial")
public class WeekDayContentTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WeekDayContentTO")
	@TableGenerator(name = "WeekDayContentTO", allocationSize = 1)
	@Column(name = "DAY_ID")
	private int id;

	@Column(name = "DAY_NAME")
	private String name;

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

	@Override
	public String toString() {
		return "WeekDayContentTO [id=" + id + ", name=" + name + "]";
	}

}
