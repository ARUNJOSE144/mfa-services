package com.sixdee.magik.services.model;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author ramesh.cheerla
 *
 */

public class GeoTreeJsonTO {

	private int id;
	private String text;
	private int category;
	private int parentId;
	private GeoTreeJsonTO nodes[];
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

//	public List<GeoTreeJsonTO> getNodes() {
//		return nodes;
//	}
//
//	public void setNodes(List<GeoTreeJsonTO> nodes) {
//		this.nodes = nodes;
//	}
	
	

	
	public void setNodes(GeoTreeJsonTO[] nodes) {
		this.nodes = nodes;
	}
	
	public GeoTreeJsonTO[] getNodes() {
		return nodes;
	}

	@Override
	public String toString() {
		return "GeoTreeJsonTO [id=" + id + ", text=" + text + ", category=" + category + ", parentId=" + parentId
				+ ", nodes=" + Arrays.toString(nodes) + "]";
	}

	

	

}
