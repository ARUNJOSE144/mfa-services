package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.GoogleCampaigner;
import com.sixdee.magik.services.dao.GoogleCampaigningDAO;
import com.sixdee.magik.services.model.GoogleAdsMaster;
import com.sixdee.magik.services.model.GoogleCampaignDetailsDTO;
import com.sixdee.magik.services.model.GoogleCampaignKeywordDetailsDTO;
import com.sixdee.magik.services.model.GoogleCampaignMasterDTO;
import com.sixdee.magik.services.model.TargetingAudienceMasterDTO;

@Repository
@Transactional
public class GoogleCampaigningDAOImpl implements GoogleCampaigningDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	GoogleCampaigner googleCampaigner;

	private static final Logger logger = Logger.getLogger(GoogleCampaigningDAOImpl.class);

	@Async
	public void manageCampaigns() {

		List<GoogleCampaignMasterDTO> masterList = getcampaignList();

		if (masterList != null && masterList.size() > 0) {

			for (GoogleCampaignMasterDTO masterDto : masterList) {

				GoogleAdsMaster adsMaster = getGoogleAds(masterDto.getAdId());
				
				TargetingAudienceMasterDTO targetMaster = getTargetingAudience(masterDto.getTargetingId());
				
				GoogleCampaignDetailsDTO detailsDto = googleCampaigner.manageCampaign(masterDto, adsMaster, targetMaster);

				if (detailsDto != null) {

					detailsDto.setMagikCampaignId(masterDto.getGoogleCampaignMaster().getId());
					logger.info("Campaign Details : " + detailsDto.toString());

					saveCampaignDetails(detailsDto);

					updateCampaignStatus(masterDto);
				}

			}

		}

	}

	private TargetingAudienceMasterDTO getTargetingAudience(int targetingId) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			return session.get(TargetingAudienceMasterDTO.class, targetingId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	private void updateCampaignStatus(GoogleCampaignMasterDTO masterDto) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();

			GoogleCampaignMasterDTO dto = session.get(GoogleCampaignMasterDTO.class, masterDto.getId());
			dto.setStatus(1);
			session.saveOrUpdate(dto);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void saveCampaignDetails(GoogleCampaignDetailsDTO detailsDto) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			session.save(detailsDto);

			for (GoogleCampaignKeywordDetailsDTO dto : detailsDto.getKeywordDetails()) {

				session.save(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private GoogleAdsMaster getGoogleAds(int adId) {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			return session.get(GoogleAdsMaster.class, adId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private List<GoogleCampaignMasterDTO> getcampaignList() {

		Session session = null;

		try {

			session = sessionFactory.getCurrentSession();
			return session.createCriteria(GoogleCampaignMasterDTO.class).add(Restrictions.eq("status", 0)).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

}
