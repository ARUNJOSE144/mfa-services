package com.sixdee.magik.services.model;

import java.util.List;

public class PaginationDTO<T> {

	public int page;
	public int dataTotalSize;
	public int firstRecord;
	public int recordCount;

	public String searchKey1;
	public String searchKey2;
	public String searchKey3;

	public String sortKey1;
	public String sortKey2;
	public String sortKey3;

	private List<T> data;
	private T obj;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getDataTotalSize() {
		return dataTotalSize;
	}

	public void setDataTotalSize(int dataTotalSize) {
		this.dataTotalSize = dataTotalSize;
	}

	public int getFirstRecord() {
		return firstRecord;
	}

	public void setFirstRecord(int firstRecord) {
		this.firstRecord = firstRecord;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getSearchKey1() {
		return searchKey1;
	}

	public void setSearchKey1(String searchKey1) {
		this.searchKey1 = searchKey1;
	}

	public String getSearchKey2() {
		return searchKey2;
	}

	public void setSearchKey2(String searchKey2) {
		this.searchKey2 = searchKey2;
	}

	public String getSearchKey3() {
		return searchKey3;
	}

	public void setSearchKey3(String searchKey3) {
		this.searchKey3 = searchKey3;
	}

	public String getSortKey1() {
		return sortKey1;
	}

	public void setSortKey1(String sortKey1) {
		this.sortKey1 = sortKey1;
	}

	public String getSortKey2() {
		return sortKey2;
	}

	public void setSortKey2(String sortKey2) {
		this.sortKey2 = sortKey2;
	}

	public String getSortKey3() {
		return sortKey3;
	}

	public void setSortKey3(String sortKey3) {
		this.sortKey3 = sortKey3;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "PaginationDTO [page=" + page + ", dataTotalSize=" + dataTotalSize + ", firstRecord=" + firstRecord
				+ ", recordCount=" + recordCount + ", searchKey1=" + searchKey1 + ", searchKey2=" + searchKey2
				+ ", searchKey3=" + searchKey3 + ", sortKey1=" + sortKey1 + ", sortKey2=" + sortKey2 + ", sortKey3="
				+ sortKey3 + ", data=" + data + ", obj=" + obj + "]";
	}

}
