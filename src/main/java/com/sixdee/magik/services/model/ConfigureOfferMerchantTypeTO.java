package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
 

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CNFG_MERCHANT_NOMENCLATURE")
public class ConfigureOfferMerchantTypeTO {

	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureOfferMerchantTypeTO")
	@TableGenerator(name = "ConfigureOfferMerchantTypeTO", allocationSize = 1)
	@Column(name = "MERCHANTID")
	private int  merchantid;
	
	@OneToOne
	@JoinColumn(name="LANGUAGEID")
	private LanguageMasterLMSTO langId;
	
	@Column(name = "MERCHANTNAME")
	private String  merchantname;
	


	public String getMerchantname() {
		return merchantname;
	}


	public LanguageMasterLMSTO getLangId() {
		return langId;
	}




	public void setLangId(LanguageMasterLMSTO langId) {
		this.langId = langId;
	}




	public int getMerchantid() {
		return merchantid;
	}




	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}




	public void setMerchantid(int merchantid) {
		this.merchantid = merchantid;
	}


	
}
