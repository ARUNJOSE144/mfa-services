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

import org.springframework.format.annotation.DateTimeFormat;



/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 *
 */
@Entity
@Table(name = "RPT_LEADER")
public class LeaderReportTO {
	

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LeaderReportTO")
	@TableGenerator(name = "LeaderReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "PLAYER_NAME")
	private String playerName;
	
	@Column(name = "PLAYER_REGION")
	private String playerRegion;
	
	@Column(name = "CURRENT_RANK")
	private int currentRank;
	
	@Column(name = "GAME_AGE")
	private String gameAge;
	
	
	@Column(name = "GAME_DATE")
	private String gameDate;


	public int getAutoId() {
		return autoId;
	}


	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public String getPlayerRegion() {
		return playerRegion;
	}


	public void setPlayerRegion(String playerRegion) {
		this.playerRegion = playerRegion;
	}


	public int getCurrentRank() {
		return currentRank;
	}


	public void setCurrentRank(int currentRank) {
		this.currentRank = currentRank;
	}


	public String getGameAge() {
		return gameAge;
	}


	public void setGameAge(String gameAge) {
		this.gameAge = gameAge;
	}


	public String getGameDate() {
		return gameDate;
	}


	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}

	
	
}
