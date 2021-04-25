package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.GoogleAdsDao;
import com.sixdee.magik.services.model.GoogleAdsKeywordMapping;
import com.sixdee.magik.services.model.GoogleAdsMaster;

@Repository
public class GoogleAdsDaoImpl implements GoogleAdsDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	public GoogleAdsMaster createAds(GoogleAdsMaster request) {

		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.save(request);

			for (GoogleAdsKeywordMapping mapDto : request.getMappingDetails()) {
				mapDto.setGoogleMaster(request);
				session.save(mapDto);
			}

			request = new GoogleAdsMaster();

			request.setStatusCode("SC0000");
			request.setStatusDescription("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			request.setStatusCode("SC0001");
			request.setStatusDescription("FAILURE");
			e.printStackTrace();
		}

		return request;
	}

	public GoogleAdsMaster updateAds(GoogleAdsMaster request) {

		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.getCurrentSession();
			query = session.createSQLQuery("DELETE FROM GOOGLE_ADS_KEYWORD_MAPPING WHERE AD_ID = " + request.getId());
			query.executeUpdate();
			session.saveOrUpdate(request);

			for (GoogleAdsKeywordMapping mapDto : request.getMappingDetails()) {
				mapDto.setGoogleMaster(request);
				session.save(mapDto);
			}

			request = new GoogleAdsMaster();

			request.setStatusCode("SC0000");
			request.setStatusDescription("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			request.setStatusCode("SC0001");
			request.setStatusDescription("FAILURE");
			e.printStackTrace();
		}

		return request;
	}

	public GoogleAdsMaster deleteAds(GoogleAdsMaster request) {

		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.delete(request);

			request = new GoogleAdsMaster();
			request.setStatusCode("SC0000");
			request.setStatusDescription("SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			request.setStatusCode("SC0001");
			request.setStatusDescription("FAILURE");
			e.printStackTrace();
		}

		return request;
	}

	@SuppressWarnings("unchecked")
	public List<GoogleAdsMaster> getAds() {

		Session session = null;
		List<GoogleAdsMaster> respList = null;
		try {
			session = sessionFactory.getCurrentSession();
			List<GoogleAdsMaster> tempList = session.createCriteria(GoogleAdsMaster.class).list();
			respList = new ArrayList<>();
			for (GoogleAdsMaster master : tempList) {

				GoogleAdsMaster dto = new GoogleAdsMaster();
				dto.setAdName(master.getAdName());
				dto.setBidAmount(master.getBidAmount());
				dto.setDescription_1(master.getDescription_1());
				dto.setDescription_2(master.getDescription_2());
				dto.setHeadLine_1(master.getHeadLine_1());
				dto.setHeadLine_2(master.getHeadLine_2());
				dto.setHeadLine_3(master.getHeadLine_3());
				dto.setUploadFileName(master.getUploadFileName());
				dto.setUploadType(master.getUploadType());
				dto.setId(master.getId());
				dto.setUrl(master.getUrl());

				List<GoogleAdsKeywordMapping> mapList = new ArrayList<>();

				for (GoogleAdsKeywordMapping mapDto : master.getMappingDetails()) {
					GoogleAdsKeywordMapping map = new GoogleAdsKeywordMapping();
					map.setId(mapDto.getId());
					map.setKeyword(mapDto.getKeyword());
					map.setKeywordType(mapDto.getKeywordType());
					mapList.add(map);
				}
				dto.setMappingDetails(mapList);
				respList.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}

		return respList;
	}

}
