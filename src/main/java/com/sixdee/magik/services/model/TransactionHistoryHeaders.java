package com.sixdee.magik.services.model;

public class TransactionHistoryHeaders {
	public TransactionHistoryHeaders() {
		super();
	}

	public TransactionHistoryHeaders(String requestId, String source, String key, String keyType, String keyword,
			TransactionFilter transactionFilter, TimeRange timeRange, Page page) {
		super();
		this.requestId = requestId;
		this.source = source;
		this.key = key;
		this.keyType = keyType;
		this.keyword = keyword;
		this.transactionFilter = transactionFilter;
		this.timeRange = timeRange;
		this.page = page;
	}

	private String requestId;
	private String source;
	private String key;
	private String keyType;
	private String keyword;
	private TransactionFilter transactionFilter;
	private TimeRange timeRange;
	private Page page;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public TransactionFilter getTransactionFilter() {
		return transactionFilter;
	}

	public void setTransactionFilter(TransactionFilter transactionFilter) {
		this.transactionFilter = transactionFilter;
	}

	public TimeRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}

class Page {
	String offset;
	String limit;

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
}

class TimeRange {
	String fromDate;
	String toDate;
	String numberOfMonths;
	String numberOfTransactions;

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

	public String getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(String numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public String getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public void setNumberOfTransactions(String numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}
}

class TransactionFilter {
	String filterFild;
	String isDetailed;
	String isSummary;
	GroupBy groupBy;

	public String getFilterFild() {
		return filterFild;
	}

	public void setFilterFild(String filterFild) {
		this.filterFild = filterFild;
	}

	public String getIsDetailed() {
		return isDetailed;
	}

	public void setIsDetailed(String isDetailed) {
		this.isDetailed = isDetailed;
	}

	public String getIsSummary() {
		return isSummary;
	}

	public void setIsSummary(String isSummary) {
		this.isSummary = isSummary;
	}

	public GroupBy getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(GroupBy groupBy) {
		this.groupBy = groupBy;
	}

}

class GroupBy {
	String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
