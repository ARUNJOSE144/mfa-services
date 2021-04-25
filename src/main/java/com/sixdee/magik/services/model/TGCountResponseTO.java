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
 * @Date : December, 2020
 *
 */
@Entity
@Table(name = "DB_CMP_TG_COUNT_RESPONSE_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TGCountResponseTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TGCountResponseTO")
	@TableGenerator(name = "TGCountResponseTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "PUSHED_COUNT")
	private String pushedCount;

	@Column(name = "TGR_COUNT")
	private String tgrCount;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPushedCount() {
		return pushedCount;
	}

	public void setPushedCount(String pushedCount) {
		this.pushedCount = pushedCount;
	}

	public String getTgrCount() {
		return tgrCount;
	}

	public void setTgrCount(String tgrCount) {
		this.tgrCount = tgrCount;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "TGCountResponseTO [id=" + id + ", date=" + date + ", pushedCount=" + pushedCount + ", tgrCount="
				+ tgrCount + ", response=" + response + ", createDateUI=" + createDateUI + "]";
	}

}
