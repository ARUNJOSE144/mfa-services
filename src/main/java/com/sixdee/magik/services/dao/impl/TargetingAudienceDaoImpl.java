package com.sixdee.magik.services.dao.impl;
/**
 * @author Amal A S
 * @category Target Audience for campaigns
 * @date 03/06/2020
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixdee.magik.services.dao.TargetingAudienceDao;
import com.sixdee.magik.services.model.FacebookAudienceDTO;
import com.sixdee.magik.services.model.GeoLocationsModelDTO;
import com.sixdee.magik.services.model.IdNameDTO;
import com.sixdee.magik.services.model.KeyValuePairDTO;
import com.sixdee.magik.services.model.TargetingAudienceDTO;
import com.sixdee.magik.services.model.TargetingAudienceMappingDTO;
import com.sixdee.magik.services.model.TargetingAudienceMasterDTO;
import com.sixdee.magik.services.model.TargetingCountries;
import com.sixdee.magik.services.model.TargetingCountriesResponseDTO;
import com.sixdee.magik.services.model.TargetingInterestsResponseDTO;
import com.sixdee.magik.services.model.TargetingLanguageMaster;
import com.sixdee.magik.services.model.TargetingRegionResponseDTO;
import com.sixdee.magik.services.model.TargetingRegionsDTO;
import com.sixdee.magik.services.model.TargetingWorkPostionsDTO;

@Repository
public class TargetingAudienceDaoImpl implements TargetingAudienceDao {

	private static final Logger logger = Logger.getLogger(TargetingAudienceDaoImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	public TargetingAudienceDTO getEstimatedGraph(TargetingAudienceDTO requestDto) {

		logger.info("Request reached for show audience graph : " + requestDto);

		TargetingAudienceDTO responseInfo = null;
		FacebookAudienceDTO fbAudDto = null;
		String url = null;
		GeoLocationsModelDTO geoDto = null;
		ObjectMapper mapper = null;
		long reqTimeStart = System.currentTimeMillis();

		try {

			responseInfo = new TargetingAudienceDTO();
			mapper = new ObjectMapper();
			geoDto = new GeoLocationsModelDTO();
			fbAudDto = new FacebookAudienceDTO();

			url = env.getProperty("facebook.url") + "act_" + env.getProperty("facebook.appcode")
					+ "/reachestimate?targeting_spec=";

			if (requestDto.getRegions() != null && requestDto.getRegions().size() > 0) {
				geoDto.setRegions(requestDto.getRegions());
			} else if (requestDto.getCountry() != null && !requestDto.getCountry().equals("")) {
				List<String> countryList = new ArrayList<>();
				for (String countries : requestDto.getCountry().split(",")) {
					countryList.add(countries);
				}
				geoDto.setCountries(countryList);
			}

			fbAudDto.setGeo_locations(geoDto);

			if (requestDto.getMinAge() != null && !requestDto.getMinAge().equals("")) {
				fbAudDto.setAge_min(Integer.parseInt(requestDto.getMinAge()));
			}

			if (requestDto.getMaxAge() != null && !requestDto.getMaxAge().equals("")) {
				fbAudDto.setAge_max(Integer.parseInt(requestDto.getMaxAge()));
			}

			if (requestDto.getGenders() != null && requestDto.getGenders().size() > 0) {
				fbAudDto.setGenders(requestDto.getGenders());
			}
			if (requestDto.getInterests() != null && requestDto.getInterests().size() > 0) {
				fbAudDto.setInterests(requestDto.getInterests());
			}
			if (requestDto.getIndustries() != null && requestDto.getIndustries().size() > 0) {
				fbAudDto.setIndustries(requestDto.getIndustries());
			}
			if (requestDto.getUser_os() != null && requestDto.getUser_os().size() > 0) {
				fbAudDto.setUser_os(requestDto.getUser_os());
			}
			fbAudDto.setPublisher_platforms(Arrays.asList(requestDto.getMediaType()));

			// Fb request formating
			String fbJson = mapper.writeValueAsString(fbAudDto);
			url = url + URLEncoder.encode(fbJson, StandardCharsets.UTF_8.toString()) + "&access_token="
					+ env.getProperty("facebook.accesstoken");

			// Calling Fb
			long beforeFBCall = System.currentTimeMillis();
			String resp = CallThirdPartyUrl.callGet(url);
			logger.info("Time Taken for FB call  : " + (System.currentTimeMillis() - beforeFBCall));

			// Response mapping
			responseInfo = mapper.readValue(resp, TargetingAudienceDTO.class);
			responseInfo.setCountry(requestDto.getCountry());
			responseInfo.setMaxAge(requestDto.getMaxAge());
			responseInfo.setMinAge(requestDto.getMinAge());
			responseInfo.setMediaType(requestDto.getMediaType());
			responseInfo.setGenders(requestDto.getGenders());
			responseInfo.setInterests(requestDto.getInterests());
			responseInfo.setIndustries(requestDto.getIndustries());
			responseInfo.setRegions(requestDto.getRegions());
			responseInfo.setUser_os(requestDto.getUser_os());

			if (responseInfo.getData() != null && responseInfo.getData().getUsers() != null) {
				responseInfo.setStatusCode("SC0000");
				responseInfo.setStatusDesc("SUCCESS");
			} else {
				responseInfo.setStatusCode("SC0001");
				responseInfo.setStatusDesc("FAILED");
			}

		} catch (Exception e) {
			responseInfo.setStatusCode("SC0001");
			responseInfo.setStatusDesc("FAILED");
			e.printStackTrace();
		} finally {

			mapper = null;
			geoDto = null;
			fbAudDto = null;
			logger.info("Total time taken for show graph : " + (System.currentTimeMillis() - reqTimeStart));
		}

		return responseInfo;
	}

	public TargetingAudienceDTO manageAudience(TargetingAudienceDTO requestDto) {

		logger.info("Request reached for Creating Audience Configuration : " + requestDto);

		TargetingAudienceDTO response = null;
		TargetingAudienceMasterDTO masterDto = null;
		TargetingAudienceMappingDTO mappingDto = null;

		Session session = null;

		long reqReachTime = System.currentTimeMillis();

		try {

			session = sessionFactory.getCurrentSession();
			response = new TargetingAudienceDTO();
			masterDto = new TargetingAudienceMasterDTO();

			masterDto.setTargetName(requestDto.getConfigName());
			masterDto.setTargetDesc(requestDto.getConfigDesc());
			masterDto.setMediaType(requestDto.getMediaType());

			session.save(masterDto);

			if (requestDto.getCountry() != null) {
				mappingDto = new TargetingAudienceMappingDTO("COUNTRY", requestDto.getCountry(), "COUNTRY", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}
			if (requestDto.getMinAge() != null) {
				mappingDto = new TargetingAudienceMappingDTO("MIN_AGE", requestDto.getMinAge(), "MIN_AGE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}
			if (requestDto.getMaxAge() != null) {
				mappingDto = new TargetingAudienceMappingDTO("MAX_AGE", requestDto.getMaxAge(), "MAX_AGE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}

			if (requestDto.getEstimatedSize() != null) {
				mappingDto = new TargetingAudienceMappingDTO("ESTIMATED_VALUE", requestDto.getEstimatedSize(),
						"ESTIMATED_VALUE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}

			if (requestDto.getCustomFileName() != null) {
				mappingDto = new TargetingAudienceMappingDTO("CUSTOM_UPLOADED_FILE", requestDto.getCustomFileName(),
						"CUSTOM_UPLOADED_FILE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}

			if (requestDto.getInterests() != null && requestDto.getInterests().size() > 0) {
				for (IdNameDTO dto : requestDto.getInterests()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getId() + "_" + dto.getName(),
							"INTERESTS", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getGenders() != null && requestDto.getGenders().size() > 0) {
				for (Integer val : requestDto.getGenders()) {
					mappingDto = new TargetingAudienceMappingDTO("ID", String.valueOf(val), "GENDER", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getIndustries() != null && requestDto.getIndustries().size() > 0) {
				for (IdNameDTO dto : requestDto.getIndustries()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getId() + "_" + dto.getName(),
							"INDUSTRIES", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getRegions() != null && requestDto.getRegions().size() > 0) {

				for (KeyValuePairDTO dto : requestDto.getRegions()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getKey(), "REGIONS", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getUser_os() != null && requestDto.getUser_os().size() > 0) {

				for (String os : requestDto.getUser_os()) {
					mappingDto = new TargetingAudienceMappingDTO("ID", os, "OS", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}
			
			if (requestDto.getLanguages() != null && requestDto.getLanguages().size() > 0) {

				for (TargetingLanguageMaster dto : requestDto.getLanguages()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getKey(), "LANGUAGE", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			response.setStatusCode("SC0000");
			response.setStatusDesc("SUCCESS");

			logger.info("Audience configuration successfully committed");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0001");
			response.setStatusDesc("FAILED");
		} finally {

			masterDto = null;
			mappingDto = null;

			logger.info("Total time taken for audience creation : " + (System.currentTimeMillis() - reqReachTime));

		}

		return response;
	}

	public TargetingAudienceDTO updateAudience(TargetingAudienceDTO requestDto) {

		logger.info("Request reached for Updating Audience Configuration : " + requestDto);

		TargetingAudienceDTO response = null;
		TargetingAudienceMasterDTO masterDto = null;
		TargetingAudienceMappingDTO mappingDto = null;

		Session session = null;
		Query query = null;

		long reqReachTime = System.currentTimeMillis();

		try {

			response = new TargetingAudienceDTO();

			session = sessionFactory.getCurrentSession();

			masterDto = session.get(TargetingAudienceMasterDTO.class, requestDto.getConfigId());
			masterDto.setTargetName(requestDto.getConfigName());
			masterDto.setTargetDesc(requestDto.getConfigDesc());
			masterDto.setMediaType(requestDto.getMediaType());
			session.saveOrUpdate(masterDto);

			query = session.createSQLQuery(
					"DELETE FROM TARGETING_AUDIENCE_MAPPING WHERE TARGET_ID=" + requestDto.getConfigId());
			query.executeUpdate();

			if (requestDto.getCountry() != null) {
				mappingDto = new TargetingAudienceMappingDTO("COUNTRY", requestDto.getCountry(), "COUNTRY", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}
			if (requestDto.getMinAge() != null) {
				mappingDto = new TargetingAudienceMappingDTO("MIN_AGE", requestDto.getMinAge(), "MIN_AGE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}
			if (requestDto.getMaxAge() != null) {
				mappingDto = new TargetingAudienceMappingDTO("MAX_AGE", requestDto.getMaxAge(), "MAX_AGE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}

			if (requestDto.getEstimatedSize() != null) {
				mappingDto = new TargetingAudienceMappingDTO("ESTIMATED_VALUE", requestDto.getEstimatedSize(),
						"ESTIMATED_VALUE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}

			if (requestDto.getCustomFileName() != null) {
				mappingDto = new TargetingAudienceMappingDTO("CUSTOM_UPLOADED_FILE", requestDto.getCustomFileName(),
						"CUSTOM_UPLOADED_FILE", masterDto);
				session.save(mappingDto);
				mappingDto = null;
			}

			if (requestDto.getInterests() != null && requestDto.getInterests().size() > 0) {
				for (IdNameDTO dto : requestDto.getInterests()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getId() + "_" + dto.getName(),
							"INTERESTS", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getGenders() != null && requestDto.getGenders().size() > 0) {
				for (Integer val : requestDto.getGenders()) {
					mappingDto = new TargetingAudienceMappingDTO("ID", String.valueOf(val), "GENDER", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getIndustries() != null && requestDto.getIndustries().size() > 0) {
				for (IdNameDTO dto : requestDto.getIndustries()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getId() + "_" + dto.getName(),
							"INDUSTRIES", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getRegions() != null && requestDto.getRegions().size() > 0) {

				for (KeyValuePairDTO dto : requestDto.getRegions()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getKey(), "REGIONS", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			if (requestDto.getUser_os() != null && requestDto.getUser_os().size() > 0) {

				for (String os : requestDto.getUser_os()) {
					mappingDto = new TargetingAudienceMappingDTO("ID", os, "OS", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}
			
			
			if (requestDto.getLanguages() != null && requestDto.getLanguages().size() > 0) {

				for (TargetingLanguageMaster dto : requestDto.getLanguages()) {
					mappingDto = new TargetingAudienceMappingDTO("ID_NAME", dto.getKey(), "LANGUAGE", masterDto);
					session.save(mappingDto);
					mappingDto = null;
				}
			}

			response.setStatusCode("SC0000");
			response.setStatusDesc("SUCCESS");

			logger.info("Audience configuration successfully updated");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0001");
			response.setStatusDesc("FAILED");
		} finally {
			masterDto = null;
			mappingDto = null;
			logger.info("Total time taken for audience updation : " + (System.currentTimeMillis() - reqReachTime));
		}

		return response;
	}

	public TargetingAudienceDTO deleteAudience(TargetingAudienceDTO requestDto) {

		logger.info("Request reached for Deleting Audience Configuration : " + requestDto);

		TargetingAudienceDTO response = null;
		TargetingAudienceMasterDTO masterDto = null;

		Session session = null;

		long reqReachTime = System.currentTimeMillis();

		try {
			response = new TargetingAudienceDTO();
			session = sessionFactory.getCurrentSession();
			masterDto = new TargetingAudienceMasterDTO();
			masterDto.setTargetId(requestDto.getConfigId());
			session.delete(masterDto);

			response.setStatusCode("SC0000");
			response.setStatusDesc("SUCCESS");

			logger.info("Audience configuration successfully deleted");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0000");
			response.setStatusDesc("SUCCESS");
		} finally {
			masterDto = null;
			logger.info("Total time taken for audience deletion : " + (System.currentTimeMillis() - reqReachTime));
		}

		return response;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TargetingAudienceDTO getTargetAudience() {

		logger.info("Request reached for getting Audience Configuration : ");

		TargetingAudienceDTO response = null;
		List<TargetingAudienceDTO> tempList = null;
		Session session = null;

		long reqReachTime = System.currentTimeMillis();

		try {
			response = new TargetingAudienceDTO();
			session = sessionFactory.getCurrentSession();

			List list = session.createCriteria(TargetingAudienceMasterDTO.class).list();
			Iterator<TargetingAudienceMasterDTO> temp = list.iterator();
			tempList = new ArrayList<>();
			while (temp.hasNext()) {
				TargetingAudienceMasterDTO mDto = temp.next();
				TargetingAudienceDTO dto = new TargetingAudienceDTO();
				dto.setConfigId(mDto.getTargetId());
				dto.setConfigName(mDto.getTargetName());
				dto.setConfigDesc(mDto.getTargetDesc());
				dto.setMediaType(mDto.getMediaType());
				List<IdNameDTO> interestList = new ArrayList<>();
				List<Integer> gender = new ArrayList<>();
				List<IdNameDTO> industriesList = new ArrayList<>();
				List<KeyValuePairDTO> regions = new ArrayList<>();
				List<String> userOs = new ArrayList<>();
				List<TargetingLanguageMaster> languages = new ArrayList<>();
				for (TargetingAudienceMappingDTO mapDto : mDto.getMappingDetails()) {
					if (mapDto.getAttributeType().equalsIgnoreCase("COUNTRY")) {
						dto.setCountry(mapDto.getAttributeValue());
					}
					if (mapDto.getAttributeType().equalsIgnoreCase("MIN_AGE")) {
						dto.setMinAge(mapDto.getAttributeValue());
					}
					if (mapDto.getAttributeType().equalsIgnoreCase("MAX_AGE")) {
						dto.setMaxAge(mapDto.getAttributeValue());
					}
					if (mapDto.getAttributeType().equalsIgnoreCase("ESTIMATED_VALUE")) {
						dto.setEstimatedSize(mapDto.getAttributeValue());
					}
					if (mapDto.getAttributeType().equalsIgnoreCase("CUSTOM_UPLOADED_FILE")) {
						dto.setCustomFileName(mapDto.getAttributeValue());
					}

					if (mapDto.getAttributeType().equalsIgnoreCase("INTERESTS")) {
						IdNameDTO idDto = new IdNameDTO();
						idDto.setId(mapDto.getAttributeValue().split("_")[0]);
						idDto.setName(mapDto.getAttributeValue().split("_")[1]);
						interestList.add(idDto);
					}
					if (mapDto.getAttributeType().equalsIgnoreCase("INDUSTRIES")) {
						IdNameDTO idDto = new IdNameDTO();
						idDto.setId(mapDto.getAttributeValue().split("_")[0]);
						idDto.setName(mapDto.getAttributeValue().split("_")[1]);
						industriesList.add(idDto);
					}

					if (mapDto.getAttributeType().equalsIgnoreCase("GENDER")) {
						gender.add(Integer.parseInt(mapDto.getAttributeValue()));

					}

					if (mapDto.getAttributeType().equalsIgnoreCase("REGIONS")) {
						KeyValuePairDTO idDto = new KeyValuePairDTO();
						idDto.setKey(mapDto.getAttributeValue());
						regions.add(idDto);
					}

					if (mapDto.getAttributeType().equalsIgnoreCase("OS")) {
						userOs.add(mapDto.getAttributeValue());	
					}
					
					if (mapDto.getAttributeType().equalsIgnoreCase("LANGUAGE")) {
						TargetingLanguageMaster idDto = new TargetingLanguageMaster();
						idDto.setKey(mapDto.getAttributeValue());
						languages.add(idDto);
					}

				}
				dto.setGenders(gender);
				dto.setInterests(interestList);
				dto.setIndustries(industriesList);
				dto.setRegions(regions);
				dto.setLanguages(languages);
				dto.setUser_os(userOs);

				tempList.add(dto);
			}

			System.out.println("List size of audience : " + tempList.size());

			response.setListData(tempList);
			response.setStatusCode("SC0000");
			response.setStatusDesc("SUCCESS");

		} catch (Exception e) {
			response.setStatusCode("SC0000");
			response.setStatusDesc("SUCCESS");
			e.printStackTrace();
		} finally {
			tempList = null;
			logger.info("Total time taken for audience List : " + (System.currentTimeMillis() - reqReachTime));
		}

		return response;

	}

	@SuppressWarnings("unchecked")
	public TargetingAudienceDTO getCountries(String updateValue, String limit, String offset, String mediaType,
			String queryVal, String country) {

		System.out.println("Request Came for fetching config lists : limit : " + limit + " updateValue : " + updateValue
				+ " offset : " + offset + " mediaType : " + mediaType + " queryValue : " + queryVal);

		TargetingAudienceDTO response = null;
		ObjectMapper mapper = null;
		Session session = null;
		String url = null;
		TargetingCountriesResponseDTO countriesDTO = null;
		TargetingRegionResponseDTO regionsDTO = null;
		TargetingInterestsResponseDTO interestsResponseDTO = null;
		TargetingWorkPostionsDTO worksDto = null;
		Query query = null;
		boolean flag = true;
		try {
			response = new TargetingAudienceDTO();
			session = sessionFactory.getCurrentSession();

			if (updateValue != null && updateValue.equalsIgnoreCase("TRUE")) {

				if (mediaType.equalsIgnoreCase("FACEBOOK_COUNTRIES")) {

					url = env.getProperty("facebook.url") + "search?type=adgeolocation"
							+ "&location_types=[\"country\"]&access_token=" + env.getProperty("facebook.accesstoken")
							+ "&limit=300";

					// System.out.println("URL : " + url);

					String callResp = CallThirdPartyUrl.callGet(url);
					mapper = new ObjectMapper();
					countriesDTO = new TargetingCountriesResponseDTO();
					countriesDTO = mapper.readValue(callResp, TargetingCountriesResponseDTO.class);

					if (countriesDTO.getData() != null && countriesDTO.getData().size() > 0) {

						query = session.createSQLQuery("TRUNCATE TABLE TARGETING_COUNTRIES_MASTER");
						query.executeUpdate();
						TimeUnit.SECONDS.sleep(5);
						for (TargetingCountries countries : countriesDTO.getData()) {
							countries.setMediaType("FACEBOOK");
							session.save(countries);
						}

					}

				} else if (mediaType.equalsIgnoreCase("FACEBOOK_REGIONS")) {

					// Update Region
					url = env.getProperty("facebook.url") + "search?type=adgeolocation"
							+ "&location_types=[\"region\"]&access_token=" + env.getProperty("facebook.accesstoken")
							+ "&limit=1000&q=";

					/*
					 * query = session.createSQLQuery("TRUNCATE TABLE TARGETING_REGIONS_MASTER");
					 * query.executeUpdate(); TimeUnit.SECONDS.sleep(5);
					 */

					while (flag) {

						String callResp = CallThirdPartyUrl.callGet(url);
						mapper = new ObjectMapper();
						regionsDTO = new TargetingRegionResponseDTO();
						regionsDTO = mapper.readValue(callResp, TargetingRegionResponseDTO.class);

						if (regionsDTO.getData() != null && regionsDTO.getData().size() > 0) {
							url = regionsDTO.getPaging().getNext();
							System.out.println("Regions : " + regionsDTO.toString());
							for (TargetingRegionsDTO dto : regionsDTO.getData()) {
								dto.setMediaType("FACEBOOK");
								session.save(dto);
							}

						} else {
							flag = false;
						}

					}
				} else if (mediaType.equalsIgnoreCase("FACEBOOK_INTERESTS")) {

					// Update Interests
					url = env.getProperty("facebook.url") + "search?type=adTargetingCategory"
							+ "&class=interests&access_token=" + env.getProperty("facebook.accesstoken");

					String callResp = CallThirdPartyUrl.callGet(url);
					mapper = new ObjectMapper();
					interestsResponseDTO = new TargetingInterestsResponseDTO();
					interestsResponseDTO = mapper.readValue(callResp, TargetingInterestsResponseDTO.class);

					if (interestsResponseDTO.getData() != null && interestsResponseDTO.getData().size() > 0) {
						query = session.createSQLQuery("TRUNCATE TABLE TARGETING_INTERESTS_MASTER");
						query.executeUpdate();
						TimeUnit.SECONDS.sleep(5);
						System.out.println("Ineterets : " + interestsResponseDTO.toString());
						for (TargetingInterestsResponseDTO dto : interestsResponseDTO.getData()) {
							dto.setMediaType("FACEBOOK");
							session.save(dto);
						}
					}

				} else if (mediaType.equalsIgnoreCase("FACEBOOK_JOBS")) {

					// Update work positions
					url = env.getProperty("facebook.jobtitle.url") + "&access_token="
							+ env.getProperty("facebook.accesstoken");

					String callResp = CallThirdPartyUrl.callGet(url);
					mapper = new ObjectMapper();
					worksDto = new TargetingWorkPostionsDTO();
					worksDto = mapper.readValue(callResp, TargetingWorkPostionsDTO.class);

					if (worksDto.getData() != null && worksDto.getData().size() > 0) {
						query = session.createSQLQuery("TRUNCATE TABLE TARGETING_WORK_POSTIONS");
						query.executeUpdate();
						TimeUnit.SECONDS.sleep(5);
						System.out.println("Jobs : " + worksDto.toString());
						for (TargetingWorkPostionsDTO dto : worksDto.getData()) {
							dto.setMediaType("FACEBOOK");
							session.save(dto);
						}
					}

				}

				if (mediaType.equalsIgnoreCase("GOOGLE_COUNTRIES")) {

					String line = "";
					try {
						BufferedReader br = new BufferedReader(new FileReader(env.getProperty("google.country.list")));
						while ((line = br.readLine()) != null) {

							String countries[] = line.split(",");

							if ((countries[4] != null && countries[4].equalsIgnoreCase("Country"))
									&& (countries[2] == null || countries[2].equals(""))) {

								TargetingCountries coun = new TargetingCountries();
								coun.setMediaType("GOOGLE");
								coun.setKey(countries[0]);
								coun.setName(countries[1]);
								coun.setType("country");
								coun.setCountry_code(countries[3]);
								session.save(coun);

							}

						}
						br.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				if (mediaType.equalsIgnoreCase("GOOGLE_REGIONS")) {

					String line = "";
					try {
						BufferedReader br = new BufferedReader(new FileReader(env.getProperty("google.country.list")));
						while ((line = br.readLine()) != null) {

							String countries[] = line.split(",");

							if ((countries[4] != null && !countries[4].equalsIgnoreCase("Country"))) {

								TargetingRegionsDTO coun = new TargetingRegionsDTO();
								coun.setMediaType("GOOGLE");
								coun.setKey(countries[0]);
								coun.setName(countries[1]);
								coun.setType(countries[4]);
								coun.setCountry_code(countries[3]);
								coun.setCountry_name(countries[2]);
								session.save(coun);
							}

						}
						br.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				if (mediaType.equalsIgnoreCase("GOOGLE_LANGUAGES")) {

					String line = "";
					try {
						BufferedReader br = new BufferedReader(new FileReader(env.getProperty("google.language.list")));
						while ((line = br.readLine()) != null) {

							String lang[] = line.split(",");

							TargetingLanguageMaster coun = new TargetingLanguageMaster();
							coun.setMediaType("GOOGLE");
							coun.setKey(lang[2]);
							coun.setValue(lang[0]);
							coun.setLanguageCode(lang[1]);
							session.save(coun);

						}
						br.close();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			} else if (updateValue != null && updateValue.equalsIgnoreCase("FALSE")) {

				if (mediaType.equalsIgnoreCase("FACEBOOK_COUNTRIES")
						|| mediaType.equalsIgnoreCase("GOOGLE_COUNTRIES")) {

					Criteria cr = session.createCriteria(TargetingCountries.class);

					if (mediaType.equalsIgnoreCase("FACEBOOK_COUNTRIES")) {
						cr.add(Restrictions.eq("mediaType", "FACEBOOK"));
					} else if (mediaType.equalsIgnoreCase("GOOGLE_COUNTRIES")) {
						cr.add(Restrictions.eq("mediaType", "GOOGLE"));
					}

					if (offset != null) {
						cr.setFirstResult(Integer.parseInt(offset));
					}

					if (limit != null) {
						cr.setMaxResults(Integer.parseInt(limit));
					}

					if (queryVal != null && !queryVal.equals("")) {
						cr.add(Restrictions.like("name", "%" + queryVal + "%"));
					}

					response.setDataCountriesList(cr.list());

				}

				if (mediaType.equalsIgnoreCase("FACEBOOK_REGIONS") || mediaType.equalsIgnoreCase("GOOGLE_REGIONS")) {

					Criteria cr = session.createCriteria(TargetingRegionsDTO.class);

					if (mediaType.equalsIgnoreCase("FACEBOOK_REGIONS")) {
						cr.add(Restrictions.eq("mediaType", "FACEBOOK"));
					} else if (mediaType.equalsIgnoreCase("GOOGLE_REGIONS")) {
						cr.add(Restrictions.eq("mediaType", "GOOGLE"));
					}

					if (offset != null) {
						cr.setFirstResult(Integer.parseInt(offset));
					}

					if (limit != null) {
						cr.setMaxResults(Integer.parseInt(limit));
					}

					if (queryVal != null && !queryVal.equals("")) {
						cr.add(Restrictions.like("name", "%" + queryVal + "%"));
					}

					if (country != null && !country.equals("")) {
						// cr.add(Restrictions.eq("country_code", country));
						List<String> countries = new ArrayList<>();
						for (String coun : country.split("_")) {
							countries.add(coun);
						}
						cr.add(Restrictions.in("country_code", countries));
					}

					response.setDataRegionList(cr.list());

				}
				if (mediaType.equalsIgnoreCase("FACEBOOK_INTERESTS")) {
					Criteria cr = session.createCriteria(TargetingInterestsResponseDTO.class);
					if (offset != null) {
						cr.setFirstResult(Integer.parseInt(offset));
					}

					if (limit != null) {
						cr.setMaxResults(Integer.parseInt(limit));
					}

					if (queryVal != null && !queryVal.equals("")) {
						cr.add(Restrictions.like("name", "%" + queryVal + "%"));
					}

					response.setDataInterestList(cr.list());

				}
				if (mediaType.equalsIgnoreCase("FACEBOOK_JOBS")) {
					Criteria cr = session.createCriteria(TargetingWorkPostionsDTO.class);
					if (offset != null) {
						cr.setFirstResult(Integer.parseInt(offset));
					}

					if (limit != null) {
						cr.setMaxResults(Integer.parseInt(limit));
					}
					if (queryVal != null && !queryVal.equals("")) {
						cr.add(Restrictions.like("name", "%" + queryVal + "%"));
					}

					response.setDataJobList(cr.list());

				}

				if (mediaType.equalsIgnoreCase("GOOGLE_LANGUAGES")) {
					Criteria cr = session.createCriteria(TargetingLanguageMaster.class);
					cr.add(Restrictions.eq("mediaType", "GOOGLE"));
					if (offset != null) {
						cr.setFirstResult(Integer.parseInt(offset));
					}

					if (limit != null) {
						cr.setMaxResults(Integer.parseInt(limit));
					}
					if (queryVal != null && !queryVal.equals("")) {
						cr.add(Restrictions.like("value", "%" + queryVal + "%"));
					}

					response.setLanguages(cr.list());

				}

			}
			System.out.println("Response : " + response.toString());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}

		return response;
	}

}
