package com.sixdee.magik.services.model;

import java.util.List;

import lombok.Data;

/**
 * @author: Arun Jose
 * @Date : March, 2021
 */
@Data
public class CommonRespTo<T> {

	private static final long serialVersionUID = 1L;
	private int resultCode;
	private String responseMsg;
	private T data;
	private List<T> list;

}