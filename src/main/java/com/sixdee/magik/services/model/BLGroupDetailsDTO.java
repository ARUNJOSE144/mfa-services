/**
 * 
 */
package com.sixdee.magik.services.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Vinay Kariyappa
 *
 *         Nov 15, 2018
 */
@JsonInclude(Include.NON_NULL)
public class BLGroupDetailsDTO {

	private Integer id;
	private String name;
	private String mode;
	private String groupId;
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
