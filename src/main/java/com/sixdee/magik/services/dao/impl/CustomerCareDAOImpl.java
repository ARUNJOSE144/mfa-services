package com.sixdee.magik.services.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixdee.magik.services.dao.CustomerCareDAO;
import com.sixdee.magik.services.model.CustomerCareDTO;
import com.sixdee.magik.services.model.EligibleCampaign;

@Repository
@Transactional
public class CustomerCareDAOImpl implements CustomerCareDAO {

	@Autowired
	private Environment env;

	@Autowired
	//@Qualifier("cdrDataSessionFactory")
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<CustomerCareDTO> getMyBestOffers(CustomerCareDTO customerCareDTO) throws Exception {

		/*
		 * String offers = sendGet(customerCareDTO);
		 * 
		 * if(offers == null) { return null; }
		 */ List<CustomerCareDTO> list = null;
		/*
		 * try { JSONObject xmlJSONObj = XML.toJSONObject(offers); String
		 * jsonPrettyPrintString = xmlJSONObj.toString();
		 * customerCareDTO.setMyBestOffers(jsonPrettyPrintString); list = new
		 * ArrayList<CustomerCareDTO>(); list.add(customerCareDTO); } catch
		 * (JSONException je) { System.out.println(je.toString()); }/}
		 */
		return list;
	}

	public String sendGet(CustomerCareDTO customerCareDTO) throws Exception {

		String url = env.getProperty("reco.mybest.offer.url") + "Recommendation?feature=RECOMMENDATION&msisdn="
				+ customerCareDTO.getMsisdn() + "&category=voice&application=ONLINE";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println(response.toString());
		return response.toString();
	}

