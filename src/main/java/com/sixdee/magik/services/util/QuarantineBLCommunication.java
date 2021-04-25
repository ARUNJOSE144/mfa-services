package com.sixdee.magik.services.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.sixdee.magik.services.model.QuarantineBLCommunicationTO;

/**
 * @author Ramesh Babu Cheerla
 * @Date : August, 2020
 *
 */

public class QuarantineBLCommunication {

	private Logger logger = Logger.getLogger(QuarantineBLCommunication.class);

	/*@Value("${BL_XML_URL}")
	String BL_XML_URL;*/

	@Value("${QUARANTINE_DATE_FORMAT}")
	String QUARANTINE_DATE_FORMAT;

	String status = "SC000";
	String marketingID=null;
	String BL_XML_URL=null;

	

	public String reqXMLToBL(QuarantineBLCommunicationTO blComnTo) {

		System.out.println("====================== Request sending to BL Process=========================");
		System.out.println("QuarantineBLCommunicationTO :::  " + blComnTo);
		// System.out.println("Special Date getSpecialDates :: " +
		// blComnTo.getWeekDays());
		System.out.println("MarketingPlanIds :: " + blComnTo.getMarketingPlanIds());

		String status = "SC0000";
		marketingID=blComnTo.getMarketingPlanIds();
		BL_XML_URL=blComnTo.getUrlValue();
		System.out.println("UrlValue  ::  " + BL_XML_URL );

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		Param param;
		MultiParam multiParam;
		ArrayList<MultiParam> multiParamList;

		Request request = new Request();
		request.setFeatureId("QUARANTINE_POLICY_EXECUTION");
		request.setApp_name("CMS");
		request.setMsg_dest("DEST");
		request.setMsg_origin("ORI");
		request.setPassword("6D");
		request.setUsername("6D");
		request.setReq_tran_id("100");
		request.setTimestamp(df.format(new Date()));

		Data data = new Data();
		request.setData(data);

		Detail detail = new Detail();
		data.setDetailReq(detail);

		ArrayList<Param> paramList;
		paramList = new ArrayList<Param>();
		detail.setLists(paramList);

		param = new Param();
		param.setName("QuarantinePolicyId");
		param.setValue(blComnTo.getPolicyId() + "");
		paramList.add(param);

		if (blComnTo.getPolicyName() != null && !blComnTo.getPolicyName().trim().equalsIgnoreCase("")) {
			param = new Param();
			param.setName("QuarantinePolicyName");
			param.setValue(blComnTo.getPolicyName());
			paramList.add(param);
		}

		if (blComnTo.getPolicyType() != null && !blComnTo.getPolicyType().trim().equalsIgnoreCase("")) {
			param = new Param();
			param.setName("PolicyType");
			param.setValue(blComnTo.getPolicyType());
			paramList.add(param);

			if (blComnTo.getPolicyType().trim().equalsIgnoreCase("GLOBAL_DND") && blComnTo.getDndStartTime() != null
					&& blComnTo.getDndEndTime() != null) {
				param = new Param();
				param.setName("Time");
				param.setValue(blComnTo.getDndStartTime() + "-" + blComnTo.getDndEndTime());
				paramList.add(param);
			}
			if (blComnTo.getPolicyType().trim().equalsIgnoreCase("GLOBAL_SPECIAL_DATE_CONTROL")
					&& (blComnTo.getSpecialDates() != null)) {
				if (blComnTo.getStatusValueDate() == "0") {
					System.out.println("Inside Single Request Special Date" + blComnTo.getSpecialDates());
					String finalDate = null;

					try {
						SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
						Date date2 = formatter2.parse(blComnTo.getSpecialDates());
						SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
						finalDate = newFormat.format(date2);
						// System.out.println("finalDate :: " + finalDate);
					} catch (ParseException exp) {
						exp.printStackTrace();
					}

					param = new Param();
					param.setName("Dates");
					param.setValue(finalDate);
					paramList.add(param);
				}
			}

			if (blComnTo.getPolicyType().trim().equalsIgnoreCase("GLOBAL_SPECIAL_DATE_CONTROL")
					&& (blComnTo.getSpecialDates() != null)) {
				if (blComnTo.getStatusValueDate() == "1") {
					System.out.println("Inside File  Request Special Date");
					Date dateSet;
					String finalDate = null;
					String testData1 = null;
					int j;
					String startDate = blComnTo.getSpecialDates();
					System.out.println("File Date ::: " + startDate);
					String listDate = null;
					String[] arrayDate = startDate.split(",");
					ArrayList<String> list = new ArrayList<String>();

					for (int i = 0; i < arrayDate.length; i++) {
						try {
							DateFormat formatterDateNew = new SimpleDateFormat("yyyy-MM-dd");
							dateSet = (Date) formatterDateNew.parse(arrayDate[i]);
							SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
							finalDate = newFormat.format(dateSet);
							String[] words = finalDate.split(" ");

							for (j = 0; j <= words.length - 1; j++) {
								list.add(words[j]);
							}
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						listDate = list.toString();
						String testData = listDate.replace("[", "");
						testData1 = testData.replace("]", "");
					}

					param = new Param();
					param.setName("Dates");
					param.setValue(testData1);
					paramList.add(param);
				}
			}

			if (blComnTo.getPolicyType().trim().equalsIgnoreCase("GLOBAL_DAY_CONTROL")
					&& blComnTo.getWeekDays() != null) {
				param = new Param();
				param.setName("Days");
				param.setValue(blComnTo.getWeekDays());
				paramList.add(param);
			}
		}

		/*
		 * param = new Param(); param.setName("ServiceDetails"); paramList.add(param);
		 * 
		 * multiParamList = new ArrayList<MultiParam>();
		 * param.setMultiParams(multiParamList); multiParam = new MultiParam();
		 * 
		 * param = new Param(); param.setName("ServiceId"); param.setValue("5527");
		 * 
		 * ArrayList<Param> paramLists = new ArrayList<Param>(); paramLists.add(param);
		 * multiParam.setParams(paramLists); multiParamList.add(multiParam);
		 */

		if (blComnTo.getMarketingPlanIds() != null && !blComnTo.getMarketingPlanIds().trim().equalsIgnoreCase("")) {

			param = new Param();
			param.setName("ServiceDetails");
			paramList.add(param);

			if (blComnTo.getMarketingPlanIds().split(",").length == 1
					&& blComnTo.getMarketingPlanIds().split(",")[0].trim().equalsIgnoreCase("-1")) {
				param.setValue("ALL");
			} else {
				multiParamList = new ArrayList<MultiParam>();
				param.setMultiParams(multiParamList);

				String[] mktplan = marketingID.split(",");

				for (int i = 0; i < mktplan.length; i++) {
					multiParam = new MultiParam();
					param = new Param();
					param.setName("ServiceId");
					param.setValue(mktplan[i]);

					ArrayList<Param> paramLists = new ArrayList<Param>();
					paramLists.add(param);
					multiParam.setParams(paramLists);
					multiParamList.add(multiParam);
				}
			}
		}

		try {
			System.out.println("============ Request convert to XML ============== ");

			String requestxml = RuleStatusParser.getRuleXStream().toXML(request);

			System.out.println("============ Request XML to BL ============== " + requestxml);

			//BL_XML_URL = "http://10.0.0.69:9575/RuleEngine/HttpAdapter";
			String resp = connecting(requestxml, BL_XML_URL);
			System.out.println(" The URL is ------------ " + BL_XML_URL);

			System.out.println("============ Responce XML From BL ============== ");
			logger.info(resp);
			System.out.println();
			// System.out.println("resp code before if :: " + resp);

			if (resp != null && !resp.trim().equalsIgnoreCase("")) {

				try {
					Response respReq = ((Response) RuleStatusParser.getRuleXStream().fromXML(resp));
					// logger.info("Responce code ---------> : " + respReq.getRespCode());
					System.out.println("Responce code ---------> : " + respReq.getRespCode());

					if (respReq.getRespCode().contains("SC0000")) {
						status = "SC0000";
					} else {
						status = "SC0001";
					}

					System.out.println("Status Value :: " + status);

				} catch (Exception e) {
					status = "SC0001";
					System.out.println("Exception on getting BL responce");
					System.out.println("Responce code >>>> : " + status);
					logger.error("RespCode -->" + e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public String connecting(String xmlRequest, String Url) {

		if (xmlRequest != null && !xmlRequest.equalsIgnoreCase("")) {
			URL url = null;
			HttpURLConnection connection = null;
			OutputStreamWriter out = null;
			InputStream inputStream = null;
			StringBuffer sb = new StringBuffer();

			try {
				// System.out.println("Request ............................ ");
				// System.out.println(xmlRequest);
				url = new URL(Url);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setReadTimeout(10000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setConnectTimeout(10000);
				out = new OutputStreamWriter(connection.getOutputStream());

				out.write(xmlRequest);
				out.flush();

				inputStream = new BufferedInputStream(connection.getInputStream(), 32 * 1024);
				sb = new StringBuffer();
				int character = inputStream.read();

				while (character != -1) {
					sb.append((char) character);
					character = inputStream.read();
				}

			} catch (Exception e) {
				logger.info("EXCEPTION == == == " + e);
			} finally {
				xmlRequest = null;
				try {
					if (inputStream != null) {
						inputStream.close();
						inputStream = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (out != null) {
						out.close();
						out = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (connection != null)
						connection.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		} else {
			return null;
		}
	}
}
