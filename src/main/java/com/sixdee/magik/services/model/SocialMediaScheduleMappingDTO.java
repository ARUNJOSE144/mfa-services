package com.sixdee.magik.services.model;
/**
 * @author amal.a.s
 * @Date : June, 2020
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SOCIAL_MEDIA_SCHEDULE_MAPPING")
@DynamicUpdate(true)
public class SocialMediaScheduleMappingDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SocialMediaScheduleMappingDTO")
	@TableGenerator(name = "SocialMediaScheduleMappingDTO")
	@Column(name = "ID")
	private int id;

	@Column(name = "ID_PARAM")
	private String idParam;

	@Column(name = "VALUE_PARAM")
	private String valueParam;

	@Column(name = "TYPE_PARAM")
	private String typeParam;

	@ManyToOne
	@JoinColumn(name = "SCHEDULE_ID", nullable = false)
	private SocialMediaScheduleMasterDTO socialMediaMaster;

	public SocialMediaScheduleMappingDTO() {
	}

	public SocialMediaScheduleMappingDTO(String idParam, String valueParam, String typeParam,
			SocialMediaScheduleMasterDTO socialMediaMaster) {
		super();
		this.idParam = idParam;
		this.valueParam = valueParam;
		this.typeParam = typeParam;
		this.socialMediaMaster = socialMediaMaster;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdParam() {
		return idParam;
	}

	public void setIdParam(String idParam) {
		this.idParam = idParam;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public String getTypeParam() {
		return typeParam;
	}

	public void setTypeParam(String typeParam) {
		this.typeParam = typeParam;
	}

	public SocialMediaScheduleMasterDTO getSocialMediaMaster() {
		return socialMediaMaster;
	}

	public void setSocialMediaMaster(SocialMediaScheduleMasterDTO socialMediaMaster) {
		this.socialMediaMaster = socialMediaMaster;
	}

	@Override
	public String toString() {
		return "SocialMediaScheduleMappingDTO [id=" + id + ", idParam=" + idParam + ", valueParam=" + valueParam
				+ ", typeParam=" + typeParam + ", socialMediaMaster=" + socialMediaMaster + "]";
	}

}
