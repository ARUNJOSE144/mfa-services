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
 * @author : Ramesh Babu Cheerla
 * @category Stratified Segment Profiles TO
 */

@Entity
@Table(name = "RE_STRTFD_SAMP_BANDS")
@SuppressWarnings("serial")
public class StratSegProfilesBandsTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "StratSegProfilesBandsTO")
	@TableGenerator(name = "StratSegProfilesBandsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "PROFILE_ID")
	private int groupId;

	@Column(name = "BAND")
	private String band;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "StratSegProfilesBandsTO [id=" + id + ", groupId=" + groupId + ", band=" + band + "]";
	}

}
