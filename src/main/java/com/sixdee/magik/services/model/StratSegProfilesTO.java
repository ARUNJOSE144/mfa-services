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
@Table(name = "RE_STRTFD_SAMP_PROF")
@SuppressWarnings("serial")
public class StratSegProfilesTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator ="StratSegProfilesTO")
	@TableGenerator( name="StratSegProfilesTO",  allocationSize=1 )
	@Column(name = "ID")
	private int id;

	@Column(name = "GROUP_ID")
	private int groupId;

	@Column(name = "SEGMENT_ID")
	private int subGroupId;

	@Column(name = "COLUMN_NAME")
	private String profile;

	@Column(name = "ENGLISH_NAME")
	private String englishName;

	@Column(name = "TYPE")
	private int type;

	@Column(name = "OPTION_VALUES")
	private String optValues;

	private String groupName;

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

	public int getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(int subGroupId) {
		this.subGroupId = subGroupId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getOptValues() {
		return optValues;
	}

	public void setOptValues(String optValues) {
		this.optValues = optValues;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
