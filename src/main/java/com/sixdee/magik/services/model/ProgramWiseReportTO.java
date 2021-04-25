package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.security.auth.login.LoginContext;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.api.client.util.Data;


/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 *
 */
@Entity
@Table(name = "RPT_PROGRAM_WISE")
@SuppressWarnings("serial")
public class ProgramWiseReportTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ProgramWiseReportTO")
	@TableGenerator(name = "ProgramWiseReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "GAME_NAME")
	private String gameName;
	
	@Column(name = "START_DATE")
	private String startDate;
	
	@Column(name = "END_DATE")
	private String endDate;
	
	@Column(name = "CURRENT_RANK")
	private int currentRank;
	
	@Column(name = "PLAYED_DATE")
	private String playedDate;
	
	@Column(name = "POINTS_REQUIRED")
	private int pointsRequired;
	
	@Column(name = "NUMBER_OF_TIMES_PLAYED")
	private int numberOfTimesPlayed;
	
	@Column(name = "GAME_REWARDS")
	private String gameRewards;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCurrentRank() {
		return currentRank;
	}

	public void setCurrentRank(int currentRank) {
		this.currentRank = currentRank;
	}

	public String getPlayedDate() {
		return playedDate;
	}

	public void setPlayedDate(String playedDate) {
		this.playedDate = playedDate;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(int pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

	public int getNumberOfTimesPlayed() {
		return numberOfTimesPlayed;
	}

	public void setNumberOfTimesPlayed(int numberOfTimesPlayed) {
		this.numberOfTimesPlayed = numberOfTimesPlayed;
	}

	public String getGameRewards() {
		return gameRewards;
	}

	public void setGameRewards(String gameRewards) {
		this.gameRewards = gameRewards;
	}

	
}
