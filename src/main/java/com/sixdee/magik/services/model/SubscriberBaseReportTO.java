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
@Table(name = "RPT_SUBSCRIBER_BASE")
public class SubscriberBaseReportTO {
	

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SubscriberBaseReportTO")
	@TableGenerator(name = "SubscriberBaseReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "GAMER_NAME")
	private String gamerName;
	
	@Column(name = "GAMER_MAILID")
	private String gamerMailId;
	
	@Column(name = "GSMER_GENDER")
	private String gamerGender;
	
	@Column(name = "GAMER_NUMBER")
	private int gamerNumber;
	
	@Column(name = "GAMER_CURRENT_RANK")
	private int gamerCurrentRank;
	
	@Column(name = "GAMER_REGION")
	private String gamerRegion;
	
	@Column(name = "GAMER_AGE_GROUP")
	private String gamerAgeGroup;
	
	@Column(name = "CURRENT_AVATARS")
	private String currentAvatars;
	
	@Column(name = "NUMBER_OF_BADGES")
	private int numberOfBadges;
	
	@Column(name = "NUMBER_OF_TROPHIES")
	private int numberOfTrophies;
	
	@Column(name = "CURRENT_GAMEPOINT_BALANCE")
	private int currentGamepointBalance;
	
	@Column(name = "NUMBER_OF_GAMES")
	private int numberOfGames;
	
	@Column(name = "NUMBER_OF_REWARDS")
	private int numberOfRewards;
	
	@Column(name = "INACTIVE_DAYS")
	private int inactiveDays;
	
	@Column(name = "ACTIVE_DAYS")
	private int activeDays	;
	
	@Column(name = "LAST_GAME")
	private String lastGame;
	
	@Column(name = "LATEST_REWARD")
	private String latestReward;
	
	@Column(name = "EXPIRED_REWARD_POINTS")
	private String expirdRewardPoints;


	@Column(name = "SUBSCRIBER_DATE")
	private String subscriberDate;


	public int getAutoId() {
		return autoId;
	}


	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}


	public String getGamerName() {
		return gamerName;
	}


	public void setGamerName(String gamerName) {
		this.gamerName = gamerName;
	}


	public String getGamerMailId() {
		return gamerMailId;
	}


	public void setGamerMailId(String gamerMailId) {
		this.gamerMailId = gamerMailId;
	}


	public String getGamerGender() {
		return gamerGender;
	}


	public void setGamerGender(String gamerGender) {
		this.gamerGender = gamerGender;
	}


	public int getGamerNumber() {
		return gamerNumber;
	}


	public void setGamerNumber(int gamerNumber) {
		this.gamerNumber = gamerNumber;
	}


	public int getGamerCurrentRank() {
		return gamerCurrentRank;
	}


	public void setGamerCurrentRank(int gamerCurrentRank) {
		this.gamerCurrentRank = gamerCurrentRank;
	}


	public String getGamerRegion() {
		return gamerRegion;
	}


	public void setGamerRegion(String gamerRegion) {
		this.gamerRegion = gamerRegion;
	}


	public String getGamerAgeGroup() {
		return gamerAgeGroup;
	}


	public void setGamerAgeGroup(String gamerAgeGroup) {
		this.gamerAgeGroup = gamerAgeGroup;
	}


	public String getCurrentAvatars() {
		return currentAvatars;
	}


	public void setCurrentAvatars(String currentAvatars) {
		this.currentAvatars = currentAvatars;
	}


	public int getNumberOfBadges() {
		return numberOfBadges;
	}


	public void setNumberOfBadges(int numberOfBadges) {
		this.numberOfBadges = numberOfBadges;
	}


	public int getNumberOfTrophies() {
		return numberOfTrophies;
	}


	public void setNumberOfTrophies(int numberOfTrophies) {
		this.numberOfTrophies = numberOfTrophies;
	}


	public int getCurrentGamepointBalance() {
		return currentGamepointBalance;
	}


	public void setCurrentGamepointBalance(int currentGamepointBalance) {
		this.currentGamepointBalance = currentGamepointBalance;
	}


	public int getNumberOfGames() {
		return numberOfGames;
	}


	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}


	public int getNumberOfRewards() {
		return numberOfRewards;
	}


	public void setNumberOfRewards(int numberOfRewards) {
		this.numberOfRewards = numberOfRewards;
	}


	public int getInactiveDays() {
		return inactiveDays;
	}


	public void setInactiveDays(int inactiveDays) {
		this.inactiveDays = inactiveDays;
	}


	public int getActiveDays() {
		return activeDays;
	}


	public void setActiveDays(int activeDays) {
		this.activeDays = activeDays;
	}


	public String getLastGame() {
		return lastGame;
	}


	public void setLastGame(String lastGame) {
		this.lastGame = lastGame;
	}


	public String getLatestReward() {
		return latestReward;
	}


	public void setLatestReward(String latestReward) {
		this.latestReward = latestReward;
	}


	public String getExpirdRewardPoints() {
		return expirdRewardPoints;
	}


	public void setExpirdRewardPoints(String expirdRewardPoints) {
		this.expirdRewardPoints = expirdRewardPoints;
	}


	public String getSubscriberDate() {
		return subscriberDate;
	}


	public void setSubscriberDate(String subscriberDate) {
		this.subscriberDate = subscriberDate;
	}
	
	
	

}
