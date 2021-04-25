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
 * @author : Rajesh
 * @category ScheduleTypeTO
 */
@Entity
@Table(name="RE_SCHEDULE_TYPE")
@SuppressWarnings("serial")
public class ScheduleTypeTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="ScheduleTypeTO")
	@TableGenerator( name="ScheduleTypeTO",  allocationSize=1 )
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TYPE")
	private String scheduleType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	
	
	

}