	@Override
	public List<CustomerCareDTO> getInfo(CustomerCareDTO customerCareDTO) throws Exception {
		boolean test = true;

		String customerProfiles = null;
		String packagesInfo = null;
		String redeemedPoints = null;
		String transactionHistory = null;

		if (test == true) {
			customerProfiles = "{\"customerProfile\":{\"firstName\":\"Rahul\",\"lastName\":\"Krishnan\",\"activationDate\":\"2020-01-24\",\"dateOfBirth\":\"1985-12-21\",\"customerClass\":\"Silver\",\"accountType\":\"Prepaid\",\"accountStatus\":\"Active\",\"accountNumber\":\"12342131\",\"CRN\":\"CR123123\"},\"deviceProfile\":{\"deviceMake\":\"Nokia\",\"deviceModel\":\"N5\",\"deviceOS\":\"Android\",\"deviceType\":\"SmartPhone\"},\"loyaltyAccount\":[{\"loyaltyId\":\"20202020202121312\",\"loyaltyStatus\":\"Active\",\"loyaltyWallet\":[{\"walletId\":\"NORMAL_WALLET\",\"enrollmentDate\":\"2020-04-01\",\"loyaltyBalance\":{\"unitType\":\"POINTS\",\"unitValue\":200}},{\"expiryBalance\":{\"unitType\":\"POINTS\",\"unitValue\":30,\"expiryDate\":\"2020-08-31\"}}]}]}";
			packagesInfo = "{\"statusDescription\":\"Package Information Retrieved Successfully\",\"statusCode\":\"SC0000\",\"transactionId\":112278555027111,\"timeStamp\":\"14122017140114\",\"packages\":[{\"cost\":10,\"offerType\":\"4\",\"startDate\":\"2029-01-01 06:31:40.0\",\"endDate\":\"2019-12-01 06:40:21.0\",\"packageID\":11,\"packageName\":\"Talktime\",\"priority\":1,\"redeemPoints\":100,\"category\":\"\",\"info\":\"talkTime Offer\",\"rectangleImagePath\":\"\",\"squareImagePath\":\"\",\"termsAndConditions\":\"TEST111\"},{\"cost\":11,\"offerType\":\"4\",\"startDate\":\"2029-01-02 06:31:40.0\",\"endDate\":\"2019-12-07 06:40:21.0\",\"packageID\":11,\"packageName\":\"Talk\",\"priority\":1,\"redeemPoints\":150,\"category\":\"\",\"info\":\"talkTime Offer\",\"rectangleImagePath\":\"\",\"squareImagePath\":\"\",\"termsAndConditions\":\"TEST222\"}]}";
			redeemedPoints = "{\"statusDescription\":\"Redeem Points Success\",\"statusCode\":\"SC0000\",\"transactionId\":112278555027111,\"timeStamp\":\"14122017140114\"}";
			transactionHistory = "{\"usageSummary\":[{\"usageType\":\"VoiceRevenue\",\"summary\":{\"usageValue\":453,\"usageUnit\":\"Amount\",\"DetailedSummary\":[{\"usageValue\":123,\"period\":\"June\"},{\"usageValue\":230,\"period\":\"July\"},{\"usageValue\":100,\"period\":\"August\"}]}},{\"usageType\":\"Topup\",\"summary\":{\"usageValue\":5400,\"usageUnit\":\"Amount\",\"DetailedSummary\":[{\"usageValue\":2000,\"period\":\"June\"},{\"usageValue\":1400,\"period\":\"July\"},{\"usageValue\":2000,\"period\":\"August\"}]}}],\"loyaltySummary\":{\"earnedPoints\":0,\"redeemedPoints\":0,\"transferPoints\":0,\"expiredPoints\":0,\"loyaltyTransactions\":{\"activityDate\":\"2020-01-20\",\"activity\":\"LoyaltyRedemption\",\"prePoints\":10000,\"postPoints\":8000,\"usedPoints\":2000,\"description\":\"Redeem 50 SAR\",\"product\":\"Cash\",\"partner\":\"Self\",\"reqTxnId\":\"1233112\",\"offerType\":\"CashRedemption\"}},\"campaignTransactions\":[{\"actionDate\":\"2020-03-12\",\"campaign\":\"RechargeCampaign\",\"actionKey\":\"RechargeOnceMore\",\"marketingPlan\":\"RechargePlan\",\"typeOfAction\":\"Promotion\",\"creditAmount\":\"\",\"debitAmount\":\"\",\"promotedOfferGroup\":\"P1\",\"offerActivated\":\"\",\"campaignType\":\"Informative\",\"message\":\"Hello World\"}]}";
		} else {
			customerProfiles = sendGet(customerCareDTO);
			packagesInfo = sendGet(customerCareDTO);
			redeemedPoints = sendGet(customerCareDTO);
			transactionHistory = sendGet(customerCareDTO);
		}

		if (customerProfiles == null && packagesInfo == null && redeemedPoints == null && transactionHistory == null) {
			return null;
		}

		List<CustomerCareDTO> list = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

			final JSONObject customerProfilesJson = new JSONObject(customerProfiles);

			final JSONObject customerProfile = customerProfilesJson.getJSONObject("customerProfile");
			final JSONObject deviceProfile = customerProfilesJson.getJSONObject("deviceProfile");
			final JSONArray loyaltyAccount = customerProfilesJson.getJSONArray("loyaltyAccount");

			final JSONObject transactionHistoryJson = new JSONObject(transactionHistory);

			final JSONArray usageSummary = transactionHistoryJson.getJSONArray("usageSummary");
			final JSONObject loyaltySummary = transactionHistoryJson.getJSONObject("loyaltySummary");
			final JSONArray campaignTransactions = transactionHistoryJson.getJSONArray("campaignTransactions");

			final JSONObject packageInfoJson = new JSONObject(packagesInfo);

			final JSONObject redeemedPointsJson = new JSONObject(redeemedPoints);

			customerCareDTO.setCustomerProfile(customerProfile.toString());
			customerCareDTO.setDeviceProfile(deviceProfile.toString());
			customerCareDTO.setLoyaltyAccount(loyaltyAccount.toString());

			customerCareDTO.setPackagesInfo(packageInfoJson.toString());
			customerCareDTO.setRedeemedPoints(redeemedPointsJson.toString());

			customerCareDTO.setUsageSummary(usageSummary.toString());
			customerCareDTO.setLoyaltySummary(loyaltySummary.toString());
			customerCareDTO.setCampaignTransactions(campaignTransactions.toString());

			list = new ArrayList<CustomerCareDTO>();
			list.add(customerCareDTO);

		} catch (JSONException je) {
			System.out.println(je.toString());
		}

