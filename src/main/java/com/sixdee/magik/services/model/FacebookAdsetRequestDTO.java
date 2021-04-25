package com.sixdee.magik.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FacebookAdsetRequestDTO {

	private int start_minute;
	private int end_minute;
	private List<Integer> days;
	
}
