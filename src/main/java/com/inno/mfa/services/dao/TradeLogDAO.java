package com.inno.mfa.services.dao;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.TradeLogDetailsTo;
import com.inno.mfa.services.model.TradeLogImageTo;
import com.inno.mfa.services.model.TradeLogMasterTo;

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

	@Value("${IMAGE_BASE_PATH}")
	private String imageBasePath;

	static final Logger logger = Logger.getLogger(TradeLogDAO.class);

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-SSS");
	Format f = new SimpleDateFormat("EEEE");

	public TradeLogMasterTo create(TradeLogMasterTo tradeLogMasterTo, CommonRespTo<String> to) {
		String hql = null;
		try {

			Session session = sessionFactory.getCurrentSession();
			tradeLogMasterTo.setCreateDate(new Date());
			tradeLogMasterTo.setModifiedDate(new Date());
			tradeLogMasterTo
					.setDay(f.format(new SimpleDateFormat("yyyy-MM-dd").parse(tradeLogMasterTo.getTradeDate())));

			logger.info("=========Create Question Record : " + tradeLogMasterTo.toString());
			session.saveOrUpdate(tradeLogMasterTo);

			if (tradeLogMasterTo.getImages().size() > 0) {
				hql = "delete from TradeLogImageTo where tradeLogId=" + tradeLogMasterTo.getId();
				session.createQuery(hql).executeUpdate();

			}

			for (MultipartFile image : tradeLogMasterTo.getImages()) {
				uploadImage(image, "COMMON", tradeLogMasterTo.getId(), 0, session);
			}

			if (tradeLogMasterTo.getTradeLogDetailsTos() != null) {
				for (TradeLogDetailsTo tradeLogDetailsTo : tradeLogMasterTo.getTradeLogDetailsTos()) {
					tradeLogDetailsTo.setTradeLogId(tradeLogMasterTo.getId());

					System.out.println("tradeLogDetailsTo : " + tradeLogDetailsTo);
					session.save(tradeLogDetailsTo);

					if (tradeLogDetailsTo.getSymbol() == 1 && tradeLogMasterTo.getNiftyImage() != null) {
						uploadImage(tradeLogMasterTo.getNiftyImage(), "NIFTY", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
					if (tradeLogDetailsTo.getSymbol() == 2 && tradeLogMasterTo.getBankNiftyImage() != null) {
						uploadImage(tradeLogMasterTo.getBankNiftyImage(), "BANK_NIFTY", tradeLogMasterTo.getId(),
								tradeLogDetailsTo.getId(), session);
					}
				}
			}
			to.setResultCode(0);
			to.setResponseMsg("Details Added...");
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
			System.out.println("Error : " + e.getCause().toString());
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

		newFileName = "IMG" + "_" + format.format(new Date()) + "_" + type + "."
				+ image.getOriginalFilename().split("\\.")[1];

		String directory = imageBasePath + tradeLogId;
		new File(directory).mkdirs();
		String fileName = directory + "/" + newFileName;

		Path filepath = Paths.get(fileName);
		try (OutputStream os = Files.newOutputStream(filepath)) {
			os.write(image.getBytes());
		}

		TradeLogImageTo tradeLogImageTo = new TradeLogImageTo();
		tradeLogImageTo.setImagePath(fileName);
		tradeLogImageTo.setTradeLogId(tradeLogId);
		tradeLogImageTo.setTradeLogDetailsId(tradeLogDetailsId);
		session.save(tradeLogImageTo);

	}

}
