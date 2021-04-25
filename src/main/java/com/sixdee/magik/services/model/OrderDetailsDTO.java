package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.List;

public class OrderDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderID;
	private String itemNumber;
	private int quantity;
	private String orderDate;
	private String expiryDate;
	private int points;
	private String itemName;
	private String orderStatus;
	private String createDate;	
	private String details;
	private String activity;
	private List orderLists;	
	private String fromDate;
	private String toDate;
	private String subscriberNum;
	private int noOfTransactions;
	private int noOfMonth;
	private String	rowCount;
	private int	totalPageNum;
	private boolean	navigation;
	private String[] deliverIDs;	
	private String status;
	private String statusCode;
	private boolean isSearch=false;
	private boolean isDeliver=false;
	
	public boolean isSearch() {
		return isSearch;
	}
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}
	public boolean isDeliver() {
		return isDeliver;
	}
	public void setDeliver(boolean isDeliver) {
		this.isDeliver = isDeliver;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public List getOrderLists() {
		return orderLists;
	}
	public void setOrderLists(List orderLists) {
		this.orderLists = orderLists;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSubscriberNum() {
		return subscriberNum;
	}
	public void setSubscriberNum(String subscriberNum) {
		this.subscriberNum = subscriberNum;
	}
	public int getNoOfTransactions() {
		return noOfTransactions;
	}
	public void setNoOfTransactions(int noOfTransactions) {
		this.noOfTransactions = noOfTransactions;
	}
	public int getNoOfMonth() {
		return noOfMonth;
	}
	public void setNoOfMonth(int noOfMonth) {
		this.noOfMonth = noOfMonth;
	}
	public String getRowCount() {
		return rowCount;
	}
	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public boolean isNavigation() {
		return navigation;
	}
	public void setNavigation(boolean navigation) {
		this.navigation = navigation;
	}
	public String[] getDeliverIDs() {
		return deliverIDs;
	}
	public void setDeliverIDs(String[] deliverIDs) {
		this.deliverIDs = deliverIDs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
