package com.sixdee.magik.services.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.QuarantineDAO;
import com.sixdee.magik.services.dao.QuarantineRevampDAO;
import com.sixdee.magik.services.model.BlackListDetailsTO;
import com.sixdee.magik.services.model.BlackListTO;
import com.sixdee.magik.services.model.DNDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.GlobalFilterTO;
import com.sixdee.magik.services.model.SpecialDateDetailsTO;
import com.sixdee.magik.services.model.SpecialDateTO;
import com.sixdee.magik.services.model.WeekDayTO;
import com.sixdee.magik.services.model.WhiteListDetailsTO;
import com.sixdee.magik.services.model.WhiteListTO;
import com.sixdee.magik.services.service.QuarantineRevampService;

@Service
@Transactional
public class QuarantineRevampServiceImpl implements QuarantineRevampService {

	@Autowired
	QuarantineRevampDAO quarantineRevampDAO;

	@Override
	public GenericTO saveSpecialDates(SpecialDateTO specialDateTO) {
		System.out.println("saveSpecialDates Service Impl :: ");
		System.out.println("saveSpecialDates Service Impl  id  :: " + specialDateTO.getMarketingPlanId());
		Set<SpecialDateDetailsTO> daysDetailsList = new HashSet<SpecialDateDetailsTO>();
		String flag = specialDateTO.getIsFromFile();
		if (flag.equals("true")) {
			if (specialDateTO.getMarketingPlanId() != null) {
				String marketingPlans[] = specialDateTO.getMarketingPlanId().split(",");
				for (int i = 0; i < marketingPlans.length; i++) {
					for (SpecialDateTO specialDaysDetails : specialDateTO.getDesclist()) {
						SpecialDateDetailsTO daysDetails = new SpecialDateDetailsTO();
						daysDetails.setDescription(specialDaysDetails.getDescription());
						daysDetails.setSpecialDate(specialDaysDetails.getSpecialDate());
						daysDetails.setMarketingPlanId(Integer.parseInt(marketingPlans[i]));
						daysDetailsList.add(daysDetails);
					}
				}
			}
		} else {
			if (specialDateTO.getMarketingPlanId() != null) {
				String marketingPlans[] = specialDateTO.getMarketingPlanId().split(",");
				for (int i = 0; i < marketingPlans.length; i++) {
					SpecialDateDetailsTO daysDetails = new SpecialDateDetailsTO();
					daysDetails.setDescription(specialDateTO.getDescription());
					daysDetails.setSpecialDate(specialDateTO.getSpecialDate());
					daysDetails.setSpecialDateStartTime(specialDateTO.getSpecialDateStartTime());
					daysDetails.setSpecialDateEndTime(specialDateTO.getSpecialDateEndTime());
					daysDetails.setMarketingPlanId(Integer.parseInt(marketingPlans[i]));
					daysDetailsList.add(daysDetails);
				}
			}
		}
		specialDateTO.setSpecialDaysDetails(daysDetailsList);
		return quarantineRevampDAO.saveSpecialDates(specialDateTO);
	}

	@Override
	public List<SpecialDateTO> getSpecialDatesList() {
		// TODO Auto-generated method stub
		return quarantineRevampDAO.getSpecialDatesList();
	}

	@Override
	public GenericTO deleteSpecialDate(SpecialDateTO specialDaysTO) {
		// TODO Auto-generated method stub
		return quarantineRevampDAO.deleteSpecialDate(specialDaysTO);
	}

	@Override
	public GenericTO saveWeekDay(WeekDayTO weekDayTO) {
		// TODO Auto-generated method stub
		return quarantineRevampDAO.saveWeekDay(weekDayTO);
	}

	@Override
	public List<WeekDayTO> getWeekDayList() {
		// TODO Auto-generated method stub
		return quarantineRevampDAO.getWeekDayList();
	}

	@Override
	public GenericTO deleteWeekDay(WeekDayTO weekDayTo) {
		// TODO Auto-generated method stub
		return quarantineRevampDAO.deleteWeekDay(weekDayTo);
	}

