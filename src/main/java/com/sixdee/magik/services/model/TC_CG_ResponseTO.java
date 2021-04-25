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
@Table(name = "DB_CMP_TG_CG_RESP_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TC_CG_ResponseTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TC_CG_ResponseTO")
	@TableGenerator(name = "TC_CG_ResponseTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "TG_RESPONSE")
	private String tgResponse;

	@Column(name = "CG_RESPONSE")
	private String cgResponse;

	@Column(name = "NRR")
	private String nrrValue;

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

	public String getTgResponse() {
		return tgResponse;
	}

	public void setTgResponse(String tgResponse) {
		this.tgResponse = tgResponse;
	}

	public String getCgResponse() {
		return cgResponse;
	}

	public void setCgResponse(String cgResponse) {
		this.cgResponse = cgResponse;
	}

	public String getNrrValue() {
		return nrrValue;
	}

	public void setNrrValue(String nrrValue) {
		this.nrrValue = nrrValue;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "TC_CG_ResponseTO [id=" + id + ", date=" + date + ", tgResponse=" + tgResponse + ", cgResponse="
				+ cgResponse + ", nrrValue=" + nrrValue + ", createDateUI=" + createDateUI + "]";
	}

}
