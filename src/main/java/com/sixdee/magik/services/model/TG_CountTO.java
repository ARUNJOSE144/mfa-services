package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@Entity
@Table(name = "DB_CMP_TG_COUNT_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TG_CountTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TG_CountTO")
	@TableGenerator(name = "TG_CountTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "YEAR2004")
	private String year2004;

	@Column(name = "YEAR2005")
	private String year2005;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getYear2004() {
		return year2004;
	}

	public void setYear2004(String year2004) {
		this.year2004 = year2004;
	}

	public String getYear2005() {
		return year2005;
	}

	public void setYear2005(String year2005) {
		this.year2005 = year2005;
	}

	@Override
	public String toString() {
		return "TG_CountTO [id=" + id + ", country=" + country + ", year2004=" + year2004 + ", year2005=" + year2005
				+ "]";
	}

}
