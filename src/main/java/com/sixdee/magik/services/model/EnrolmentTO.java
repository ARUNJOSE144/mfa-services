package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;




/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 *
 */
@Entity
@Table(name = "RPT_ENROLMENT")
@SuppressWarnings("serial")
public class EnrolmentTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EnrolmentTO")
	@TableGenerator(name = "EnrolmentTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	

	@Column(name = "ENROLMENT_DATE")
	private String enrolmentDate;
	
	@Column(name = "SUBSCRIBER_NO")
	private int subscriberNumber;
	
	@Column(name = "SUBSCRIBER_EMAIL_ID")
	private String subscriberEmailID;
	
	@Column(name = "CURRENT_RANK")
	private int currentRank;
	
	@Column(name = "CURRENT_POINT_BALANCE")
	private int currentpointBalance;
	
	@Column(name = "NO_OF_BADGES")
	private int numberOfBadges;
	
	@Column(name = "LOYALTY_SEGMENT")
	private String loyaltySegment;
	
	@Column(name = "NO_OF_GAMES")
	private int numberOfGames;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getEnrolmentDate() {
		return enrolmentDate;
	}

	public void setEnrolmentDate(String enrolmentDate) {
		this.enrolmentDate = enrolmentDate;
	}

	public int getSubscriberNumber() {
		return subscriberNumber;
	}

	public void setSubscriberNumber(int subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	public String getSubscriberEmailID() {
		return subscriberEmailID;
	}

	public void setSubscriberEmailID(String subscriberEmailID) {
		this.subscriberEmailID = subscriberEmailID;
	}

	public int getCurrentRank() {
		return currentRank;
	}

	public void setCurrentRank(int currentRank) {
		this.currentRank = currentRank;
	}

	public int getCurrentpointBalance() {
		return currentpointBalance;
	}

	public void setCurrentpointBalance(int currentpointBalance) {
		this.currentpointBalance = currentpointBalance;
	}

	public int getNumberOfBadges() {
		return numberOfBadges;
	}

	public void setNumberOfBadges(int numberOfBadges) {
		this.numberOfBadges = numberOfBadges;
	}

	public String getLoyaltySegment() {
		return loyaltySegment;
	}

	public void setLoyaltySegment(String loyaltySegment) {
		this.loyaltySegment = loyaltySegment;
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}
	
	

	

}
