package com.inno.mfa.services.dao;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.TradeEventsTo;
import com.inno.mfa.services.model.TradeLogDetailsTo;
import com.inno.mfa.services.model.TradeLogImageTo;
import com.inno.mfa.services.model.TradeLogMasterTo;
import com.inno.mfa.services.model.TradeSymbolTo;
import com.inno.mfa.services.util.Util;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class TradeLogDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Value("${IMAGE_BASE_PATH_TRADE_LOG}")
	private String imageBasePath_trade_log;

	static final Logger logger = Logger.getLogger(TradeLogDAO.class);

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-SSS");
	Format f = new SimpleDateFormat("EEEE");
	Format f_date = new SimpleDateFormat("dd");

	public TradeLogMasterTo create(TradeLogMasterTo tradeLogMasterTo, CommonRespTo<String> to) {
		String hql = null;
		try {

			Session session = sessionFactory.getCurrentSession();
			tradeLogMasterTo.setCreateDate(new Date());
			tradeLogMasterTo.setModifiedDate(new Date());
			tradeLogMasterTo
					.setDay(f.format(new SimpleDateFormat("yyyy-MM-dd").parse(tradeLogMasterTo.getTradeDate())));
			tradeLogMasterTo.setDate(Integer.parseInt(
					f_date.format(new SimpleDateFormat("yyyy-MM-dd").parse(tradeLogMasterTo.getTradeDate()))));

			logger.info("=========Create Question Record : " + tradeLogMasterTo.toString());
			session.saveOrUpdate(tradeLogMasterTo);

			/*
			 * if (tradeLogMasterTo.getImages().size() > 0) { hql =
			 * "delete from TradeLogImageTo where tradeLogId=" + tradeLogMasterTo.getId();
			 * session.createQuery(hql).executeUpdate();
			 * 
			 * }
			 */

			for (MultipartFile image : tradeLogMasterTo.getImages()) {
				uploadImage(image, "COMMON", tradeLogMasterTo.getId(), 0, session);
			}

			if (tradeLogMasterTo.getTradeLogDetailsTos() != null) {
				for (TradeLogDetailsTo tradeLogDetailsTo : tradeLogMasterTo.getTradeLogDetailsTos()) {
					tradeLogDetailsTo.setTradeLogId(tradeLogMasterTo.getId());

					System.out.println("tradeLogDetailsTo : " + tradeLogDetailsTo);
					session.saveOrUpdate(tradeLogDetailsTo);

					if (tradeLogDetailsTo.getSymbol() == 1 && tradeLogMasterTo.getNiftyImage() != null) {
						uploadImage(tradeLogMasterTo.getNiftyImage(), "NIFTY", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 2 && tradeLogMasterTo.getBankNiftyImage() != null) {
						uploadImage(tradeLogMasterTo.getBankNiftyImage(), "BANK_NIFTY", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 3 && tradeLogMasterTo.getFinNiftyImage() != null) {
						uploadImage(tradeLogMasterTo.getFinNiftyImage(), "FIN_NIFTY", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 4 && tradeLogMasterTo.getDowJohnsImage() != null) {
						uploadImage(tradeLogMasterTo.getDowJohnsImage(), "DOW_JOHNS", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 5 && tradeLogMasterTo.getNasdaqImage() != null) {
						uploadImage(tradeLogMasterTo.getNasdaqImage(), "NASDAQ", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 6 && tradeLogMasterTo.getSp500Image() != null) {
						uploadImage(tradeLogMasterTo.getSp500Image(), "SP_500", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 7 && tradeLogMasterTo.getStock1Image() != null) {
						uploadImage(tradeLogMasterTo.getStock1Image(), "STOCK_1", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 8 && tradeLogMasterTo.getStock2Image() != null) {
						uploadImage(tradeLogMasterTo.getStock2Image(), "STOCK_2", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
				}
			}
			to.setResultCode(0);
			to.setResponseMsg("Details Added...");
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
			to.setResultCode(100);

			if (e.getCause().toString().contains("UNIQUE_KEY_TRADE_DATE")) {
				to.setResponseMsg("Trade Date already Exist...");
			} else {
				to.setResponseMsg("Application Process Error...");
			}

		}
		return tradeLogMasterTo;
	}

	public void uploadImage(MultipartFile image, String type, int tradeLogId, int tradeLogDetailsId, Session session)
			throws IOException {
		String newFileName = "";
		String hql = "";

		newFileName = "IMG" + "_" + format.format(new Date()) + "_" + type + "."
				+ image.getOriginalFilename().split("\\.")[1];

		String directory = imageBasePath_trade_log + tradeLogId;
		new File(directory).mkdirs();
		String fileName = directory + "/" + newFileName;

		Path filepath = Paths.get(fileName);
		try (OutputStream os = Files.newOutputStream(filepath)) {
			os.write(image.getBytes());
		}

		if (tradeLogDetailsId != 0) {
			hql = "delete from TradeLogImageTo where tradeLogDetailsId=" + tradeLogDetailsId + " and tradeLogId="
					+ tradeLogId;
			session.createQuery(hql).executeUpdate();

		}

		TradeLogImageTo tradeLogImageTo = new TradeLogImageTo();
		tradeLogImageTo.setImagePath(fileName);
		tradeLogImageTo.setTradeLogId(tradeLogId);
		tradeLogImageTo.setTradeLogDetailsId(tradeLogDetailsId);
		session.save(tradeLogImageTo);

	}

	public PaginationTo<List<TradeLogMasterTo>> search(TradeLogMasterTo searchTO) {
		List<TradeLogMasterTo> list = new ArrayList<TradeLogMasterTo>();
		PaginationTo<List<TradeLogMasterTo>> paginationTo = new PaginationTo<List<TradeLogMasterTo>>();
		try {
			Session session = sessionFactory.getCurrentSession();

			paginationTo.setDataTotalSize(getRowCountForSearch(searchTO, session));

			Criteria criteria = session.createCriteria(TradeLogMasterTo.class);
			criteria.add(Restrictions.eq("softDelete", 0));

			if (searchTO.getTradeDate() != null) {
				criteria.add(Restrictions.eq("tradeDate", searchTO.getTradeDate()));
			}

			if (searchTO.getDayList() != null && searchTO.getDayList().size() > 0) {
				criteria.add(Restrictions.in("day", searchTO.getDayList()));
			}

			if (searchTO.getDateList() != null && searchTO.getDateList().size() > 0) {
				criteria.add(Restrictions.in("date", searchTO.getDateList()));
			}

			if (searchTO.getEvents() != null && !searchTO.getEvents().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("events", searchTO.getEvents(), MatchMode.ANYWHERE));
			}

			if (searchTO.getComments() != null && !searchTO.getComments().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("comments", searchTO.getComments(), MatchMode.ANYWHERE));
			}

			criteria.addOrder(Order.desc("id"));
			criteria.setFirstResult(0);
			criteria.setMaxResults(searchTO.getRowCount());

			list = criteria.list();
			paginationTo.setData(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paginationTo;
	}

	public Integer getRowCountForSearch(TradeLogMasterTo searchTO, Session session) {
		Long count = 0l;
		try {
			// Session session = sessionFactory.getCurrentSession();

			Criteria criteria = session.createCriteria(TradeLogMasterTo.class);
			criteria.setProjection(Projections.rowCount());
			criteria.add(Restrictions.eq("softDelete", 0));

			if (searchTO.getTradeDate() != null) {
				criteria.add(Restrictions.eq("tradeDate", searchTO.getTradeDate()));
			}

			if (searchTO.getDay() != null) {
				criteria.add(Restrictions.in("day", searchTO.getDayList()));
			}

			if (searchTO.getDate() != 0) {
				criteria.add(Restrictions.in("date", searchTO.getDateList()));
			}

			if (searchTO.getEvents() != null && !searchTO.getEvents().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("events", searchTO.getEvents(), MatchMode.ANYWHERE));
			}

			if (searchTO.getComments() != null && !searchTO.getComments().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("comments", searchTO.getComments(), MatchMode.ANYWHERE));
			}

			count = (Long) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	public PaginationTo<TradeLogMasterTo> getDaysList(PaginationTo<TradeLogMasterTo> paginationTo) {
		List<TradeLogMasterTo> list = new ArrayList<TradeLogMasterTo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			if (paginationTo.getDataTotalSize() == 0) {
				paginationTo.setDataTotalSize(getRowCount(paginationTo, session));
			}

			Criteria criteria = session.createCriteria(TradeLogMasterTo.class);
			criteria.add(Restrictions.eq("softDelete", 0));
			/*
			 * criteria.setProjection(Projections.projectionList().add(Projections.alias(
			 * Projections.property("id"), "id"))
			 * .add(Projections.alias(Projections.property("name"), "name"))
			 * .add(Projections.alias(Projections.property("key"), "key"))
			 * .add(Projections.alias(Projections.property("questionFrom"), "questionFrom"))
			 * .add(Projections.alias(Projections.property("answer"), "answer")))
			 * .setResultTransformer(Transformers.aliasToBean(QuestionMasterTo.class));
			 */

			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("tradeDate", "%" + paginationTo.getSearchKey1() + "%"));
			}

			criteria.addOrder(Order.desc("id"));
			criteria.setFirstResult(paginationTo.getFirstRecord());
			criteria.setMaxResults(paginationTo.getRecordCount());
			list = criteria.list();

			/*
			 * List<TradeLogMasterTo> processedList = new ArrayList<TradeLogMasterTo>(); for
			 * (TradeLogMasterTo questionMasterTo : list) {
			 * questionMasterTo.setHavingAnswer(questionMasterTo.getAnswer().
			 * equalsIgnoreCase("") ? 2 : 1); questionMasterTo.setAnswer(null);
			 * processedList.add(questionMasterTo); }
			 */

			paginationTo.setList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paginationTo;
	}

	public Integer getRowCount(PaginationTo<TradeLogMasterTo> paginationTo, Session session) {
		Long count = 0l;
		try {
			Criteria criteria = session.createCriteria(TradeLogMasterTo.class);
			criteria.add(Restrictions.eq("softDelete", 0));
			criteria.setProjection(Projections.rowCount());
			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("tradeDate", "%" + paginationTo.getSearchKey1() + "%"));
			}
			count = (Long) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	public TradeLogMasterTo view(TradeLogMasterTo dto) {
		Criteria criteria = null;
		Session session = null;
		TradeLogMasterTo tempRequest = dto;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(TradeLogMasterTo.class);
			criteria.add(Restrictions.eq("id", dto.getId()));
			dto = (TradeLogMasterTo) criteria.uniqueResult();

			criteria = session.createCriteria(TradeLogImageTo.class);
			criteria.add(Restrictions.eq("tradeLogId", dto.getId()));
			criteria.add(Restrictions.eq("tradeLogDetailsId", 0));
			List<TradeLogImageTo> imagelist = (List<TradeLogImageTo>) criteria.list();
			dto.setImageList(imagelist);

			criteria = session.createCriteria(TradeLogDetailsTo.class);
			criteria.add(Restrictions.eq("tradeLogId", dto.getId()));
			if (tempRequest.getShowResultOf() != null && tempRequest.getShowResultOf().size() > 0) {
				criteria.add(Restrictions.in("symbol", tempRequest.getShowResultOf()));
			}
			List<TradeLogDetailsTo> list = (List<TradeLogDetailsTo>) criteria.list();

			for (TradeLogDetailsTo tradeLogDetailsTo : list) {
				criteria = session.createCriteria(TradeLogImageTo.class);
				criteria.add(Restrictions.eq("tradeLogId", dto.getId()));
				criteria.add(Restrictions.eq("tradeLogDetailsId", tradeLogDetailsTo.getId()));
				TradeLogImageTo imageTo = (TradeLogImageTo) criteria.uniqueResult();
				tradeLogDetailsTo.setTradeLogImageTo(imageTo);
			}

			dto.setTradeLogDetailsTos(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	public List<TradeEventsTo> getEvents() {
		Criteria criteria = null;
		Session session = null;
		List<TradeEventsTo> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(TradeEventsTo.class);
			list = (List<TradeEventsTo>) criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TradeSymbolTo> getSymbols() {
		Criteria criteria = null;
		Session session = null;
		List<TradeSymbolTo> list = null;
		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(TradeSymbolTo.class);
			list = (List<TradeSymbolTo>) criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void delete(TradeLogMasterTo to) {
		Session session = null;
		String hql = null;
		try {
			session = sessionFactory.getCurrentSession();
			TradeLogMasterTo masterTo = (TradeLogMasterTo) session.createCriteria(TradeLogMasterTo.class)
					.add(Restrictions.eq("id", to.getId())).uniqueResult();

			masterTo.setSoftDelete(1);
			session.update(masterTo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
