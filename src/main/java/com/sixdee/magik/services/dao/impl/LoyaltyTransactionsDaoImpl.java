package com.sixdee.magik.services.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.LoyaltyTransactionDao;
import com.sixdee.magik.services.lmsService.AccountManagementStub;
import com.sixdee.magik.services.lmsService.AccountManagementStub.AccountDTO;
import com.sixdee.magik.services.lmsService.AccountManagementStub.CreateLoyaltyAccount;
import com.sixdee.magik.services.lmsService.AccountManagementStub.CreateLoyaltyAccountResponse;
import com.sixdee.magik.services.lmsService.AccountManagementStub.DeleteLoyaltyAccount;
import com.sixdee.magik.services.lmsService.AccountManagementStub.DeleteLoyaltyAccountResponse;
import com.sixdee.magik.services.lmsService.AccountManagementStub.ResponseDTO;
import com.sixdee.magik.services.lmsService.TransactionManagementStub;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.ChangeOrderStatus;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.ChangeOrderStatusResponse;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.GetOrderDetails;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.GetOrderDetailsResponse;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.GetTransactionDetails;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.GetTransactionDetailsResponse;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.OrderStatusDTO;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.OrderTrackingDTO;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.OrderTrackingDetailsDTO;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.OrderTrackingInfoDTO;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.TransactionDTO;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.TransactionDetailsDTO;
import com.sixdee.magik.services.lmsService.TransactionManagementStub.TransactionInfoDTO;
import com.sixdee.magik.services.model.LmsPackageCategoryChildDTO;
import com.sixdee.magik.services.model.LmsPackageCategoryMasterDTO;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.OrderDetailsDTO;

/**
 * @author amal.a.s
 * @Date : April, 2019
 *
 */

@Repository
public class LoyaltyTransactionsDaoImpl implements LoyaltyTransactionDao {

