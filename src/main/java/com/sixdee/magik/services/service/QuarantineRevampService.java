package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.BlackListTO;
import com.sixdee.magik.services.model.DNDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.GlobalFilterTO;
import com.sixdee.magik.services.model.SpecialDateTO;
import com.sixdee.magik.services.model.WeekDayTO;
import com.sixdee.magik.services.model.WhiteListTO;

/**
 * @author Nakhil Kurian
 * @Date : August 2020
 *
 */
public interface QuarantineRevampService {

	GenericTO saveSpecialDates(SpecialDateTO specialDateTO);

	List<SpecialDateTO> getSpecialDatesList();

	GenericTO deleteSpecialDate(SpecialDateTO specialDaysTO);

	GenericTO saveWeekDay(WeekDayTO weekDayTO);

	List<WeekDayTO> getWeekDayList();

	GenericTO deleteWeekDay(WeekDayTO weekDayTo);

	GenericTO saveBlackList(BlackListTO blackListTO);

	List<BlackListTO> getBlackList();

	GenericTO deleteBlackLsit(BlackListTO blackListTo);

	GenericTO saveDND(DNDTO dndTo);

	List<DNDTO> getDND();

	GenericTO deleteDND(DNDTO dndTO);

	GenericTO saveWhiteList(WhiteListTO whitelistTO);

	List<WhiteListTO> getWhiteList();

	GenericTO deleteWhiteList(WhiteListTO whitelistTO);


	GenericTO saveGlobalFilter(GlobalFilterTO globalFilterTo);

	List<GlobalFilterTO> getGlobalFilter();

	GenericTO deleteGlobalFilter(GlobalFilterTO globalFilterTO);

}