		return list;

	}

	@Override
	public List<CustomerCareDTO> getTransactionsHistoryInfo(CustomerCareDTO customerCareDTO) throws Exception {
		boolean test = true;

		String transactionHistory = null;

		if (test == true) {
			transactionHistory = "{\"usageSummary\":[{\"usageType\":\"VoiceRevenue\",\"summary\":{\"usageValue\":453,\"usageUnit\":\"Amount\",\"DetailedSummary\":[{\"usageValue\":123,\"period\":\"June\"},{\"usageValue\":230,\"period\":\"July\"},{\"usageValue\":100,\"period\":\"August\"}]}},{\"usageType\":\"Topup\",\"summary\":{\"usageValue\":5400,\"usageUnit\":\"Amount\",\"DetailedSummary\":[{\"usageValue\":2000,\"period\":\"June\"},{\"usageValue\":1400,\"period\":\"July\"},{\"usageValue\":2000,\"period\":\"August\"}]}}],\"loyaltySummary\":{\"earnedPoints\":0,\"redeemedPoints\":0,\"transferPoints\":0,\"expiredPoints\":0,\"loyaltyTransactions\":{\"activityDate\":\"2020-01-20\",\"activity\":\"LoyaltyRedemption\",\"prePoints\":10000,\"postPoints\":8000,\"usedPoints\":2000,\"description\":\"Redeem 50 SAR\",\"product\":\"Cash\",\"partner\":\"Self\",\"reqTxnId\":\"1233112\",\"offerType\":\"CashRedemption\"}},\"campaignTransactions\":[{\"actionDate\":\"2020-03-12\",\"campaign\":\"RechargeCampaign\",\"actionKey\":\"RechargeOnceMore\",\"marketingPlan\":\"RechargePlan\",\"typeOfAction\":\"Promotion\",\"creditAmount\":\"\",\"debitAmount\":\"\",\"promotedOfferGroup\":\"P1\",\"offerActivated\":\"\",\"campaignType\":\"Informative\",\"message\":\"Hello World\"}]}";
		} else {
			transactionHistory = sendGet(customerCareDTO);
		}

		if (transactionHistory == null) {
			return null;
		}

		List<CustomerCareDTO> list = null;

		try {

			final JSONObject transactionHistoryJson = new JSONObject(transactionHistory);

			final JSONArray usageSummary = transactionHistoryJson.getJSONArray("usageSummary");
			final JSONObject loyaltySummary = transactionHistoryJson.getJSONObject("loyaltySummary");
			final JSONArray campaignTransactions = transactionHistoryJson.getJSONArray("campaignTransactions");

			customerCareDTO.setUsageSummary(usageSummary.toString());
			customerCareDTO.setLoyaltySummary(loyaltySummary.toString());
			customerCareDTO.setCampaignTransactions(campaignTransactions.toString());

			list = new ArrayList<CustomerCareDTO>();
			list.add(customerCareDTO);

		} catch (JSONException je) {
			System.out.println(je.toString());
		}

		return list;

	}

	@Override
	public List<CustomerCareDTO> getBasicInfo(CustomerCareDTO customerCareDTO) throws Exception {
		/*
		 * List<CustomerCareDTO> list = null; Session session = null;
		 *//*
			 * try { session = sessionFactory.getCurrentSession(); String query =
			 * "SELECT SUBSCRIBER_TYPE,LANGUAGE,REGION,ACTIVATION_DATE FROM CDR_BASIC_INFO WHERE MSISDN=:msisdn"
			 * ; SQLQuery sqlQuery = session.createSQLQuery(query);
			 * sqlQuery.setParameter("msisdn", customerCareDTO.getMsisdn()); List<Object[]>
			 * results = (List<Object[]>)sqlQuery.list(); if(results!= null &&
			 * results.size()>0) { list = new ArrayList<CustomerCareDTO>(); for (Object[]
			 * aRow : results) { if(aRow != null && aRow[0]!=null && aRow[1]!=null &&
			 * aRow[2]!=null && aRow[3]!=null) { customerCareDTO.setStatus(true);
			 * customerCareDTO.setSubscriberType((String)aRow[0]);
			 * customerCareDTO.setLanguage((String)aRow[1]);
			 * customerCareDTO.setReagion((String)aRow[2]);
			 * customerCareDTO.setActDate((String)aRow[3]); } } list.add(customerCareDTO); }
			 * } catch (Exception e) { e.printStackTrace(); }
			 */
		return new ArrayList<CustomerCareDTO>();
	}

	@Override
	public List<CustomerCareDTO> getBasicViewInfo(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = new ArrayList<>();

		/*
		 * try { customerCareDTO.setStatus(true);
		 * customerCareDTO.setSubscriberType("Prepaid");
		 * customerCareDTO.setLanguage("English"); customerCareDTO.setArea("Bangalore");
		 * customerCareDTO.setReagion("Indian");
		 * customerCareDTO.setActDate("03/04/2019");
		 * customerCareDTO.setDeviceType("Smart Phone");
		 * customerCareDTO.setDeviceModal("Redmi");
		 * 
		 * list.add(customerCareDTO); } catch (Exception e) { e.printStackTrace(); }
		 */ return list;
	}

	@Override
	public List<CustomerCareDTO> getTransactionsHistory(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = new ArrayList<>();

		/*
		 * try { customerCareDTO.setTransactionDate("01/12/2018");
		 * customerCareDTO.setMarketingPlan("Recharge value Enhancer");
		 * customerCareDTO.setTransactionactionKey("Credit");
		 * customerCareDTO.setCreditAmount("200");
		 * customerCareDTO.setDebitAmount("100");
		 * customerCareDTO.setTypeOfAction("Bonus");
		 * customerCareDTO.setTransactionStatus("Active");
		 * 
		 * list.add(customerCareDTO); } catch (Exception e) { e.printStackTrace(); }
		 */ return list;
	}

	@Override
	public List<CustomerCareDTO> getRevenueDetails(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = new ArrayList<>();

		/*
		 * try { customerCareDTO.setVoiceRevenu(23.54f);
		 * customerCareDTO.setSmsRevenu(13.25f); customerCareDTO.setDataRevenu(12.56f);
		 * customerCareDTO.setVasRevenue(24.56f);
		 * customerCareDTO.setTotalRevenue(73.93f);
		 * customerCareDTO.setRechargeAmount(48.753f);
		 * customerCareDTO.setRechargeCount(136);
		 * 
		 * list.add(customerCareDTO); } catch (Exception e) { e.printStackTrace(); }
		 */
		return list;
	}

	@Override
	public List<CustomerCareDTO> getVoiceDetails(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = new ArrayList<>();

		/*
		 * try { customerCareDTO.setInternalLast(1238);
		 * customerCareDTO.setMocCallLast(8218); customerCareDTO.setMtcCallLast(3213);
		 * customerCareDTO.setOnNetCallLast(1234);
		 * customerCareDTO.setOffNetCallLast(1764);
		 * customerCareDTO.setTotalCallLast(4245);
		 * 
		 * 
		 * customerCareDTO.setInternalCallPenultimate(1213);
		 * customerCareDTO.setMocCallPenultimate(8314);
		 * customerCareDTO.setMtcCallPenultimate(2312);
		 * customerCareDTO.setOnNetCallPenultimate(1134);
		 * customerCareDTO.setOffNetCallPenultimate(1564);
		 * customerCareDTO.setTotalCallPenultimate(3911);
		 * 
		 * customerCareDTO.setInternalCallAntepenultimate(1254);
		 * customerCareDTO.setMocCallAntepenultimate(7452);
		 * customerCareDTO.setMtcCallAntepenultimate(1213);
		 * customerCareDTO.setOnNetCallAntepenultimate(1215);
		 * customerCareDTO.setOffNetCallAntepenultimate(1534);
		 * customerCareDTO.setTotalCallAntepenultimate(3682);
		 * 
		 * list.add(customerCareDTO); } catch (Exception e) { e.printStackTrace(); }
		 */ return list;
	}

	@Override
	public List<CustomerCareDTO> getDataDetails(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = new ArrayList<>();

		/*
		 * try { customerCareDTO.setDataUploadVolumeLast(83569);
		 * customerCareDTO.setDataDownloadVolumeLast(85896);
		 * 
		 * 
		 * customerCareDTO.setDataUploadVolumeLast(74637);
		 * customerCareDTO.setDataDownloadVolumePenultimate(78956);
		 * 
		 * customerCareDTO.setDataUploadVolumeAntepenultimate(74576);
		 * customerCareDTO.setDataDownloadVolumeAntepenultimate(89765);
		 * 
		 * list.add(customerCareDTO);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */ return list;
	}

	@Override
	public List<CustomerCareDTO> getSmsDetails(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = new ArrayList<>();

		/*
		 * try { customerCareDTO.setSmsTotalCountLast(233);
		 * customerCareDTO.setSmsOnNetCountLast(110);
		 * customerCareDTO.setSmsOffNetCountLast(123);
		 * 
		 * customerCareDTO.setSmsTotalCountPenultimate(245);
		 * customerCareDTO.setSmsTotalCountAntepenultimate(103);
		 * customerCareDTO.setSmsOffNetCountPenultimate(142);
		 * 
		 * customerCareDTO.setSmsTotalCountAntepenultimate(253);
		 * customerCareDTO.setSmsOnNetCountPenultimate(99);
		 * customerCareDTO.setSmsOffNetCountPenultimate(154);
		 * 
		 * list.add(customerCareDTO);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */ return list;
	}

	@Override
	public List<CustomerCareDTO> getLoyaltyDetails(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = new ArrayList<>();

		/*
		 * try { customerCareDTO.setLoyaltyId(9980071524d);
		 * customerCareDTO.setLoyaltyActionDate(new Date("18/8/2020"));
		 * customerCareDTO.setLoyaltyCustomerType("Prepaid");
		 * 
		 * customerCareDTO.setCurrentTierName("Platinum");
		 * customerCareDTO.setLoyalityRegion("bangalore");
		 * customerCareDTO.setTotalPointsAccumulated(400);
		 * 
		 * customerCareDTO.setTotalBalanceExpiryPoints(900);
		 * customerCareDTO.setTotalPointsReedemed(2001);
		 * customerCareDTO.setBalancePointsExpiryDate(new Date("22/08/2020"));
		 * customerCareDTO.setTotalPointsTransfered(1001);
		 * 
		 * 
		 * list.add(customerCareDTO);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		return list;
	}

	public CustomerCareDTO getArpuDetails(CustomerCareDTO customerCareDTO) {
		/*
		 * Session session = null; String arpu = null;
		 */ /*
			 * try { session = sessionFactory.getCurrentSession(); String query =
			 * "SELECT VOICEM2ARPU FROM CDR_ARPU_INFO WHERE MSISDN=:msisdn"; SQLQuery
			 * sqlQuery = session.createSQLQuery(query); sqlQuery.setParameter("msisdn",
			 * customerCareDTO.getMsisdn()); List<Integer> list =sqlQuery.list();
			 * for(Integer all:list) { arpu = all!=null?all.toString():"0";
			 * customerCareDTO.setStatus(true); }
			 * 
			 * customerCareDTO.setArpu(arpu); } catch (Exception e) { e.printStackTrace(); }
			 */return customerCareDTO;
	}

	public CustomerCareDTO getEligibleCampaigns(CustomerCareDTO customerCareDTO) {

		/*
		 * Session session = null; List<EligibleCampaign> list = null; EligibleCampaign
		 * eligibleCampaign = null; Map<String, EligibleCampaign> activeCamp = null;
		 *//*
			 * try { session = sessionFactory.getCurrentSession(); String query =
			 * "SELECT CAMP_ID, CAMP_NAME,MESSAGE,FIELD1,FIELD2,FIELD3,FIELD4,FIELD5 FROM CDR_ELIGIBLE_CAMP_INFO WHERE MSISDN=:msisdn"
			 * ; SQLQuery sqlQuery = session.createSQLQuery(query);
			 * sqlQuery.setParameter("msisdn", customerCareDTO.getMsisdn()); List<Object[]>
			 * results = (List<Object[]>)sqlQuery.list(); if(results!= null &&
			 * results.size()>0) { list = new ArrayList<EligibleCampaign>(); activeCamp =
			 * new HashMap<String, EligibleCampaign>(); for (Object[] aRow : results) {
			 * eligibleCampaign = new EligibleCampaign(); if(aRow != null && aRow[0]!=null
			 * && aRow[1]!=null) { customerCareDTO.setStatus(true);
			 * eligibleCampaign.setMarketingPlanId((String)aRow[0]);
			 * eligibleCampaign.setMarketingPlanName((String)aRow[1]);
			 * eligibleCampaign.setMessage((String)aRow[2]);
			 * eligibleCampaign.setField1((String)aRow[3]);
			 * eligibleCampaign.setField2((String)aRow[4]);
			 * eligibleCampaign.setField3((String)aRow[5]);
			 * eligibleCampaign.setField4((String)aRow[6]);
			 * eligibleCampaign.setField5((String)aRow[7]); list.add(eligibleCampaign);
			 * activeCamp.put((String)aRow[0], eligibleCampaign); } }
			 * customerCareDTO.setActiveCamp(activeCamp);
			 * customerCareDTO.setEligibleCampaign(list); } } catch (Exception e) {
			 * e.printStackTrace(); }
			 */return customerCareDTO;
	}

	public CustomerCareDTO getRechargeValues(CustomerCareDTO customerCareDTO) {
		/*
		 * Session session = null; try { session = sessionFactory.getCurrentSession();
		 * String query =
		 * " SELECT RECHARGE_COUNT,RECHARGE_AMOUNT,VOICE_REVENUE,VOICE_CUSTOMARE_CALLS,VOICE_BILL_AMOUNT,   "
		 * +
		 * "VOICE_FREE_MINUTES,VOICE_CAHRGE_AMOUNT,VOICE_COUNT_INTERNATIONAL,MOC_CALLS,MTC_CALLS,"
		 * + "OFF_NET_CALLS,ON_NET_CALLS,SMS_OFFER_COUNT,SMS_COUNT,SMS_REVENUE," +
		 * "SMS_BONUS_COUNT,MO_SMS_CALLS,DATA_REVENUE,DATA_AMOUNT FROM SUMMARIZED_INFO WHERE  MSISDN=:msisdn"
		 * ; SQLQuery sqlQuery = session.createSQLQuery(query);
		 * sqlQuery.setParameter("msisdn", customerCareDTO.getMsisdn()); List<Object>
		 * results = (List<Object>)sqlQuery.list(); if(results!= null &&
		 * results.size()>0) { customerCareDTO.setStatus(true); for (Object aRow :
		 * results) {
		 * 
		 * } } } catch (Exception e) { e.printStackTrace(); }
		 */
		return customerCareDTO;
	}

	public List<CustomerCareDTO> getCustomerInsights(CustomerCareDTO customerCareDTO) {

		List<CustomerCareDTO> list = null;

		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			String query = "SELECT SUB_SEGMENT, NUMBER_OF_SUBSCRIBERS, " + "AVG_AGE, AVG_REVENUE_PER_MONTH, "
					+ "AVG_ACC_BALANCE, MAX_AGE_IN_NETWORK, " + "MIN_AGE_IN_NETWORK, AVG_INT_CALL_PER_DAY, "
					+ "AVG_LOCAL_CALL_PER_DAY, AVG_OUT_CALL_PER_DAY, " + "AVG_VOL_PER_DAY, AVG_BAL_RECHARGE, "
					+ "AVG_MAX_REFILL, AVG_MIN_REFILL, AVG_RECHARGE_VALUE_PER_MON, "
					+ "AVG_RECHARGES_PER_MONTH, AVG_TIME_BW_RECHARGES, "
					+ "AVG_OUT_SMS_PER_DAY, SEGMENT, REST_SEGMENT, AVG_RESP_RATE, "
					+ "SMS_COUNT, MMS_COUNT, EMAIL_COUNT, USSD_COUNT, AVG_CHANNEL_RESPONSE, "
					+ "SOCIAL_COUNT FROM CUSTOMERCARE_INSIGHTS WHERE MSISDN=:msisdn";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("msisdn", customerCareDTO.getMsisdn());

			@SuppressWarnings("unchecked")
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				list = new ArrayList<CustomerCareDTO>();
				for (Object[] row : results) {
					customerCareDTO.setStatus(true);
					customerCareDTO.setSubSegment(row[0] != null ? (String) row[0] : "");
					customerCareDTO.setNumberOfSubscribers(row[1] != null ? (String) row[1] : "");
					customerCareDTO.setAvAge(row[2] != null ? (String) row[2] : "");
					customerCareDTO.setAvgRevenuePerMonth(row[3] != null ? (String) row[3] : "");
					customerCareDTO.setAvgAccBalance(row[4] != null ? (String) row[4] : "");
					customerCareDTO.setMaxAgeInNetwork(row[5] != null ? (String) row[5] : "");
					customerCareDTO.setMinAgeInNetwork(row[6] != null ? (String) row[6] : "");
					customerCareDTO.setAvgIntCallPerDay(row[7] != null ? (String) row[7] : "");
					customerCareDTO.setAvgLocalCallPerDay(row[8] != null ? (String) row[8] : "");
					customerCareDTO.setAvgOutCallPerDay(row[9] != null ? (String) row[9] : "");
					customerCareDTO.setAvgVolPerDay(row[10] != null ? (String) row[10] : "");
					customerCareDTO.setAvgBalRecharge(row[11] != null ? (String) row[11] : "");
					customerCareDTO.setAvgMaxRefill(row[12] != null ? (String) row[12] : "");
					customerCareDTO.setAvgMinRefill(row[13] != null ? (String) row[13] : "");
					customerCareDTO.setAvgRechargeValuePerMon(row[14] != null ? (String) row[14] : "");
					customerCareDTO.setAvgRechargesPerMonth(row[15] != null ? (String) row[15] : "");
					customerCareDTO.setAvgTimeBwRecharges(row[16] != null ? (String) row[16] : "");
					customerCareDTO.setAvgOutSmsPerDay(row[17] != null ? (String) row[17] : "");
					customerCareDTO.setSegment(row[18] != null ? (String) row[18] : "");
					customerCareDTO.setRestSegment(row[19] != null ? (String) row[19] : "");
					customerCareDTO.setAvgRespRate(row[20] != null ? (String) row[20] : "");
					customerCareDTO.setInsightSmsCount(row[21] != null ? (String) row[21] : "");
					customerCareDTO.setMmsCount(row[22] != null ? (String) row[22] : "");
					customerCareDTO.setEmailCount(row[23] != null ? (String) row[23] : "");
					customerCareDTO.setUssdCount(row[24] != null ? (String) row[24] : "");
					customerCareDTO.setAvgChannelResponse(row[25] != null ? (String) row[25] : "");
					customerCareDTO.setSocialCount(row[26] != null ? (String) row[26] : "");

				}
				list.add(customerCareDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