	static final Logger logger = Logger.getLogger(LoyaltyTransactionsDaoImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	//@Qualifier("lmsSessionFactory")
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<LoyaltyRequestDTO> getPointsHistoryTransactions(LoyaltyRequestDTO loyaltyRequestDTO) {

		List<LoyaltyRequestDTO> tempList = null;
		TransactionManagementStub transactionManagementStub = null;
		GetTransactionDetails getTransactionDetails = null;
		TransactionDTO transactionDTO = null;

		try {

			System.out.println("msisdn : " + loyaltyRequestDTO.getMsisdn());
			transactionManagementStub = new TransactionManagementStub(
					env.getProperty("loyalty.transaction.service.url"));
			transactionDTO = new TransactionDTO();
			getTransactionDetails = new GetTransactionDetails();

			transactionDTO.setChannel(env.getProperty("loyalty.channel"));
			transactionDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
			transactionDTO.setTransactionId(String.valueOf(System.nanoTime()));
			transactionDTO.setLanguageID("1");

			if (loyaltyRequestDTO.getMsisdn() != null) {
				transactionDTO.setSubscriberNumber(loyaltyRequestDTO.getMsisdn());
			}
			if (loyaltyRequestDTO.getEndDate() != null && loyaltyRequestDTO.getFromDate() != null) {
				transactionDTO.setFromDate(loyaltyRequestDTO.getFromDate());
				transactionDTO.setToDate(loyaltyRequestDTO.getEndDate());
			}
			if (loyaltyRequestDTO.getNumberOfTransactions() != null
					&& !loyaltyRequestDTO.getNumberOfTransactions().equals("")) {
				transactionDTO.setNoOfLastTransaction(Integer.parseInt(loyaltyRequestDTO.getNumberOfTransactions()));
			}

			getTransactionDetails.setTransactionDTO(transactionDTO);

			GetTransactionDetailsResponse transactionDetailsResponse = transactionManagementStub
					.getTransactionDetails(getTransactionDetails);
			TransactionInfoDTO transactionInfoDTO = transactionDetailsResponse.get_return();
			TransactionDetailsDTO[] responseDto = transactionInfoDTO.getTransactionDetails();
			System.out.println(transactionInfoDTO.getStatusCode());
			System.out.println(transactionInfoDTO.getStatusDescription());
			if (transactionInfoDTO != null && transactionInfoDTO.getStatusCode() != null) {

				if (transactionInfoDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					loyaltyRequestDTO.setStatusCode(transactionInfoDTO.getStatusCode());
					loyaltyRequestDTO.setStatusDescription(transactionInfoDTO.getStatusDescription());
					if (responseDto != null && responseDto.length > 0) {
						tempList = new ArrayList<>();
						for (int i = 0; i < responseDto.length; i++) {
							if (responseDto[i] != null) {
								loyaltyRequestDTO = new LoyaltyRequestDTO();
								loyaltyRequestDTO.setTransactionDate(responseDto[i].getDate());
								loyaltyRequestDTO.setPoints(String.valueOf(responseDto[i].getLoyaltyPoints()));
								loyaltyRequestDTO.setAccountLineNumber(responseDto[i].getAccountLineNumber());
								loyaltyRequestDTO.setAction(responseDto[i].getActivity());
								tempList.add(loyaltyRequestDTO);
							}
						}
					}
				} else {
					loyaltyRequestDTO.setStatusCode("SC0001");
					loyaltyRequestDTO.setStatusDescription(transactionInfoDTO.getStatusDescription());
				}

			} else {
				loyaltyRequestDTO.setStatusCode("SC0001");
				loyaltyRequestDTO.setStatusDescription("Service Call Failed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			loyaltyRequestDTO.setStatusCode("SC0001");
			loyaltyRequestDTO.setStatusDescription("Failed to call Service");
			e.printStackTrace();
		}

		return tempList;
	}

	@Override
	public LoyaltyRequestDTO createAccount(LoyaltyRequestDTO loyaltyRequestDTO) {

		AccountManagementStub accountManagementStub = null;
		CreateLoyaltyAccount createLoyaltyAccount = null;
		AccountDTO accountDTO = null;
		ResponseDTO responseDTO = null;
		CreateLoyaltyAccountResponse loyaltyAccountResponse = null;

		try {
			accountManagementStub = new AccountManagementStub(env.getProperty("loyalty.account.service.url"));
			createLoyaltyAccount = new CreateLoyaltyAccount();
			accountDTO = new AccountDTO();

			accountDTO.setChannel(env.getProperty("loyalty.channel"));
			accountDTO.setLanguageID("1");
			accountDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
			accountDTO.setTransactionId(String.valueOf(System.nanoTime()));
			if (loyaltyRequestDTO.getMsisdn() != null) {
				System.out.println("msisdn : " + loyaltyRequestDTO.getMsisdn());
				accountDTO.setMoNumber(loyaltyRequestDTO.getMsisdn());
			}
			createLoyaltyAccount.setAccountDTO(accountDTO);
			loyaltyAccountResponse = accountManagementStub.createLoyaltyAccount(createLoyaltyAccount);
			responseDTO = loyaltyAccountResponse.get_return();

			if (responseDTO != null && responseDTO.getStatusCode() != null) {
				if (responseDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					loyaltyRequestDTO.setStatusCode(responseDTO.getStatusCode());
					loyaltyRequestDTO.setStatusDescription(responseDTO.getStatusDescription());
				} else {
					loyaltyRequestDTO.setStatusCode("SC0001");
					loyaltyRequestDTO.setStatusDescription(responseDTO.getStatusDescription());
				}

			} else {
				loyaltyRequestDTO.setStatusCode("SC0001");
				loyaltyRequestDTO.setStatusDescription("Service Call Failed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loyaltyRequestDTO.setStatusCode("SC0001");
			loyaltyRequestDTO.setStatusDescription("Failed to call Service");
		}

		return loyaltyRequestDTO;
	}

	@Override
	public LoyaltyRequestDTO unsubscribeAccount(LoyaltyRequestDTO loyaltyRequestDTO) {

		AccountManagementStub accountManagementStub = null;
		DeleteLoyaltyAccount deleteLoyaltyAccount = null;
		DeleteLoyaltyAccountResponse deleteLoyaltyAccountResponse = null;
		AccountDTO accountDTO = null;
		ResponseDTO responseDTO = null;

		try {
			accountManagementStub = new AccountManagementStub(env.getProperty("loyalty.account.service.url"));
			accountDTO = new AccountDTO();
			deleteLoyaltyAccount = new DeleteLoyaltyAccount();

			accountDTO.setChannel(env.getProperty("loyalty.channel"));
			accountDTO.setLanguageID("1");
			accountDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
			accountDTO.setTransactionId(String.valueOf(System.nanoTime()));
			if (loyaltyRequestDTO.getMsisdn() != null) {
				System.out.println("msisdn : " + loyaltyRequestDTO.getMsisdn());
				accountDTO.setMoNumber(loyaltyRequestDTO.getMsisdn());
			}
			deleteLoyaltyAccount.setAccountDTO(accountDTO);

			deleteLoyaltyAccountResponse = accountManagementStub.deleteLoyaltyAccount(deleteLoyaltyAccount);
			responseDTO = deleteLoyaltyAccountResponse.get_return();

			if (responseDTO != null && responseDTO.getStatusCode() != null) {
				if (responseDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					loyaltyRequestDTO.setStatusCode(responseDTO.getStatusCode());
					loyaltyRequestDTO.setStatusDescription(responseDTO.getStatusDescription());
				} else {
					loyaltyRequestDTO.setStatusCode("SC0001");
					loyaltyRequestDTO.setStatusDescription(responseDTO.getStatusDescription());
				}

			} else {
				loyaltyRequestDTO.setStatusCode("SC0001");
				loyaltyRequestDTO.setStatusDescription("Service Call Failed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loyaltyRequestDTO.setStatusCode("SC0001");
			loyaltyRequestDTO.setStatusDescription("Failed to call Service");
		}

		return loyaltyRequestDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderDetailsDTO getOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		OrderDetailsDTO orderdto = null;
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		try {
			TransactionManagementStub orderStub = new TransactionManagementStub(
					env.getProperty("loyalty.account.service.url"));
			GetOrderDetails getOrderDetails = new GetOrderDetails();
			OrderTrackingDTO orderDetails = new OrderTrackingDTO();
			orderDetails.setTranscationId("TRANS" + dateFormat.format(date));
			orderDetails.setTimestamp(dateFormat.format(date));
			orderDetails.setChannel("LMS_WEB");
			orderDetails.setSubscriberNumber("" + orderDetailsDTO.getSubscriberNum());
			if (orderDetailsDTO.getNoOfTransactions() == 0 && orderDetailsDTO.getNoOfMonth() == 0) {
				orderDetails.setFromDate("" + orderDetailsDTO.getFromDate());
				orderDetails.setToDate("" + orderDetailsDTO.getToDate());
			} else if (orderDetailsDTO.getNoOfMonth() == 0)
				orderDetails.setNoOfLastTransaction(orderDetailsDTO.getNoOfTransactions());
			else
				orderDetails.setNoOfMonths(orderDetailsDTO.getNoOfMonth());
			orderDetails.setOffSet(0);
			orderDetails.setLimit(100);
			getOrderDetails.setOrderTrackingDTO(orderDetails);
			logger.info("SUBSSCRIBER NUM" + orderDetails.getSubscriberNumber());
			logger.info("FROM DATE" + orderDetails.getFromDate());
			logger.info("TO DATE" + orderDetails.getToDate());
			logger.info("NO. OF TRANS" + orderDetails.getNoOfLastTransaction());
			logger.info("NO OF MONTH" + orderDetails.getNoOfMonths());

			GetOrderDetailsResponse orderResp = orderStub.getOrderDetails(getOrderDetails);
			OrderTrackingInfoDTO orderInfo = orderResp.get_return();

			OrderTrackingDetailsDTO[] respDTOs = orderInfo.getOrderDetails();
			logger.info("orderInfo.getOrderDetails()" + orderInfo.getOrderDetails());
			logger.info("RESULT SIZE ::" + respDTOs.length);

			if (respDTOs.length > 0)
				for (int i = 0; i < respDTOs.length; i++) {
					if (respDTOs[i] != null) {
						orderdto = new OrderDetailsDTO();
						orderdto.setOrderID(respDTOs[i].getOrderId());
						orderdto.setOrderDate(respDTOs[i].getOrderDate());
						orderdto.setItemNumber(respDTOs[i].getItemNumber());
						orderdto.setItemName(respDTOs[i].getItemName());
						orderdto.setQuantity(respDTOs[i].getQuantity());
						orderdto.setPoints(respDTOs[i].getRedeemPoints());
						orderdto.setOrderStatus(respDTOs[i].getOrderStatus());
						orderdto.setExpiryDate(respDTOs[i].getOrderExpiryDate());
						list.add(orderdto);
					}
				}
			orderDetailsDTO.setOrderLists(list);

		} catch (Exception e) {
			throw e;
		}
		return orderDetailsDTO;
	}

	public OrderDetailsDTO getOrderStatus(OrderDetailsDTO orderDetailsDTO) throws Exception {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		try {

			TransactionManagementStub orderStatusStub = new TransactionManagementStub(
					env.getProperty("loyalty.account.service.url"));
			ChangeOrderStatus changeOrderStatus = new ChangeOrderStatus();
			OrderStatusDTO orderStatus = new OrderStatusDTO();

			orderStatus.setTransactionId("TRANS" + dateFormat.format(date));
			orderStatus.setTimestamp(dateFormat.format(date));
			orderStatus.setChannel("WEB");

			orderStatus.setStatusId(2);
			orderStatus.setOrderIds(orderDetailsDTO.getDeliverIDs());
			logger.info("DELIVER ORDER IDS" + orderStatus.getOrderIds());
			orderStatus.setSubscriberNumber("" + orderDetailsDTO.getSubscriberNum());
			changeOrderStatus.setOrderStatusDTO(orderStatus);

			ChangeOrderStatusResponse orderStatusResp = orderStatusStub.changeOrderStatus(changeOrderStatus);
			com.sixdee.magik.services.lmsService.TransactionManagementStub.ResponseDTO respDTO = orderStatusResp
					.get_return();
			logger.info("RESPONSE CODE" + respDTO.getStatusCode());
			logger.info("RESPONSE DESC" + respDTO.getStatusDescription());

			if (respDTO.getStatusCode() != null && respDTO.getStatusCode().equalsIgnoreCase("SC0000")
					|| orderStatus.getOrderIds() == null) {
				orderDetailsDTO.setStatusCode(respDTO.getStatusCode());
				orderDetailsDTO.setStatus(respDTO.getStatusDescription());
				getOrderDetails(orderDetailsDTO);
			} else {
				orderDetailsDTO.setStatusCode(respDTO.getStatusCode());
				orderDetailsDTO.setStatus(respDTO.getStatusDescription());
				getOrderDetails(orderDetailsDTO);
			}

		} catch (Exception e) {
			throw e;
		}
		return orderDetailsDTO;

	}

	public LmsPackageCategoryMasterDTO createCategory(LmsPackageCategoryMasterDTO dto) {

		Session session = null;
		try {

			
			if (dto.getPackageCategories()!=null) {
				
				for (LmsPackageCategoryChildDTO temp : dto.getPackageCategories()) {
					if (temp.getCategoryName()== null) {
						dto.setStatusCode("categoryManagement-name");
						return dto;
					}
					if (temp.getCategorySynonym()== null) {
						dto.setStatusCode("categoryManagement-synonym");
						return dto;
					}

					
				}
				
				
			}
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(dto);
			for (LmsPackageCategoryChildDTO temp : dto.getPackageCategories()) {

					LmsPackageCategoryChildDTO childDTO = new LmsPackageCategoryChildDTO(dto, temp.getLangId(),
							temp.getCategoryName(), temp.getCategoryDescription(), temp.getCategorySynonym(), temp.getId());
					session.saveOrUpdate(childDTO);	
				
			}
			session.getTransaction().commit();
			dto.setStatusCode("categoryManagement-success");
			
		} catch (Exception e) {
			// TODO: handle exception
			dto.setStatusCode("categoryManagement-failed");
			session.getTransaction().rollback();
			e.printStackTrace();

		}

		return dto;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LmsPackageCategoryMasterDTO getCategories(LmsPackageCategoryMasterDTO dto) {

		Session session = null;
		try {

			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			if (dto.getLimit() == 0) {
				dto.setLimit(Integer.parseInt(env.getProperty("loyalty.default.limit")));
			}

			Criteria cr = session.createCriteria(LmsPackageCategoryMasterDTO.class).setFirstResult(dto.getOffset())
					.setMaxResults(dto.getLimit());
			if (dto.getCategoryId()!=0) {
				cr.add(Restrictions.eq("categoryId", dto.getCategoryId()));	
			}
			
			List mainList = cr.list();

			List<LmsPackageCategoryChildDTO> tempList = new ArrayList<>();

			Iterator<LmsPackageCategoryMasterDTO> tempCatList = mainList.iterator();
			while (tempCatList.hasNext()) {
				LmsPackageCategoryMasterDTO lpm = tempCatList.next();

				List<LmsPackageCategoryChildDTO> tempLpc = lpm.getPackageCategories();

				Iterator<LmsPackageCategoryChildDTO> temp = tempLpc.iterator();

				while (temp.hasNext()) {
					LmsPackageCategoryChildDTO tempVal = temp.next();
					LmsPackageCategoryChildDTO lpc = new LmsPackageCategoryChildDTO();
					lpc.setCategoryId(lpm.getCategoryId());
					lpc.setCategoryName(tempVal.getCategoryName());
					lpc.setCategoryDescription(tempVal.getCategoryDescription());
					lpc.setCategorySynonym(tempVal.getCategorySynonym());
					lpc.setLangId(tempVal.getLangId());
					lpc.setId(tempVal.getId());
					tempList.add(lpc);
				}
			}
			dto.setTotalCount(tempList.size());
			dto.setPackageCategories(tempList);
			dto.setStatusCode("SC0000");
			dto.setStatusDescription("Success");

		} catch (Exception e) {
			// TODO: handle exception
			dto.setStatusCode("SC0001");
			dto.setStatusDescription("Failed");
			e.printStackTrace();

		}

		return dto;
	}
	
	
	public LmsPackageCategoryMasterDTO deleteCategory(LmsPackageCategoryMasterDTO dto) {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(dto);
			session.getTransaction().commit();
			
			dto.setStatusCode("categoryManagement-delSuccess");
			
		} catch (Exception e) {
			// TODO: handle exception
			dto.setStatusCode("categoryManagement-delFailed");
			e.printStackTrace();
			
		}
		
		return dto;
	}
	
}
