package com.inno.mfa.services.adaptor;

import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inno.mfa.services.dao.TokenDAO;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Component
public class Cache {

	public static LinkedHashMap<String, String> propertiesMap = new LinkedHashMap<String, String>();

	@Autowired
	TokenDAO tokenDAO;

	public Cache() {
	}

	@PostConstruct
	void init() {

		try {
			// Loading the sessions from DB while starting the system
			tokenDAO.loadExixstingSessionsFromDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
