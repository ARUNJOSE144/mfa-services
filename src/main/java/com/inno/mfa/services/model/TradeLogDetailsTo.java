package com.inno.mfa.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Entity
@Table(name = "TRADE_LOG_DETAILS")
public class TradeLogDetailsTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "TRADE_LOG_ID", nullable = false)
	private int tradeLogId;

	@Column(name = "SYMBOL_ID", nullable = false)
	private int symbol;

	@Column(name = "PRE_OPEN", nullable = true)
	private String preOpen;

	@Column(name = "FIRST_HALF", nullable = true)
	private String firstHalf;

	@Column(name = "SECOND_HALF", nullable = true)
	private String secondHalf;

	@Column(name = "COMMENTS", nullable = true)
	private String comments;

	@Transient
	private TradeLogImageTo tradeLogImageTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTradeLogId() {
		return tradeLogId;
	}

	public void setTradeLogId(int tradeLogId) {
		this.tradeLogId = tradeLogId;
	}

	public int getSymbol() {
		return symbol;
	}

	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}

	public String getPreOpen() {
		return preOpen;
	}

	public void setPreOpen(String preOpen) {
		this.preOpen = preOpen;
	}

	public String getFirstHalf() {
		return firstHalf;
	}

	public void setFirstHalf(String firstHalf) {
		this.firstHalf = firstHalf;
	}

	public String getSecondHalf() {
		return secondHalf;
	}

	public void setSecondHalf(String secondHalf) {
		this.secondHalf = secondHalf;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public TradeLogImageTo getTradeLogImageTo() {
		return tradeLogImageTo;
	}

	public void setTradeLogImageTo(TradeLogImageTo tradeLogImageTo) {
		this.tradeLogImageTo = tradeLogImageTo;
	}

}