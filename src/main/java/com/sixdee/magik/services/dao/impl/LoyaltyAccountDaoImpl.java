package com.sixdee.magik.services.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LoyaltyAccountDAO;
import com.sixdee.magik.services.lmsService.AccountManagementStub;
import com.sixdee.magik.services.lmsService.AccountManagementStub.AccountMerging;
import com.sixdee.magik.services.lmsService.AccountManagementStub.AccountMergingDTO;
import com.sixdee.magik.services.lmsService.AccountManagementStub.AccountMergingResponse;
import com.sixdee.magik.services.lmsService.AccountManagementStub.ResponseDTO;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.RateCardDTO;
import com.sixdee.magik.services.model.TierConfigDTO;
import com.sixdee.magik.services.model.TierInfoDTO;

@Repository
@Transactional
public class LoyaltyAccountDaoImpl implements LoyaltyAccountDAO {

	static final Logger logger = Logger.getLogger(LoyaltyAccountDaoImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	//@Qualifier("lmsSessionFactory")
	@Qualifier("applicationSessionFactory") 
	private SessionFactory sessionFactory;
	
	@Override
	public LoyaltyRequestDTO accountMerging(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {

		logger.info("LoyaltyAccountDaoImpl>>>>>>>accountMerging>>>>>>Subscriber1" + loyaltyRequestDTO.getSubscriber1());
		logger.info("LoyaltyAccountDaoImpl>>>>>>>accountMerging>>>>>>Subscriber1" + loyaltyRequestDTO.getSubscriber2());

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH");
			DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();

			AccountManagementStub stub = new AccountManagementStub(env.getProperty("loyalty.account.service.url"));
			AccountMerging accountmerge = new AccountMerging();
			AccountMergingDTO accountmergedto = new AccountMergingDTO();
			accountmergedto.setTimestamp(dateFormat.format(date));
			accountmergedto.setTransactionId(dateFormat1.format(date));
			String[] RegisterNumber = new String[] { loyaltyRequestDTO.getSubscriber1(), loyaltyRequestDTO.getSubscriber2() };

			accountmergedto.setRegisterNumbers(RegisterNumber);
			accountmerge.setAccountMergingDTO(accountmergedto);

			AccountMergingResponse responce = stub.accountMerging(accountmerge);
			
			ResponseDTO accountdto = responce.get_return();

			loyaltyRequestDTO.setStatus(accountdto.getStatusDescription());
			loyaltyRequestDTO.setReturnCode(accountdto.getStatusCode());

		} catch (Exception e) {
			throw e;
		}
		return loyaltyRequestDTO;
	}

	@Override
	public LoyaltyRequestDTO accountTransfer(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return loyaltyRequestDTO;
	}

	@Override
	public LoyaltyRequestDTO accountTransferPoints(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return loyaltyRequestDTO;
	}

	@Override
	public TierInfoDTO getTierAndCategory(TierInfoDTO tierInfoDTO) throws Exception {
		Session session = null;
		String sql = null;
		List<TierInfoDTO> tierList = new ArrayList<TierInfoDTO>();
		try {
			session = sessionFactory.getCurrentSession();
			sql = "SELECT ID,TIER_NAME FROM TIER_INFO ORDER BY ID";
			Query query = session.createSQLQuery(sql);
			List<Object[]> results = query.list();
			if(results!= null && results.size()>0) {
				for (Object[] aRow : results) {
					TierInfoDTO dto = new TierInfoDTO();
					int id = Integer.parseInt(aRow[0].toString());
					String name  = aRow[1].toString();
					dto.setTierId(id);
					dto.setTierName(name);
					tierList.add(dto);
				}
				tierInfoDTO.setTierList(tierList);
			}
			
			getCategory(tierInfoDTO);
			
		} catch (Exception e) {
			throw e;
		}
		return tierInfoDTO;
	}
	
	public TierInfoDTO getCategory(TierInfoDTO tierInfoDTO) throws Exception {
		Session session = null;
		String sql = null;
		List<TierInfoDTO> tierList = new ArrayList<TierInfoDTO>();
		try {
			session = sessionFactory.getCurrentSession();
			sql = "SELECT CATEGORY_ID,CATEGORY_NAME,UNITS_CALCULATION FROM REWARD_POINTS_CATEGORY ORDER BY CATEGORY_ID";
			Query query = session.createSQLQuery(sql);
			List<Object[]> results = query.list();
			if(results!= null && results.size()>0) {
				for (Object[] aRow : results) {
					TierInfoDTO dto = new TierInfoDTO();
					int id = Integer.parseInt(aRow[0].toString());
					String name  = aRow[1].toString();
					String unitCalc  = aRow[2].toString();
					dto.setCategoryId(id);
					dto.setCategoryName(name);
					dto.setUnitCalculation(unitCalc);
					tierList.add(dto);
				}
				tierInfoDTO.setCategoryList(tierList);
			}
		} catch (Exception e) {
			throw e;
		}
		return tierInfoDTO;
	}

	@Override
	public TierInfoDTO createTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(tierInfoDTO);
			session.getTransaction().commit();
			tierInfoDTO.setStatus(0);
		} catch (Exception e) {
			try {
				tierInfoDTO.setStatus(1);
				session.getTransaction().rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		}
		return tierInfoDTO;
	}

