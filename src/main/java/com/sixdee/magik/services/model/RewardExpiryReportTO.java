package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import java.util.Date;

import javax.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 *
 */
@Entity
@Table(name = "RPT_REWARD_EXPIRY")
@SuppressWarnings("serial")
public class RewardExpiryReportTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RewardExpirReportTO")
	@TableGenerator(name = "RewardExpirReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "GAME_NAME")
	private String gameName;
	
	@Column(name = "REWRD_DETAILS")
	private String rewardDetails;
	
	@Column(name = "EXPIRY_DATE")
	private String expiryDate;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getRewardDetails() {
		return rewardDetails;
	}

	public void setRewardDetails(String rewardDetails) {
		this.rewardDetails = rewardDetails;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	


}
