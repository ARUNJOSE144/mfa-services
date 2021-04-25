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
@Table(name = "RPT_REWARD")
@SuppressWarnings("serial")
public class RewardReportTO {


	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RewardReportTO")
	@TableGenerator(name = "RewardReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "REWARD_NAME")
	private String rewardName;
	
	@Column(name = "REWARD_TYPE")
	private String rewardType;
	
	@Column(name = "REWARD_STATUS")
	private String rewardStatus;
	
	
	@Column(name = "REDEEM_DATE")
	private String redeemDate;


	public int getAutoId() {
		return autoId;
	}


	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}


	public String getRewardName() {
		return rewardName;
	}


	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}


	public String getRewardType() {
		return rewardType;
	}


	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}


	public String getRewardStatus() {
		return rewardStatus;
	}


	public void setRewardStatus(String rewardStatus) {
		this.rewardStatus = rewardStatus;
	}


	public String getRedeemDate() {
		return redeemDate;
	}


	public void setRedeemDate(String redeemDate) {
		this.redeemDate = redeemDate;
	}
	
	

}
