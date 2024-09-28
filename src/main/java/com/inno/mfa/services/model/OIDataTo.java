package com.inno.mfa.services.model;

import java.util.List;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
public class OIDataTo {

	private int id;
	private String Data;
	private String symbol;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	

}