package com.sixdee.magik.services.dao.impl;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		doSomethingAfterStartup();
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public static void doSomethingAfterStartup() {
		System.out.println("===========================");
		System.out.println(
				"hello world, I have just started up-- Kindly uncomment "
				+ "\"@EventListener(ApplicationReadyEvent.class)\" \n from below classes for cache enabling in PRODUCTION Environment ");
	    
	    System.out.println("Cahce Class Name : StatusCodeDAOImpl");
	    System.out.println("===========================");
	}

}
