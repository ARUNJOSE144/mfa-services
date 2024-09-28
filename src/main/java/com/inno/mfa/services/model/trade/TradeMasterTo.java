package com.inno.mfa.services.model.trade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Entity
@Table(name = "TRADE_MASTER")
public class TradeMasterTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "SYMBOL", nullable = false, updatable = false)
	private String symbolName;

	@Column(name = "STRIKE", nullable = false, updatable = false)
	private String strike;

	@Column(name = "TYPE", nullable = true, updatable = false)
	private String callOrPut;

	@Column(name = "SIDE", nullable = true, updatable = false)
	private String buyOrSell;

	@Column(name = "STATUS", nullable = true, updatable = false)
	private Integer status;

	@Column(name = "ABOVE_BELOW", nullable = true, updatable = false)
	private String aboveOrBelow;

	@Column(name = "PRICE")
	private float price;

	@Column(name = "PROFIT_PRICE")
	private float profitPrice;

	@Column(name = "SL_PRICE")
	private float slPrice;

	@Column(name = "QTY", nullable = true)
	private int qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public String getStrike() {
		return strike;
	}

	public void setStrike(String strike) {
		this.strike = strike;
	}

	public String getCallOrPut() {
		return callOrPut;
	}

	public void setCallOrPut(String callOrPut) {
		this.callOrPut = callOrPut;
	}

	public String getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAboveOrBelow() {
		return aboveOrBelow;
	}

	public void setAboveOrBelow(String aboveOrBelow) {
		this.aboveOrBelow = aboveOrBelow;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getProfitPrice() {
		return profitPrice;
	}

	public void setProfitPrice(float profitPrice) {
		this.profitPrice = profitPrice;
	}

	public float getSlPrice() {
		return slPrice;
	}

	public void setSlPrice(float slPrice) {
		this.slPrice = slPrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	/*
	 * @Column(name = "CREATE_DATE", nullable = true, updatable = false) private
	 * Date createDate;
	 */

	/*
	 * @Transient private int rowCount;
	 * 
	 * @Transient private int page;
	 */

}