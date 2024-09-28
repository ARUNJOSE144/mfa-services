package com.inno.mfa.services.model;

import java.util.List;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

public class PaginationTo<T> {

	private int recordCount;
	private int firstRecord;
	private int dataTotalSize;
	
	private String searchKey1;
	private String searchKey2;
	private String searchKey3;

	private T data;
	private List<T> list;
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getFirstRecord() {
		return firstRecord;
	}
	public void setFirstRecord(int firstRecord) {
		this.firstRecord = firstRecord;
	}
	public int getDataTotalSize() {
		return dataTotalSize;
	}
	public void setDataTotalSize(int dataTotalSize) {
		this.dataTotalSize = dataTotalSize;
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}