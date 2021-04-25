package com.sixdee.magik.services.model;

public class TelegramPaginationDTO {
	
	private int offset;
	
	private int length;
	
	private String type;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TelegramPaginationDTO [offset=" + offset + ", length=" + length + ", type=" + type + "]";
	}
	
	

}
