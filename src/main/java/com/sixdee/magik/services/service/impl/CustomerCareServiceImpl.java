package com.sixdee.magik.services.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sixdee.imp.service.PointManagement;

import com.sixdee.imp.service.servicedto.req.xsd.AccountValidationCredentialsDTO;

import com.sixdee.imp.service.servicedto.resp.xsd.PackageInfoDTO;
import com.sixdee.imp.service.servicedto.resp.xsd.ResponseDTO;
import com.sixdee.magik.services.dao.CustomerCareDAO;
import com.sixdee.magik.services.model.CustomerCareDTO;
import com.sixdee.magik.services.model.CustomerCareHeaders;
import com.sixdee.magik.services.model.CustomerProfileHeaders;
import com.sixdee.magik.services.model.TransactionHistoryHeaders;
import com.sixdee.magik.services.service.CustomerCareService;

@Service
@Transactional
public class CustomerCareServiceImpl implements CustomerCareService {
	@Autowired
	private CustomerCareDAO customerCareDAO;

	@Override
	public String getDashboardDetails(CustomerCareHeaders customerCareHeaders) throws Exception {

		List<CustomerCareDTO> list = null;
		String listString = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		RestTemplate restTemplate = new RestTemplate();
		if (null != customerCareHeaders && null != customerCareHeaders.getFeature()) {

			HttpEntity<CustomerProfileHeaders> request = new HttpEntity<>(
					customerCareHeaders.getCustomerProfileHeaders(), headers);
			HttpEntity<TransactionHistoryHeaders> requestTransaction = new HttpEntity<>(
					customerCareHeaders.getTransactionHistoryHeaders(), headers);
			// Add the Jackson Message converter
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

			// Note: here we are making this converter to process any kind of response,
			// not only application/*json, which is the default behaviour
			converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_PLAIN));
			restTemplate.getMessageConverters().add(converter);

			
			ObjectMapper mapper  = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(request);
			System.err.println(jsonString);
			System.err.println("------------------"+customerCareHeaders.getFeature()+"--------------------");
			switch (customerCareHeaders.getFeature()) {

		/*	case "CustomerProfile":
				listString = "{CustomerProfile:"
						+ restTemplate.postForObject("http://10.0.0.95:8095/LMS/CustomProfile", request, String.class)
						+ "}";
				break;
			case "TransactionHistory":
				listString = "{TransactionHistory:" + restTemplate.postForObject(
						"http://10.0.0.95:8095/LMS/GetTransaction", requestTransaction, String.class) + "}";
				break;*/
				
			case "BasicView":
				
				/*listString = "{\"CustomerProfile\":"
						+ restTemplate.postForObject("http://10.0.0.95:8095/LMS/CustomProfile", request, String.class)
						+ ",";
				System.err.println("http://10.0.0.95:8095/---");
				System.err.println("Called Service:1=/LMS/CustomProfile"+listString);
				
				listString = listString + "\"TransactionHistory\":" + restTemplate.postForObject(
						"http://10.0.0.95:8095/LMS/GetTransaction", requestTransaction, String.class) + "}";
				System.err.println("Called Service:2=/LMS/GetTransaction "+listString);*/
				
				
				listString=temp_JsonResponse("BasicView");
				
				
				break;

			/*case "redeemPoints":
				PointManagement pointManagement = new PointManagement();

				com.sixdee.imp.service.servicedto.req.xsd.RedeemDTO redeemDTO = new com.sixdee.imp.service.servicedto.req.xsd.RedeemDTO();
				AccountValidationCredentialsDTO accountValidationCredentialsDTO = new AccountValidationCredentialsDTO();

				final JAXBElement<String> password = new JAXBElement<String>(new QName("password"), String.class,
						"magikuser@123");

				final JAXBElement<String> username = new JAXBElement<String>(new QName("userName"), String.class,
						"magikuser");

				final JAXBElement<AccountValidationCredentialsDTO> accountValidationCredentialsDTOJAXBElement = new JAXBElement<AccountValidationCredentialsDTO>(
						new QName("accountValidationCredentials"), AccountValidationCredentialsDTO.class,
						accountValidationCredentialsDTO);

				final JAXBElement<String> channel = new JAXBElement<String>(new QName("channel"), String.class,
						"LMS_WEB");

				final JAXBElement<String> moNumber = new JAXBElement<String>(new QName("moNumber"), String.class,
						customerCareHeaders.getMsisdn());

				final JAXBElement<String> timestamp = new JAXBElement<String>(new QName("timestamp"), String.class,
						"5656655666");

				final JAXBElement<String> transactionID = new JAXBElement<String>(new QName("transactionID"),
						String.class, "677888999");

				accountValidationCredentialsDTO.setPassword(password);
				accountValidationCredentialsDTO.setUserName(username);

				redeemDTO.setAccountValidationCredentials(accountValidationCredentialsDTOJAXBElement);
				redeemDTO.setChannel(channel);
				redeemDTO.setLanguageId(1);
				redeemDTO.setMoNumber(moNumber);
				redeemDTO.setPackID(21);
				redeemDTO.setPin(0);
				redeemDTO.setTimestamp(timestamp);
				redeemDTO.setTransactionID(transactionID);

				ResponseDTO responseDTO = pointManagement.getPointManagementHttpSoap11Endpoint()
						.redeemPoints(redeemDTO);
				System.out.println(responseDTO+".................................................");
				StringBuilder dataStr = new StringBuilder("{");
				responseDTO.getData().stream().forEach(name -> { dataStr.append(name.getName().getValue()+":"+name.getValue().getValue()+",");});
				dataStr.deleteCharAt(dataStr.length()-1);
				dataStr.append("}");
				System.out.println(responseDTO.toString() +"......." +dataStr+"....."
						+ responseDTO.getStatusCode().getValue());

				break;*/

			case "packages":
				/*PointManagement pointManagement1 = new PointManagement();

				com.sixdee.imp.service.servicedto.req.xsd.PackageDTO packageDTO = new com.sixdee.imp.service.servicedto.req.xsd.PackageDTO();
				AccountValidationCredentialsDTO accountValidationCredentialsDTO1 = new AccountValidationCredentialsDTO();

				final JAXBElement<String> password1 = new JAXBElement<String>(new QName("password"), String.class,
						"magikuser@123");

				final JAXBElement<String> username1 = new JAXBElement<String>(new QName("userName"), String.class,
						"magikuser");

				final JAXBElement<AccountValidationCredentialsDTO> accountValidationCredentialsDTOJAXBElement1 = new JAXBElement<AccountValidationCredentialsDTO>(
						new QName("accountValidationCredentials"), AccountValidationCredentialsDTO.class,
						accountValidationCredentialsDTO1);

				final JAXBElement<String> channel1 = new JAXBElement<String>(new QName("channel"), String.class,
						"LMS_WEB");

				final JAXBElement<String> moNumber1 = new JAXBElement<String>(new QName("subscriberNumber"),
						String.class, customerCareHeaders.getMsisdn());

				final JAXBElement<String> timestamp1 = new JAXBElement<String>(new QName("timestamp"), String.class,
						"1233555");

				final JAXBElement<String> transactionID1 = new JAXBElement<String>(new QName("transactionID"),
						String.class, "78779999");

				final JAXBElement<String> languageId1 = new JAXBElement<String>(new QName("languageId"), String.class,
						"1");

				final JAXBElement<String> offerType1 = new JAXBElement<String>(new QName("offerType"), String.class,
						"-1");

				accountValidationCredentialsDTO1.setPassword(password1);
				accountValidationCredentialsDTO1.setUserName(username1);

				packageDTO.setAccountValidationCredentials(accountValidationCredentialsDTOJAXBElement1);
				packageDTO.setChannel(channel1);
				packageDTO.setLanguageId(languageId1);
				packageDTO.setSubscriberNumber(moNumber1);
				packageDTO.setOfferType(offerType1);
				packageDTO.setPin(0);
				packageDTO.setTimestamp(timestamp1);
				packageDTO.setTranscationId(transactionID1);

				PackageInfoDTO packageInfoDTO = pointManagement1.getPointManagementHttpSoap11Endpoint()
						.getPackages(packageDTO);
				System.out.println(packageInfoDTO+".................................................");
				
				ObjectMapper Obj = new ObjectMapper(); 
				listString = Obj.writeValueAsString(packageInfoDTO); */

				/*StringBuilder dataStr1 = new StringBuilder("{package:[");*/
				//String json = new Gson().toJson(foo );
				//packageInfoDTO.getPackages().stream().forEach(package -> { dataStr1.append("{"+package.getCategory().getName()+":"+package.getCategory().getValue()+","+"cost:"+package.getCost()+","+package.getEndDate().getName()+":"+package.getEndDate().getValue()+","+package.getIconImagePath().getName()+":"+package.getIconImagePath().getValue()+","+);});
				/*dataStr1.deleteCharAt(dataStr1.length()-1);
				dataStr1.append("}");
				System.out.println(packageInfoDTO.toString() +"......." +dataStr1+"....."
						+ packageInfoDTO.getStatusCode().getValue());
               listString str = "{data:"+dataStr+","+ */

				
				listString=temp_JsonResponse("packages");
				break;
				
			case "Insights":
				listString=temp_JsonResponse("Insights");
				break;
				
			default:
				break; 
			}
		}
		return listString;
	}
	
	@Override
	public List<CustomerCareDTO> getDashboardDetailsOld(CustomerCareDTO customerCareDTO) throws Exception {

		List<CustomerCareDTO> list = null;
		if (null != customerCareDTO && null != customerCareDTO.getFeature()) {

			switch (customerCareDTO.getFeature()) {
			
			case "Insights":
				list = customerCareDAO.getCustomerInsights(customerCareDTO);
				break;
			default:
				break;
			}
		}

		return list;
	}
	
	//For Demo..................
	public String temp_JsonResponse(String feature)
	{
		String jsonResponse="";
		
		if(feature.equals("BasicView"))
		 jsonResponse="[{\"CustomerProfile\":{\"customerProfile\":{ \"firstName\": \"Edward\", \"lastName\":\"E\", \"activationDate\":\"2021-03-01 00:00:00.000\", \"accountType\":\"2\", \"accountNumber\":\"71524\", \"area\":\"Bangalore\", \"region\":\"India\", \"language\":\"ENGLISH\" }, \"deviceDetail\":{ \"model\":\"N5\", \"os\":\"Android\", \"deviceType\":\"SmartPhone\", \"deviceMake\":\"Nokia\" }, \"loyaltyAccount\": [ { \"loyaltyId\": \"20202020202121312\", \"loyaltyStatus\": \"Active\", \"loyaltyWallet\": [ { \"walletId\": \"NORMAL_WALLET\", \"enrollmentDate\": \"2020-04-01\", \"loyaltyBalance\": { \"unitType\": \"POINTS\", \"unitValue\": 200.0 } }, { \"expiryBalance\": { \"unitType\": \"POINTS\", \"unitValue\": 30.0, \"expiryDate\": \"2020-08-31\" } } ] } ] }, \"TransactionHistory\": { \"usageSummary\":{ \"revenueDetails\":[ { \"header\":\"SMS Revenue ($)\", \"value\":\"3.25\" }, { \"header\":\"Data Revenue ($)\", \"value\":\"12.56\" }, { \"header\":\"VAS Revenue ($)\", \"value\":\"24.56\" }, { \"header\":\"Total Revenue ($)\", \"value\":\"63.93\" } ], \"rechargeDetails\":[ { \"header\":\"Recharge Amount($)\", \"value\":\"400\" }, { \"header\":\"Recharge Count\", \"value\":\"96\" } ], \"voicedetails\": [ { \"usageType\": \"Total Call duration(min)\", \"summary\": { \"usageValue\": 453.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 123.0, \"period\": \"Oct\" }, { \"usageValue\": 230.0, \"period\": \"Nov\" }, { \"usageValue\": 100.0, \"period\": \"Dec\" } ] } }, { \"usageType\": \"International Call Minutes\", \"summary\": { \"usageValue\": 5400.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 2000.0, \"period\": \"Oct\" }, { \"usageValue\": 1400.0, \"period\": \"Nov\" }, { \"usageValue\": 2000.0, \"period\": \"Dec\" } ] } }, { \"usageType\": \"On Net Call Minutes\", \"summary\": { \"usageValue\": 5400.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 2000.0, \"period\": \"Oct\" }, { \"usageValue\": 1400.0, \"period\": \"Nov\" }, { \"usageValue\": 2000.0, \"period\": \"Dec\" } ] } } ], \"smsdetails\": [ { \"usageType\": \"SMS Count\", \"summary\": { \"usageValue\": 453.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 233.0, \"period\": \"Oct\" }, { \"usageValue\": 245.0, \"period\": \"Nov\" }, { \"usageValue\": 253.0, \"period\": \"Dec\" } ] } }, { \"usageType\": \"On Net SMS Count\", \"summary\": { \"usageValue\": 5400.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 110.0, \"period\": \"Oct\" }, { \"usageValue\": 103.0, \"period\": \"Nov\" }, { \"usageValue\": 99.0, \"period\": \"Dec\" } ] } }, { \"usageType\": \"On Net Call Minutes\", \"summary\": { \"usageValue\": 5400.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 123.0, \"period\": \"Oct\" }, { \"usageValue\": 142.0, \"period\": \"Nov\" }, { \"usageValue\": 154.0, \"period\": \"Dec\" } ] } } ], \"datadetails\": [ { \"usageType\": \"Data Upload Volume (MB)\", \"summary\": { \"usageValue\": 453.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 5569.0, \"period\": \"Oct\" }, { \"usageValue\": 5673.0, \"period\": \"Nov\" }, { \"usageValue\": 5576.0, \"period\": \"Dec\" } ] } }, { \"usageType\": \"Data Download Volume (MB)\", \"summary\": { \"usageValue\": 5400.0, \"usageUnit\": \"Amount\", \"DetailedSummary\": [ { \"usageValue\": 5896.0, \"period\": \"Oct\" }, { \"usageValue\": 5956.0, \"period\": \"Nov\" }, { \"usageValue\": 4765.0, \"period\": \"Dec\" } ] } } ] }, \"loyaltySummary\": { \"earnedPoints\": 0.0, \"redeemedPoints\": 0.0, \"transferPoints\": 0.0, \"expiredPoints\": 0.0, \"loyaltyTransactions\": { \"activityDate\": \"2020-01-20\", \"activity\": \"LoyaltyRedemption\", \"prePoints\": 10000.0, \"postPoints\": 8000.0, \"usedPoints\": 2000.0, \"description\": \"Redeem 50 SAR\", \"product\": \"Cash\", \"partner\": \"Self\", \"reqTxnId\": \"1233112\", \"offerType\": \"CashRedemption\" } }, \"campaignTransactions\":[ { \"actionDate\": \"2020-03-12\", \"campaign\": \"RechargeCampaign\", \"actionKey\": \"RechargeOnceMore\", \"marketingPlan\": \"RechargePlan\", \"typeOfAction\": \"Promotion\", \"creditAmount\": \"150.0\", \"debitAmount\": \"100.0\", \"promotedOfferGroup\": \"P1\", \"offerActivated\": \"\", \"campaignType\": \"Informative\", \"message\": \"Hello World\", \"status\":\"Activated\" }, { \"actionDate\": \"2020-03-12\", \"campaign\": \"Offer\", \"actionKey\": \"RechargeOnceMore\", \"marketingPlan\": \"OfferPlan\", \"typeOfAction\": \"Promotion\", \"creditAmount\": \"--\", \"debitAmount\": \"--\", \"promotedOfferGroup\": \"P1\", \"offerActivated\": \"\", \"campaignType\": \"Informative\", \"message\": \"Hello World\", \"status\":\"InActive\" } ] } }]";
		else if(feature.equals("packages"))
			jsonResponse="[{\"MyBestOffer\":{\"packagesInfo\":{ \"data\":[ { \"offer\":\"Data_1\", \"messagetext\":\"15gb 3month prepaid\", \"mrp\":\"128\", \"startDate\":\"2020-01-20\", \"endDate\":\"2020-04-20\", \"type\":\"3rd\" }, { \"offer\":\"Data_2\", \"messagetext\":\"25gb 3month prepaid\", \"mrp\":\"399\", \"startDate\":\"2020-05-20\", \"endDate\":\"2020-08-20\", \"type\":\"3rd\" }, { \"offer\":\"Data_3\", \"messagetext\":\"5gb 4month prepaid\", \"mrp\":\"599\", \"startDate\":\"2020-05-20\", \"endDate\":\"2020-08-20\", \"type\":\"Telecom\" } ] } } } ]";
		else if(feature.equals("Insights"))
			jsonResponse="{ \"CustomerInsigth\": [ { \"feature\": \"Insights\", \"msisdn\": \"9980071524\", \"status\": true, \"customerProfile\": null, \"deviceProfile\": null, \"loyaltyAccount\": null, \"packagesInfo\": null, \"redeemedPoints\": null, \"usageSummary\": null, \"loyaltySummary\": null, \"campaignTransactions\": null, \"subSegment\": \"HVC\", \"numberOfSubscribers\": \"225\", \"avAge\": \"4 Years 6 Months 6 Days\", \"avgRevenuePerMonth\": \"100\", \"avgAccBalance\": \"83.22\", \"maxAgeInNetwork\": \"4 Years 6 Months 6 Days\", \"minAgeInNetwork\": \"1\", \"avgIntCallPerDay\": \"0\", \"avgOutCallPerDay\": \"4\", \"avgVolPerDay\": \"100 MB\", \"avgBalRecharge\": \"100\", \"avgMaxRefill\": \"150\", \"avgMinRefill\": \"50\", \"avgRechargeValuePerMon\": \"100\", \"avgRechargesPerMonth\": \"5\", \"avgTimeBwRecharges\": \"1 Month 7 Days\", \"avgOutSmsPerDay\": \"5\", \"segment\": \"128\", \"restSegment\": \"30\", \"avgRespRate\": \"20\", \"insightSmsCount\": \"8\", \"mmsCount\": \"2\", \"emailCount\": \"5\", \"ussdCount\": \"10\", \"avgChannelResponse\": \"55%\", \"socialCount\": \"5\", \"avgLocalCallPerDay\": \"4\" } ] }";
		return jsonResponse;
	}


}
