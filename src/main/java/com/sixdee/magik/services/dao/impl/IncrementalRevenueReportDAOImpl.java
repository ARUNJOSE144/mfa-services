package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.apache.axis.wsdl.symbolTable.Undefined;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.IncrementalRevenueReportDAO;
import com.sixdee.magik.services.model.ARPUBandsTO;
import com.sixdee.magik.services.model.IncrementalRevenueTO;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class IncrementalRevenueReportDAOImpl implements IncrementalRevenueReportDAO {

	static final Logger logger = Logger.getLogger(IncrementalRevenueReportDAOImpl.class);

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<IncrementalRevenueTO> getData(String month, String arpu, String segment) {

		// List<IncrementalRevenueTO> list =
		// currentSession().createCriteria(IncrementalRevenueTO.class).list();

		System.out.println("===========  month =============" + month);
		System.out.println("===========  arpu =============" + arpu);
		System.out.println("===========  segment =============" + segment);

		Criteria cr = currentSession().createCriteria(IncrementalRevenueTO.class);
		cr.add(Restrictions.eq("month", month));
		if (arpu != null && !arpu.equals("") && !arpu.equals("undefined"))
			cr.add(Restrictions.eq("arpu", arpu));
		if (segment != null && !segment.equals("") && !segment.equals("undefined") && !segment.equals("All"))
			cr.add(Restrictions.eq("segment", segment));
		List<IncrementalRevenueTO> list = cr.list();
		int totalSent = 0;
		int uniqueTargettedSubs = 0;
		double smsSentTargetSubs = 0;
		int totalConverted = 0;
		int uniqueConverted = 0;
		double convPerc = 0;
		double convPercUniqueTGSubs = 0;
		int incrementalRevenue = 0;
		int incrementalRevenuePerSubs = 0;

		IncrementalRevenueTO incRevTO = new IncrementalRevenueTO();
		for (IncrementalRevenueTO incrementalRevenueTO : list) {
			if (incrementalRevenueTO.getTotalSent() != null) {
				totalSent = totalSent + incrementalRevenueTO.getTotalSent();
			}
			if (incrementalRevenueTO.getUniqueTargettedSubs() != null) {
				uniqueTargettedSubs = uniqueTargettedSubs + incrementalRevenueTO.getUniqueTargettedSubs();
			}
			if (incrementalRevenueTO.getSmsSentTargetSubs() != null) {
				smsSentTargetSubs = smsSentTargetSubs + incrementalRevenueTO.getSmsSentTargetSubs();
			}
			if (incrementalRevenueTO.getTotalConverted() != null) {
				totalConverted = totalConverted + incrementalRevenueTO.getTotalConverted();
			}
			if (incrementalRevenueTO.getUniqueConverted() != null) {
				uniqueConverted = uniqueConverted + incrementalRevenueTO.getUniqueConverted();
			}
			if (incrementalRevenueTO.getConvPercUniqueTGSubs() != null) {
				convPercUniqueTGSubs = convPercUniqueTGSubs + incrementalRevenueTO.getConvPercUniqueTGSubs();
			}
			if (incrementalRevenueTO.getConvPerc() != null) {
				convPerc = convPerc + incrementalRevenueTO.getConvPerc();
			}
			if (incrementalRevenueTO.getIncrementalRevenue() != null) {
				incrementalRevenue = incrementalRevenue + incrementalRevenueTO.getIncrementalRevenue();
			}
			if (incrementalRevenueTO.getIncrementalRevenuePerSubs() != null) {
				incrementalRevenuePerSubs = incrementalRevenuePerSubs
						+ incrementalRevenueTO.getIncrementalRevenuePerSubs();
			}
			incRevTO.setArpu("Grand Total");
			incRevTO.setTotalSent(totalSent);
			incRevTO.setUniqueTargettedSubs(uniqueTargettedSubs);
			incRevTO.setSmsSentTargetSubs(smsSentTargetSubs);
			incRevTO.setTotalConverted(totalConverted);
			incRevTO.setUniqueConverted(uniqueConverted);
			incRevTO.setConvPerc(convPerc);
			incRevTO.setConvPercUniqueTGSubs(convPercUniqueTGSubs);
			incRevTO.setIncrementalRevenue(incrementalRevenue);
			incRevTO.setIncrementalRevenuePerSubs(incrementalRevenuePerSubs);
			incRevTO.setMonth(incrementalRevenueTO.getMonth());
		}

		list.add(incRevTO);

		return list;

	}

	@Override
	public List<ARPUBandsTO> getArpuBandData() {
		List<ARPUBandsTO> list = currentSession().createCriteria(ARPUBandsTO.class).list();
		return list;
	}

}