	@Override
	public GenericTO saveBlackList(BlackListTO blackListTO) {
		GenericTO genericTO = null;
		System.out.println("saveBlackList Service Impl ::" + blackListTO);
		System.out.println("saveBlackList Service Impl MarketingPlanId ::" + blackListTO.getMarketingPlanId());
		blackListTO.setCreateDate(new Date());
		String filName = blackListTO.getFileUploadName();
		if (filName != null && !filName.isEmpty()) {
			Set<BlackListDetailsTO> blackListTOs = new HashSet<BlackListDetailsTO>();
			if (blackListTO.getMarketingPlanId() != null) {
				String marketingPlans[] = blackListTO.getMarketingPlanId().split(",");
				for (int i = 0; i < marketingPlans.length; i++) {
					for (BlackListTO blNumDate : blackListTO.getMsisdnList()) {
						BlackListDetailsTO blackListDetailsTO = new BlackListDetailsTO();
						blackListDetailsTO.setMsisdn(blNumDate.getMsisdn());
						// blackListDetailsTO.setDescription(blNumDate.getDescription());
						System.out.println("getExpiryDate   " + blNumDate.getExpiryDate());
						String string = blNumDate.getExpiryDate();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date date;
						try {
							date = format.parse(string);
							System.out.println("setExpiryDate   " + date);
							blackListDetailsTO.setExpiryDate(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						blackListDetailsTO.setMarketingPlanId(Integer.parseInt(marketingPlans[i]));
						blackListTOs.add(blackListDetailsTO);
					}
				}
			}

			blackListTO.setDetailsTo(blackListTOs);
			genericTO = quarantineRevampDAO.saveBlackList(blackListTO);
		} else {
			Set<BlackListDetailsTO> blackListTOs = new HashSet<BlackListDetailsTO>();
			if (blackListTO.getMarketingPlanId() != null) {
				String marketingPlans[] = blackListTO.getMarketingPlanId().split(",");
				for (int i = 0; i < marketingPlans.length; i++) {
					BlackListDetailsTO blackListDetailsTO = new BlackListDetailsTO();
					blackListDetailsTO.setMsisdn(blackListTO.getMsisdn());
					blackListDetailsTO.setDescription(blackListTO.getDescription());
					blackListTOs.add(blackListDetailsTO);
					blackListTO.setDetailsTo(blackListTOs);
					blackListDetailsTO.setMarketingPlanId(Integer.parseInt(marketingPlans[i]));
					genericTO = quarantineRevampDAO.saveBlackList(blackListTO);
				}
			}
		}
		return genericTO;

	}

	@Override
	public List<BlackListTO> getBlackList() {
		return quarantineRevampDAO.getBlackList();
	}

	@Override
	public GenericTO deleteBlackLsit(BlackListTO blackListTo) {
		// TODO Auto-generated method stub
		return quarantineRevampDAO.deleteBlackLsit(blackListTo);
	}

	@Override
	public GenericTO saveDND(DNDTO dndTo) {
		// TODO Auto-generated method stub
		return quarantineRevampDAO.saveDND(dndTo);
	}

	@Override
	public List<DNDTO> getDND() {
		return quarantineRevampDAO.getDND();
	}

	@Override
	public GenericTO deleteDND(DNDTO dndTO) {
		return quarantineRevampDAO.deleteDND(dndTO);
	}

	@Override
	public GenericTO saveWhiteList(WhiteListTO whitelistTO) {
		GenericTO genericTO = null;
		System.out.println("saveWhiteList Service Impl ::" + whitelistTO);
		whitelistTO.setCreateDate(new Date());
		String filName = whitelistTO.getFileUploadName();
		if (filName != null && !filName.isEmpty()) {
			Set<WhiteListDetailsTO> blackListTOs = new HashSet<WhiteListDetailsTO>();
			for (WhiteListTO blNumDate : whitelistTO.getMsisdnList()) {
				WhiteListDetailsTO blackListDetailsTO = new WhiteListDetailsTO();
				blackListDetailsTO.setMsisdn(blNumDate.getMsisdn());
				blackListDetailsTO.setExpiryDate(blNumDate.getExpiryDate());
				blackListDetailsTO.setDescription(blNumDate.getDescription());
				blackListTOs.add(blackListDetailsTO);
			}

			whitelistTO.setDetailsTo(blackListTOs);
			genericTO = quarantineRevampDAO.savewhiteList(whitelistTO);
		} else {
			Set<WhiteListDetailsTO> blackListTOs = new HashSet<WhiteListDetailsTO>();
			WhiteListDetailsTO blackListDetailsTO = new WhiteListDetailsTO();
			blackListDetailsTO.setMsisdn(whitelistTO.getMsisdn());
			blackListDetailsTO.setExpiryDate(whitelistTO.getExpiryDate());
			blackListDetailsTO.setDescription(whitelistTO.getDescription());
			blackListTOs.add(blackListDetailsTO);
			whitelistTO.setDetailsTo(blackListTOs);
			genericTO = quarantineRevampDAO.savewhiteList(whitelistTO);
		}

		return genericTO;
	}

	@Override
	public List<WhiteListTO> getWhiteList() {
		return quarantineRevampDAO.getWhiteList();
	}

	@Override
	public GenericTO deleteWhiteList(WhiteListTO whitelistTO) {
		return quarantineRevampDAO.deleteWhiteList(whitelistTO);
	}

	@Override
	public GenericTO saveGlobalFilter(GlobalFilterTO globalFilterTo) {
		return quarantineRevampDAO.saveGlobalFilter(globalFilterTo);
	}

	@Override
	public List<GlobalFilterTO> getGlobalFilter() {
		return quarantineRevampDAO.getGlobalFilter();
	}

	@Override
	public GenericTO deleteGlobalFilter(GlobalFilterTO globalFilterTO) {
		return quarantineRevampDAO.deleteGlobalFilter(globalFilterTO);
	}
}