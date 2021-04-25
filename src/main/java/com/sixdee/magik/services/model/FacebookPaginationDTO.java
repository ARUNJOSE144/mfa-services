package com.sixdee.magik.services.model;

public class FacebookPaginationDTO {
	
	private FacebookPaginationCursorsDTO cursors;

	private String next;
	
	private String previous;

	public FacebookPaginationCursorsDTO getCursors() {
		return cursors;
	}

	public void setCursors(FacebookPaginationCursorsDTO cursors) {
		this.cursors = cursors;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	@Override
	public String toString() {
		return "FacebookPaginationDTO [cursors=" + cursors + ", next=" + next + ", previous=" + previous + "]";
	}
	
	
}
