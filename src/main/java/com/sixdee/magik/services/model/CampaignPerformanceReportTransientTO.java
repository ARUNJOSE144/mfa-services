package com.sixdee.magik.services.model;

import java.util.List;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
public class CampaignPerformanceReportTransientTO {
	
	private List<TGbaseReportTO> tgBaselist;
	private List<CGBaseReportTO> cgBaselist;
	private List<WeekNetConvReportTO> weekNetlist;
	private List<WeekRevenueBaseReportTO> weekRevenuelist;
	private List<ShortSummaryReportTO> shortSummarylist;

	
	public List<TGbaseReportTO> getTgBaselist() {
		return tgBaselist;
	}

	public void setTgBaselist(List<TGbaseReportTO> tgBaselist) {
		this.tgBaselist = tgBaselist;
	}

	public List<CGBaseReportTO> getCgBaselist() {
		return cgBaselist;
	}

	public void setCgBaselist(List<CGBaseReportTO> cgBaselist) {
		this.cgBaselist = cgBaselist;
	}

	public List<WeekNetConvReportTO> getWeekNetlist() {
		return weekNetlist;
	}

	public void setWeekNetlist(List<WeekNetConvReportTO> weekNetlist) {
		this.weekNetlist = weekNetlist;
	}

	public List<WeekRevenueBaseReportTO> getWeekRevenuelist() {
		return weekRevenuelist;
	}

	public void setWeekRevenuelist(List<WeekRevenueBaseReportTO> weekRevenuelist) {
		this.weekRevenuelist = weekRevenuelist;
	}

	public List<ShortSummaryReportTO> getShortSummarylist() {
		return shortSummarylist;
	}

	public void setShortSummarylist(List<ShortSummaryReportTO> shortSummarylist) {
		this.shortSummarylist = shortSummarylist;
	}
	
	

}
