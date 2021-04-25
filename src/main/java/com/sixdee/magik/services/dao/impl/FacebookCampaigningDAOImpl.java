package com.sixdee.magik.services.dao.impl;
/**
 * @author Amal A S
 * @category Scheduler for facebook campaigns
 * @date 23/06/2020
 * 
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.FacebookCampaigner;
import com.sixdee.magik.services.dao.FacebookCampaigningDAO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.FacebookCampaignDetailsDTO;
import com.sixdee.magik.services.model.FacebookCampaignMasterDTO;
import com.sixdee.magik.services.model.SocialMediaScheduleMappingDTO;
import com.sixdee.magik.services.model.SocialMediaScheduleMasterDTO;
import com.sixdee.magik.services.model.TargetingAudienceMasterDTO;

@Repository
@Transactional
public class FacebookCampaigningDAOImpl implements FacebookCampaigningDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	FacebookCampaigner facebookCampaigner;

	@Autowired
	Environment env;

	private static final Logger logger = Logger.getLogger(FacebookCampaigningDAOImpl.class);

	@Async
	public void manageFacebookCampaigns() {

		// Checking for facebook campaign with scheduled=0
		List<FacebookCampaignMasterDTO> fbCampList = checkForFacebookCampaigns();
		FacebookCampaignDetailsDTO fbcampDetails = null;

		if (fbCampList != null) {

			for (FacebookCampaignMasterDTO masterDto : fbCampList) {

				if (masterDto.getFacebookCampaignMaster().getCampaignSegmentType() != -1
						&& masterDto.getFacebookCampaignMaster().getCampaignSegmentType() != 0) {

					TargetingAudienceMasterDTO audienceMasterDto = getTargetingDetails(
							masterDto.getFacebookCampaignMaster().getCampaignSegmentType());

					if (audienceMasterDto != null) {

						updateCampaignStatus(masterDto, 2, fbcampDetails);

						fbcampDetails = facebookCampaigner.manageCampaigns(masterDto, audienceMasterDto);

						updateCampaignStatus(masterDto, 1, fbcampDetails);

						updateCampaignMaster(masterDto.getFacebookCampaignMaster().getId(), 1);

					}

				}

			}

		}

	}

	@Async
	public void scheduleFacebookCampaigns() {

		String response = null;

		try {

			List<FacebookCampaignDetailsDTO> campist = getFacebookCampaignList();

			List<SocialMediaScheduleMasterDTO> inActiveCampList = getSocialMediaList();

			if (inActiveCampList != null && inActiveCampList.size() > 0) {

				pauseFacebookCampaigns(inActiveCampList);
			}

			if (campist != null) {

				for (FacebookCampaignDetailsDTO detailsDto : campist) {

					List<SocialMediaScheduleMasterDTO> scMasterList = getCampaignDetails(
							detailsDto.getMagikCampaignId());

					if (scMasterList != null) {

						for (SocialMediaScheduleMasterDTO scMasterDto : scMasterList) {

							String requestData = createScedulingQuery(scMasterDto);

							if (requestData != null) {

								response = facebookCampaigner.updateSchedule(scMasterDto, detailsDto, requestData);

								if (response != null && response.equals("SC0000")) {

									if (!scMasterDto.getSchedulingType().equalsIgnoreCase("monthly")) {
										updateScheduleStatus(detailsDto, scMasterDto, 1);
									}

								} else {

									updateScheduleStatus(detailsDto, scMasterDto, 4);

								}

							}

						}
					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Async
	private void pauseFacebookCampaigns(List<SocialMediaScheduleMasterDTO> inActiveCampList) {

		List<FacebookCampaignDetailsDTO> fbCampList = null;

		try {

			for (SocialMediaScheduleMasterDTO scMasterDTO : inActiveCampList) {

				fbCampList = getFacebookCampaignDetails(scMasterDTO.getCampaignId());

				if (fbCampList != null && fbCampList.size() > 0) {

					for (FacebookCampaignDetailsDTO fbDto : fbCampList) {

						String status = facebookCampaigner.updateFacebookAdSetStatus(fbDto, "PAUSE");

						if (status != null && status.equalsIgnoreCase("SC0000")) {

							updateScheduleStatus(fbDto, scMasterDTO, 3);

						} else {

							updateScheduleStatus(fbDto, scMasterDTO, 4);

						}

					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private List<FacebookCampaignDetailsDTO> getFacebookCampaignDetails(int campaignId) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			return session.createCriteria(FacebookCampaignDetailsDTO.class)
					.add(Restrictions.eqOrIsNull("magikCampaignId", campaignId)).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<SocialMediaScheduleMasterDTO> getSocialMediaList() {

		Session session = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

		try {

			session = sessionFactory.getCurrentSession();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);

			Criteria cr = session.createCriteria(SocialMediaScheduleMasterDTO.class);
			cr.add(Restrictions.eq("scheduledStatus", 1));
			cr.add(Restrictions.eq("scheduleExpiryDate", df.format(c.getTime())));
			return cr.list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	private void updateScheduleStatus(FacebookCampaignDetailsDTO detailsDto, SocialMediaScheduleMasterDTO scMasterDto,
			int status) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			FacebookCampaignDetailsDTO dto = session.get(FacebookCampaignDetailsDTO.class, detailsDto.getId());
			dto.setStatus(status);
			session.save(dto);
			SocialMediaScheduleMasterDTO scDto = session.get(SocialMediaScheduleMasterDTO.class, scMasterDto.getId());
			scDto.setScheduledStatus(status);
			session.save(scDto);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private String createScedulingQuery(SocialMediaScheduleMasterDTO scMasterDto) {

		Calendar calendar = Calendar.getInstance();
		int startTime = 0;
		int endTime = 0;
		int currentDay = 0;
		int hour = 0;
		int min = 0;
		String requestData = null;

		try {

			if (scMasterDto.getSchedulingType().equalsIgnoreCase("scheduleNow")) {

				hour = calendar.get(Calendar.HOUR_OF_DAY);
				min = calendar.get(Calendar.MINUTE);

				if (min > 30) {
					startTime = (int) (60 * (hour)); // logic changed due to fb validation
				} else {
					startTime = (60 * hour);
				}

				endTime = startTime + 120;
				currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;

				requestData = "[{\"start_minute\": " + startTime + ",\"end_minute\": " + endTime + ",\"days\": ["
						+ currentDay + "]}]";

			}

			if (scMasterDto.getSchedulingType().equalsIgnoreCase("interval")) {

				for (SocialMediaScheduleMappingDTO mapDto : scMasterDto.getMappingDetails()) {
					if (mapDto.getIdParam().equalsIgnoreCase("Day")) {
						currentDay = Integer.parseInt(mapDto.getValueParam());
					}

					if (mapDto.getIdParam().equalsIgnoreCase("Time")) {

						String startT = mapDto.getValueParam().split("_")[0];
						String endT = mapDto.getValueParam().split("_")[1];

						hour = Integer.parseInt(startT.split(":")[0]);
						min = Integer.parseInt(startT.split(":")[1]);
						startTime = (60 * hour);
						hour = Integer.parseInt(endT.split(":")[0]);
						min = Integer.parseInt(endT.split(":")[1]);
						endTime = (60 * hour);
					}

				}

				requestData = "[{\"start_minute\": " + startTime + ",\"end_minute\": " + endTime + ",\"days\": ["
						+ currentDay + "]}]";

			}

			if (scMasterDto.getSchedulingType().equalsIgnoreCase("daily")) {

				for (SocialMediaScheduleMappingDTO mapDto : scMasterDto.getMappingDetails()) {

					if (mapDto.getIdParam().equalsIgnoreCase("dailyStartTime")) {
						String startT = mapDto.getValueParam().split("_")[0];
						String endT = mapDto.getValueParam().split("_")[1];

						hour = Integer.parseInt(startT.split(":")[0]);
						min = Integer.parseInt(startT.split(":")[1]);
						startTime = (60 * hour);
						hour = Integer.parseInt(endT.split(":")[0]);
						min = Integer.parseInt(endT.split(":")[1]);
						endTime = (60 * hour);
					}

				}

				requestData = "[{\"start_minute\": " + startTime + ",\"end_minute\": " + endTime
						+ ",\"days\": [0,1,2,3,4,5,6]}]";

			}

			if (scMasterDto.getSchedulingType().equalsIgnoreCase("weekly")) {

				String tempRequest = "";

				for (SocialMediaScheduleMappingDTO mapDto : scMasterDto.getMappingDetails()) {

					if (mapDto.getIdParam().equalsIgnoreCase("days")) {

						for (String days : mapDto.getValueParam().split(",")) {

							String startT = days.split("_")[1];
							String endT = days.split("_")[2];

							hour = Integer.parseInt(startT.split(":")[0]);
							min = Integer.parseInt(startT.split(":")[1]);
							startTime = (60 * hour);
							hour = Integer.parseInt(endT.split(":")[0]);
							min = Integer.parseInt(endT.split(":")[1]);
							endTime = (60 * hour);

							tempRequest += "{\"start_minute\": " + startTime + ",\"end_minute\": " + endTime
									+ ",\"days\": [" + days.split("_")[0] + "]},";

						}

					}

				}

				requestData = "[" + tempRequest.substring(0, tempRequest.length() - 1) + "]";

			}

			if (scMasterDto.getSchedulingType().equalsIgnoreCase("monthly")) {

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				if (scMasterDto.getScheduledDate() != null) {

					String dateCheck = df.format(new Date());

					if (!dateCheck.equals(scMasterDto.getScheduledDate())) {

						requestData = getMonthlyData(scMasterDto);
						updateScheduledDate(scMasterDto);

					}

				} else {

					requestData = getMonthlyData(scMasterDto);
					updateScheduledDate(scMasterDto);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return requestData;
	}

	private void updateScheduledDate(SocialMediaScheduleMasterDTO scMasterDto) {

		Session session = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {

			session = sessionFactory.getCurrentSession();

			SocialMediaScheduleMasterDTO dto = session.get(SocialMediaScheduleMasterDTO.class, scMasterDto.getId());
			dto.setScheduledDate(df.format(new Date()));
			session.save(dto);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private String getMonthlyData(SocialMediaScheduleMasterDTO scMasterDto) {
		// TODO Auto-generated method stub

		String respData = null;
		int startTime = 0;
		int endTime = 0;
		int hour = 0;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date();

		try {

			for (SocialMediaScheduleMappingDTO mapDto : scMasterDto.getMappingDetails()) {

				if (mapDto.getIdParam().equalsIgnoreCase("selectedDates")) {

					for (String days : mapDto.getValueParam().split(",")) {

						String day = days.split("_")[0];
						String startT = days.split("_")[1];
						String endT = days.split("_")[2];

						try {

							Date forDate = df
									.parse(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + day);
							String dat = df.format(forDate);
							String dat1 = df.format(curDate);

							if (dat.equals(dat1)) {

								hour = Integer.parseInt(startT.split(":")[0]);
								startTime = (60 * hour);
								hour = Integer.parseInt(endT.split(":")[0]);
								endTime = (60 * hour);

								respData = "[{\"start_minute\": " + startTime + ",\"end_minute\": " + endTime
										+ ",\"days\": [" + (cal.get(Calendar.DAY_OF_WEEK) - 1) + "]}]";
							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return respData;
	}

	@SuppressWarnings("unchecked")
	private List<SocialMediaScheduleMasterDTO> getCampaignDetails(int magikCampaignId) {

		Session session = null;
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

		try {

			session = sessionFactory.getCurrentSession();

			return session.createCriteria(SocialMediaScheduleMasterDTO.class)
					.add(Restrictions.eq("campaignId", magikCampaignId))
					.add(Restrictions.eq("scheduleStartDate", df.format(new Date()))).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<FacebookCampaignDetailsDTO> getFacebookCampaignList() {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			return session.createCriteria(FacebookCampaignDetailsDTO.class).add(Restrictions.eq("status", 0)).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	private void updateCampaignStatus(FacebookCampaignMasterDTO masterDto, int i,
			FacebookCampaignDetailsDTO fbcampDetails) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			FacebookCampaignMasterDTO dto = session.get(FacebookCampaignMasterDTO.class, masterDto.getId());
			dto.setScheduled(i);
			session.saveOrUpdate(dto);

			logger.info("Updated scheduled status");

			if (i == 1) {
				if (fbcampDetails != null) {
					session.saveOrUpdate(fbcampDetails);
					logger.info("Inserted into facebook Campaign details");
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private TargetingAudienceMasterDTO getTargetingDetails(int campaignSegmentType) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			return session.get(TargetingAudienceMasterDTO.class, campaignSegmentType);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FacebookCampaignMasterDTO> checkForFacebookCampaigns() {

		List<FacebookCampaignMasterDTO> fbList = null;
		Session session = null;

		try {

			logger.info("Checking for facebook campaigns");

			session = sessionFactory.getCurrentSession();
			fbList = session.createCriteria(FacebookCampaignMasterDTO.class).add(Restrictions.eq("scheduled", 0))
					.list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return fbList;
	}

	private void updateCampaignMaster(int campaignId, int status) {
		// TODO Auto-generated method stub

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			CampaignMasterTO dto = session.get(CampaignMasterTO.class, campaignId);
			dto.setStatus(String.valueOf(status));
			session.save(dto);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