	@Override
	public List<TierInfoDTO> getAllTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		Session session = null;
		TierInfoDTO dto = null;
		List<TierInfoDTO> viewList = new ArrayList<TierInfoDTO>();
		HashMap<Integer, String> tierMap = new HashMap<Integer, String>();
		HashMap<Integer, String> categoryMap = new HashMap<Integer, String>();
		HashMap<String, List> tierCategoryMap = new HashMap<String, List>();
		List<TierInfoDTO> detailList = null;
		try {
			session = sessionFactory.getCurrentSession();
			String sql="SELECT TIER_ID,TI.TIER_NAME,TRD.CATEGORY_ID,RPC.CATEGORY_NAME,TRD.MIN_VALUE," +
					" TRD.MAX_VALUE,TRD.REWARD_POINTS,TRD.STATUS_POINTS,RPC.UNITS_CALCULATION,TRD.ID FROM TIER_REWARD_POINTS_DETAILS TRD, " +
					" REWARD_POINTS_CATEGORY RPC,TIER_INFO TI WHERE TRD.TIER_ID = TI.ID AND TRD.CATEGORY_ID = RPC.CATEGORY_ID" ;
			Query query=session.createSQLQuery(sql);
			List<Object[]> results = query.list();
			
			for(Object[] all:results)
			{	dto = new TierInfoDTO();
				dto.setTierId(Integer.parseInt(all[0].toString()));
				dto.setTierName(all[1].toString());
				dto.setCategoryId(Integer.parseInt(all[2].toString()));
				dto.setCategoryName(all[3].toString());
				dto.setMinValue(all[4]!=null?all[4].toString():"No Limit");
				dto.setMaxValue(all[5]!=null?all[5].toString():"No Limit");
				dto.setRewardPts(all[6]!=null?all[6].toString():"No Limit");
				dto.setStatusPts(all[7]!=null?all[7].toString():"No Limit");
				dto.setUnitCalculation(all[8]!=null?all[8].toString():"No Limit");
				dto.setRecordId(Integer.parseInt(all[9].toString()));
				
				tierMap.put(Integer.parseInt(all[0].toString()), all[1].toString());
				tierInfoDTO.setTierMap(tierMap);
				categoryMap.put(Integer.parseInt(all[2].toString()), all[3].toString());
				tierInfoDTO.setCategoryMap(categoryMap);
				
				if(tierCategoryMap.containsKey(all[0].toString()+"-"+all[2].toString()))
				{
					detailList = tierCategoryMap.get(all[0].toString()+"-"+all[2].toString());
					detailList.add(dto);
				}
				else
				{
					detailList = new ArrayList<TierInfoDTO>();
					detailList.add(dto);
					tierCategoryMap.put(all[0].toString()+"-"+all[2].toString(), detailList);
				}
				
				
				viewList.add(dto);
				
			tierInfoDTO.setTierCategoryMap(tierCategoryMap);
			}
		} catch (Exception e) {
			throw e;
		}
		return viewList;
	}

	@Override
	public TierInfoDTO createCategoryDetails(TierInfoDTO tierInfoDTO) throws Exception {
		Session session = null;
		Transaction tx = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			String sql="INSERT INTO REWARD_POINTS_CATEGORY (CATEGORY_NAME,CATEGORY_DESC,UNITS_CALCULATION) VALUES (?,?,?)";
			Query query = session.createSQLQuery(sql);      
	        query.setParameter(0,tierInfoDTO.getCategoryName());
	        query.setParameter(1,tierInfoDTO.getCategoryDesc());
	        query.setParameter(2,tierInfoDTO.getUnitCalculation());
	        int rowCount = query.executeUpdate();
		
			if(rowCount>0)
			{
				tx.commit();
				tierInfoDTO.setStatus(0);
			}
		}
		catch (Exception e) {
			try {
				tierInfoDTO.setStatus(1);
				tx.rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		}
		return tierInfoDTO;
	}

	@Override
	public TierInfoDTO updateTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE TIER_REWARD_POINTS_DETAILS SET MIN_VALUE = ? , MAX_VALUE =? , REWARD_POINTS = ? , STATUS_POINTS = ?  where ID = ?";
			Query query = session.createSQLQuery(sql);
			query.setParameter(0, tierInfoDTO.getMinValue());
			query.setParameter(1, tierInfoDTO.getMaxValue());
			query.setParameter(2, tierInfoDTO.getRewardPts());
			query.setParameter(3, tierInfoDTO.getStatusPts());
			query.setParameter(4, tierInfoDTO.getRecordId());
		
			int rowCount = query.executeUpdate();
			if (rowCount > 0) {
				tx.commit();
				tierInfoDTO.setStatus(0);
			}
		} catch (Exception e) {
			tierInfoDTO.setStatus(1);
			e.printStackTrace();
			throw e;
		}
		return tierInfoDTO;
	}

	@Override
	public TierInfoDTO deleteTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "DELETE FROM TIER_REWARD_POINTS_DETAILS WHERE ID =?";
			Query query = session.createSQLQuery(hql);
			query.setParameter(0, tierInfoDTO.getRecordId());
			int row = query.executeUpdate();
			if (row == 0) {
				tx.commit();
				tierInfoDTO.setStatus(0);
			} else {
				tierInfoDTO.setStatus(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			tierInfoDTO.setStatus(1);
			throw e;
		}
		return tierInfoDTO;
	}

	@Override
	public List<TierConfigDTO> getAllTierConfigDetails(TierConfigDTO tierInfoDTO) throws Exception {
		Session session = null;
		List<TierConfigDTO> tierList=null;
		TierConfigDTO tierDto = null;
		try {
			session = sessionFactory.getCurrentSession();
			String sql="SELECT ID,TIER_NAME,TIER_DESC,MIN_VALUE,MAX_VALUE,WELCOME_REWARD_POINTS,WELCOME_STATUS_POINTS from TIER_INFO" ;
			Query query = session.createSQLQuery(sql);
			List<Object[]> list=query.list();
			tierList = new ArrayList<TierConfigDTO>();
			for(Object[] obj:list)
			{
				tierDto=new TierConfigDTO();	
				tierDto.setTierId(obj[0]!=null?obj[0].toString():"");
				tierDto.setTierName(obj[1]!=null?obj[1].toString():"");
				tierDto.setTierDesc(obj[2]!=null?obj[2].toString():"");
				tierDto.setMinValue(obj[3]!=null?obj[3].toString():"");
				tierDto.setMaxValue(obj[4]!=null?obj[4].toString():"");
				tierDto.setWelcomeRewardPts(Integer.parseInt((obj[5]!=null?obj[5].toString():"0")));
				tierDto.setWelcomeStatusPts(Integer.parseInt((obj[6]!=null?obj[6].toString():"0")));
				tierList.add(tierDto);
			}
			tierInfoDTO.setStatus(0);
		} catch (Exception e) {
			tierInfoDTO.setStatus(1);
			e.printStackTrace();
			throw e;
		}
		return tierList;
	}

	@Override
	public TierConfigDTO createTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception {
		Session session = null;
		Transaction tx = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			String sql="INSERT INTO TIER_INFO (TIER_NAME,TIER_DESC,MIN_VALUE,MAX_VALUE,WELCOME_REWARD_POINTS,WELCOME_STATUS_POINTS) VALUES (?,?,?,?,?,?)";
			Query query = session.createSQLQuery(sql);      
	        query.setParameter(0,tierConfigDTO.getTierName());
	        query.setParameter(1,tierConfigDTO.getTierDesc());
	        query.setParameter(2,tierConfigDTO.getMinValue());
	        query.setParameter(3,tierConfigDTO.getMaxValue());
	        query.setParameter(4,tierConfigDTO.getWelcomeRewardPts());
	        query.setParameter(5,tierConfigDTO.getWelcomeStatusPts());
	        int rowCount = query.executeUpdate();
		
			if(rowCount>0)
			{
				tx.commit();
				tierConfigDTO.setStatus(0);
			}
			
			tx = session.beginTransaction();
			sql="INSERT INTO TIER_LANGUAGE_MAPPING (TIER_ID,TIER_NAME,LANG_ID) VALUES ((select ID from TIER_INFO where TIER_NAME='"+tierConfigDTO.getTierName()+"'),?,?)";
			query = session.createSQLQuery(sql);
	        query.setParameter(0,tierConfigDTO.getTierName());
	        query.setParameter(1,1);
	        rowCount = query.executeUpdate();
			
			if(rowCount>0)
			{
				tx.commit();	
				tierConfigDTO.setStatus(0);
			}
			
			tx = session.beginTransaction();
			sql="INSERT INTO TIER_LANGUAGE_MAPPING (TIER_ID,TIER_NAME,LANG_ID) VALUES ((select ID from TIER_INFO where TIER_NAME='"+tierConfigDTO.getTierName()+"'),?,?)";
			query = session.createSQLQuery(sql);
	        query.setParameter(0,tierConfigDTO.getTierName());
	        query.setParameter(1,2);
	        if(rowCount>0)
			{
				tx.commit();	
				tierConfigDTO.setStatus(0);
			}
		}
		catch (Exception e) {
			try {
				tierConfigDTO.setStatus(1);
				tx.rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		}
		return tierConfigDTO;
	}
	
	@Override
	public TierConfigDTO deleteTierConfigDetails(TierConfigDTO tierConfigDTO) {

		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "DELETE FROM TIER_INFO WHERE ID =:packageID";
			Query query = session.createSQLQuery(hql);
			query.setParameter("packageID", Integer.parseInt(tierConfigDTO.getTierId()));
			int row = query.executeUpdate();
			tx.commit();
			if (row == 0)
				tierConfigDTO.setStatus(1);
			else
				tierConfigDTO.setStatus(0);
			tx = session.beginTransaction();
			hql = "DELETE FROM TIER_LANGUAGE_MAPPING WHERE TIER_ID =:packageID";
			query = session.createSQLQuery(hql);
			query.setParameter("packageID", Integer.parseInt(tierConfigDTO.getTierId()));
			row = query.executeUpdate();
			tx.commit();
			if (row == 0)
				tierConfigDTO.setStatus(0);
			else
				tierConfigDTO.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			tierConfigDTO.setStatus(1);
			tx.rollback();
			throw e;
		}
		return tierConfigDTO;
	}
	
	@Override
	public TierConfigDTO updateTierConfigDetails(TierConfigDTO tierConfigDTO) {

		Transaction tx = null;
		Session session = null;
		try {

			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE TIER_LANGUAGE_MAPPING SET TIER_NAME = :tiername   where TIER_ID = :tierId";
			Query query = session.createSQLQuery(sql);
			query.setParameter("tierId", Integer.parseInt(tierConfigDTO.getTierId()));
			query.setParameter("tiername", tierConfigDTO.getTierName());

			int rowCount = query.executeUpdate();
			if (rowCount > 0) {
				tierConfigDTO.setStatus(0);
			tx.commit();
			} else {
				tierConfigDTO.setStatus(1);
				tx.rollback();
			}

			tx = session.beginTransaction();
			sql = "UPDATE TIER_INFO SET TIER_NAME = :tiername , TIER_DESC =:tierdesc , MIN_VALUE = :minvalue , "
					+ "MAX_VALUE = :maxvalue , WELCOME_REWARD_POINTS =:rewardPt , WELCOME_STATUS_POINTS=:statusPt "
					+ " where ID = :tierId";
			query = session.createSQLQuery(sql);
			query.setParameter("tiername", tierConfigDTO.getTierName());
			query.setParameter("tierdesc", tierConfigDTO.getTierDesc());
			query.setParameter("minvalue", tierConfigDTO.getMinValue());
			query.setParameter("maxvalue", tierConfigDTO.getMaxValue());
			query.setParameter("rewardPt", tierConfigDTO.getWelcomeRewardPts());
			query.setParameter("statusPt", tierConfigDTO.getWelcomeStatusPts());
			query.setParameter("tierId", tierConfigDTO.getTierId());

			rowCount = query.executeUpdate();

			if (rowCount > 0) {
				tierConfigDTO.setStatus(0);
				tx.commit();
			} else {
				tierConfigDTO.setStatus(1);
				tx.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			tierConfigDTO.setStatus(1);
			tx.rollback();
			throw e;
		} 
		return tierConfigDTO;

	}

	@Override
	public RateCardDTO createRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		Session session = null;
		Transaction tx = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			String sql="INSERT INTO RATE_CARD(RATE_CARD_NAME,RATE_CARD_DESC,DENOMINATION , STATUS , VALIDITY_DAYS) VALUES(?,?,?,?,?)";
			Query query = session.createSQLQuery(sql);      
	        query.setParameter(0,rateCardDTO.getRateCardName());
	        query.setParameter(1,rateCardDTO.getRateCardDesc());
	        query.setParameter(2,rateCardDTO.getDenomination());
	        query.setParameter(3,rateCardDTO.getStatus());
	        query.setParameter(4,rateCardDTO.getValidityDays());
	        int rowCount = query.executeUpdate();
	        if (rowCount > 0) {
	        	tx.commit();
				rateCardDTO.setStatusCode(0);
			} else {
				rateCardDTO.setStatusCode(1);
			}
		}
		catch (Exception e) {
			try {
				rateCardDTO.setStatusCode(1);
				tx.rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		}
		return rateCardDTO;
	}

	@Override
	public List<RateCardDTO> getAllRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		Session session = null;
		List<RateCardDTO> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			String sql = " SELECT ID,RATE_CARD_NAME,RATE_CARD_DESC,DENOMINATION,STATUS,VALIDITY_DAYS FROM RATE_CARD order by ID DESC";
			Query query = session.createSQLQuery(sql);
			List<Object[]> results = query.list();
			if(results!= null && results.size()>0) {
				list = new ArrayList<RateCardDTO>();
				for (Object[] aRow : results) {
					RateCardDTO dto = new RateCardDTO();
					dto.setId(Integer.parseInt(aRow[0].toString()));
					dto.setRateCardName(aRow[1].toString());
					dto.setRateCardDesc(aRow[2].toString());
					dto.setDenomination(Integer.parseInt(aRow[3].toString()));
					dto.setStatus(aRow[4].toString());
					dto.setValidityDays(aRow[5].toString());
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public RateCardDTO updateRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE RATE_CARD SET RATE_CARD_NAME=:RateCardName , RATE_CARD_DESC=:Description , DENOMINATION=:Denomination ,STATUS=:status ,VALIDITY_DAYS=:validityDays WHERE ID=:Id";
			Query qry = session.createSQLQuery(sql);
			qry.setParameter("RateCardName", rateCardDTO.getRateCardName());
			qry.setParameter("Description", rateCardDTO.getRateCardDesc());
			qry.setParameter("Denomination", rateCardDTO.getDenomination());
			qry.setParameter("status", rateCardDTO.getStatus());
			qry.setParameter("validityDays", rateCardDTO.getValidityDays());
			qry.setParameter("Id", rateCardDTO.getId());
			int rowCount = qry.executeUpdate();

			if (rowCount > 0) {
				tx.commit();
				rateCardDTO.setStatusCode(0);
			} else {
				rateCardDTO.setStatusCode(1);
				tx.rollback();
			}
		} catch (Exception e) {
			try {
				rateCardDTO.setStatusCode(1);
				tx.rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		}
		return rateCardDTO;
	}

	@Override
	public RateCardDTO deleteRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String sql = "DELETE FROM RATE_CARD WHERE ID=:Id";
			Query qry = session.createSQLQuery(sql);
			qry.setParameter("Id", rateCardDTO.getId());
			int rowCount = qry.executeUpdate();
			if (rowCount > 0) {
				tx.commit();
				rateCardDTO.setStatusCode(0);
			} else {
				rateCardDTO.setStatusCode(1);
				tx.rollback();
			}
		} catch (Exception e) {
			try {
				rateCardDTO.setStatusCode(1);
				tx.rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		}
		return rateCardDTO;
	}
}
