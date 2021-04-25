package com.sixdee.magik.services.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.DemoExternalModuleDao;
import com.sixdee.magik.services.model.DemoExternalModuleTO;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */
@Repository
public class DemoExternalModuleDaoImpl implements DemoExternalModuleDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	private Environment env;

	@Override
	public DemoExternalModuleTO getActiveOffer() {
		String status = "1";
		String activeOfferUrl = env.getProperty("activeOffer");

		HttpURLConnection connection = null;
		DemoExternalModuleTO activeOffer = new DemoExternalModuleTO();

		try {

			URL myURL = new URL(activeOfferUrl);
			System.out.println("activeOfferUrl  " + activeOfferUrl.toString());
			connection = (HttpURLConnection) myURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder results = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				results.append(line);
			}
			activeOffer.setOutputData(results.toString());
			connection.disconnect();
			System.out.println("output   ::  " + activeOffer.getOutputData().toString());

		} catch (Exception e) {
			status = "1";
			activeOffer.setStatus(status);
			System.out.println("Exception On Active Offer  Side :::::");
			e.printStackTrace();
		}
		return activeOffer;
	}

}
