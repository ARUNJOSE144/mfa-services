package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CNFG_OFFER_NOMENCLATURE")
public class ConfigureOfferNomenclatureLMSTO {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureOfferNomenclatureLMSTO")
	@TableGenerator(name = "ConfigureOfferNomenclatureLMSTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoID;

	@OneToOne
	@JoinColumn(name = "OFFERID")
	private ConfigureOfferMasterLMSTO offerId;

	
	@OneToOne
	@JoinColumn(name = "LANGUAGEID")
	private LanguageMasterLMSTO langId;

	@Column(name = "OFFERNAME")
	private String offerName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "TERMS_AND_CONDITIONS")
	private String termsCondtions;

	@Column(name = "IMAGENAME")
	private String imageName;

	@Column(name = "SQUARE_IMAGE")
	private String squraeName;

	@Column(name = "ICON_IMAGE")
	private String iconName;

	@Column(name = "ICONIMAGE_B64")
	private String iconName_base64;

	@Column(name = "IMAGENAMEFILE_B64")
	private String imageFile_base64;

	@Column(name = "SQUAREIMAGE_B64")
	private String squareImg_base64;

	public String getIconName_base64() {
		return iconName_base64;
	}

	public void setIconName_base64(String iconName_base64) {
		this.iconName_base64 = iconName_base64;
	}

	public String getImageFile_base64() {
		return imageFile_base64;
	}

	public void setImageFile_base64(String imageFile_base64) {
		this.imageFile_base64 = imageFile_base64;
	}

	public String getSquareImg_base64() {
		return squareImg_base64;
	}

	public void setSquareImg_base64(String squareImg_base64) {
		this.squareImg_base64 = squareImg_base64;
	}

	public int getAutoID() {
		return autoID;
	}

	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}

	public ConfigureOfferMasterLMSTO getOfferId() {
		return offerId;
	}

	public void setOfferId(ConfigureOfferMasterLMSTO offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public LanguageMasterLMSTO getLangId() {
		return langId;
	}

	public void setLangId(LanguageMasterLMSTO langId) {
		this.langId = langId;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTermsCondtions() {
		return termsCondtions;
	}

	public void setTermsCondtions(String termsCondtions) {
		this.termsCondtions = termsCondtions;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getSquraeName() {
		return squraeName;
	}

	public void setSquraeName(String squraeName) {
		this.squraeName = squraeName;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

}
