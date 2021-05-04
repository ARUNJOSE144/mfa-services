package com.inno.mfa.services.model;

import java.util.List;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
@Data
public class PaginationTo<T> {

	private int recordCount;
	private int firstRecord;
	private int dataTotalSize;
	
	private String searchKey1;
	private String searchKey2;
	private String searchKey3;

	private T data;
	private List<T> list;

}