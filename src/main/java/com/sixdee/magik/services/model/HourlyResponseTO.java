package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@Entity
@Table(name = "DB_CMP_HOURLY_DELIVERY_COUNT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class HourlyResponseTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "HourlyResponseTO")
	@TableGenerator(name = "HourlyResponseTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "TIME")
	private String time;

	@Column(name = "DELIVERED_COUNT")
	private String delievryCount;

	@Column(name = "RESPONSE")
	private String response;

	@Transient
	private String createDateUI;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDelievryCount() {
		return delievryCount;
	}

	public void setDelievryCount(String delievryCount) {
		this.delievryCount = delievryCount;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "HourlyResponseTO [id=" + id + ", time=" + time + ", delievryCount=" + delievryCount + ", response="
				+ response + ", createDateUI=" + createDateUI + "]";
	}

}
