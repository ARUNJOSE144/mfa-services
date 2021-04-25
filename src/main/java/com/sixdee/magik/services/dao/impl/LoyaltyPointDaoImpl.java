package com.sixdee.magik.services.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LoyaltyPointsDAO;
import com.sixdee.magik.services.lmsService.PointManagementStub;
import com.sixdee.magik.services.lmsService.PointManagementStub.ResponseDTO;
import com.sixdee.magik.services.lmsService.PointManagementStub.TransferPoints;
import com.sixdee.magik.services.lmsService.PointManagementStub.TransferPointsDTO;
import com.sixdee.magik.services.lmsService.PointManagementStub.TransferPointsResponse;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;

@Repository
@Transactional
public class LoyaltyPointDaoImpl implements LoyaltyPointsDAO {

	static final Logger logger = Logger.getLogger(LoyaltyPointDaoImpl.class);
	
	@Autowired
	private Environment env;
	
	@Override
	public LoyaltyRequestDTO pointTransfer(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {
		logger.info("LoyaltyPointDaoImpl>>>>>>>pointTransfer>>>>>>Subscriber1" + loyaltyRequestDTO.getSubscriber1());
		logger.info("LoyaltyPointDaoImpl>>>>>>>pointTransfer>>>>>>Subscriber1" + loyaltyRequestDTO.getSubscriber2());
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH");
			DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();

			PointManagementStub stub = new PointManagementStub(env.getProperty("loyalty.point.service.url"));
			TransferPoints transferPoints = new TransferPoints();
			TransferPointsDTO transferPointsDTO = new TransferPointsDTO();
			transferPointsDTO.setTimestamp(dateFormat.format(date));
			transferPointsDTO.setTransactionID(dateFormat1.format(date));

			transferPointsDTO.setSubscriberNumber(loyaltyRequestDTO.getSubscriber1());
			transferPointsDTO.setDestSubscriberNumber(loyaltyRequestDTO.getSubscriber2());
			transferPointsDTO.setPoints(Integer.parseInt(loyaltyRequestDTO.getPoints()));
			transferPoints.setTransferPointsDTO(transferPointsDTO);

			TransferPointsResponse responce = stub.transferPoints(transferPoints);
			
			ResponseDTO accountdto = responce.get_return();

			loyaltyRequestDTO.setStatus(accountdto.getStatusDescription());
			loyaltyRequestDTO.setReturnCode(accountdto.getStatusCode());
		} catch (Exception e) {
			throw e;
		}
		return loyaltyRequestDTO;
	}
	
}
