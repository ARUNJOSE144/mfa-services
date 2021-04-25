package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="FACEBOOK_CAMPAIGN_DETAILS")
@DynamicUpdate(true)
public class FacebookCampaignDetailsDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="FacebookCampaignDetailsDTO")
	@TableGenerator(name="FacebookCampaignDetailsDTO")
	@Column(name="ID")
	private int id;
	
	@Column(name="MAGIK_CAMPAIGN_ID")
	private int magikCampaignId;
	
	@Column(name="FB_CAMPAIGN_ID")
	private String fbCampaignId;
	
	@Column(name="AD_SET_ID")
	private String adSetId;
	
	@Column(name="AD_CREATIVE_ID")
	private String adCreativeId; 
	
	@Column(name="AD_AD_ID")
	private String adId;
	
	@Column(name="IMAGE_HASH_ID")
	private String imageHashId;
	
	@Column(name="STATUS")
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMagikCampaignId() {
		return magikCampaignId;
	}

	public void setMagikCampaignId(int magikCampaignId) {
		this.magikCampaignId = magikCampaignId;
	}

	public String getFbCampaignId() {
		return fbCampaignId;
	}

	public void setFbCampaignId(String fbCampaignId) {
		this.fbCampaignId = fbCampaignId;
	}

	public String getAdSetId() {
		return adSetId;
	}

	public void setAdSetId(String adSetId) {
		this.adSetId = adSetId;
	}

	public String getAdCreativeId() {
		return adCreativeId;
	}

	public void setAdCreativeId(String adCreativeId) {
		this.adCreativeId = adCreativeId;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getImageHashId() {
		return imageHashId;
	}

	public void setImageHashId(String imageHashId) {
		this.imageHashId = imageHashId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FacebookCampaignDetailsDTO [id=" + id + ", magikCampaignId=" + magikCampaignId + ", fbCampaignId="
				+ fbCampaignId + ", adSetId=" + adSetId + ", adCreativeId=" + adCreativeId + ", adId=" + adId
				+ ", imageHashId=" + imageHashId + ", status=" + status + "]";
	}

	
	
}
