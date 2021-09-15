package com.inno.mfa.services.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.QuestionImageTo;
import com.inno.mfa.services.model.QuestionMasterTo;
import com.inno.mfa.services.util.Util;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class QuestionsDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Value("${IMAGE_BASE_PATH}")
	private String imageBasePath;

	static final Logger logger = Logger.getLogger(QuestionsDAO.class);

	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	@SuppressWarnings("unchecked")
	public PaginationTo<QuestionMasterTo> getQustionList(PaginationTo<QuestionMasterTo> paginationTo) {
		List<QuestionMasterTo> list = new ArrayList<QuestionMasterTo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			if (paginationTo.getDataTotalSize() == 0) {
				paginationTo.setDataTotalSize(getRowCount(paginationTo, session));
			}

			Criteria criteria = session.createCriteria(QuestionMasterTo.class);
			criteria.setProjection(Projections.projectionList().add(Projections.alias(Projections.property("id"), "id"))
					.add(Projections.alias(Projections.property("name"), "name"))
					.add(Projections.alias(Projections.property("key"), "key"))
					.add(Projections.alias(Projections.property("questionFrom"), "questionFrom")))
					.setResultTransformer(Transformers.aliasToBean(QuestionMasterTo.class));

			criteria.add(Restrictions.eq("softDelete", 0));
			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("name", "%" + paginationTo.getSearchKey1() + "%"));
			}
			criteria.setFirstResult(paginationTo.getFirstRecord());
			criteria.setMaxResults(paginationTo.getRecordCount());
			list = criteria.list();
			paginationTo.setList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paginationTo;
	}

	public Integer getRowCount(PaginationTo<QuestionMasterTo> paginationTo, Session session) {
		Long count = 0l;
		try {
			Criteria criteria = session.createCriteria(QuestionMasterTo.class);
			criteria.add(Restrictions.eq("softDelete", 0));
			criteria.setProjection(Projections.rowCount());
			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("roleName", "%" + paginationTo.getSearchKey1() + "%"));
			}
			count = (Long) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count.intValue();
	}

	/*
	 * @SuppressWarnings("unchecked") public void
	 * getModulePermission(CommonRespTo<ModuleMasterTo> to) { List<ModuleMasterTo>
	 * masterTos = null; List<FeatureMasterTo> featureMasterTos = null; try {
	 * Session session = sessionFactory.getCurrentSession(); Criteria criteria =
	 * session.createCriteria(ModuleMasterTo.class); masterTos =
	 * (List<ModuleMasterTo>) criteria.list();
	 * 
	 * criteria = session.createCriteria(FeatureMasterTo.class); featureMasterTos =
	 * (List<FeatureMasterTo>) criteria.list();
	 * 
	 * for (ModuleMasterTo mto : masterTos) { List<FeatureMasterTo> list = new
	 * ArrayList<FeatureMasterTo>(); for (FeatureMasterTo fto : featureMasterTos) {
	 * if (fto.getModuleId() == mto.getModuleId()) { list.add(fto); } }
	 * mto.setFeatures(list);
	 * 
	 * } to.setList(masterTos); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	public void create(QuestionMasterTo questionMasterTo) {
		String newFileName = "";
		int previousQuestionid = questionMasterTo.getId();
		String hql = null;
		try {

			Session session = sessionFactory.getCurrentSession();
			questionMasterTo.setCreatedTime(new Date());

			logger.info("=========Create Question Record : " + questionMasterTo.toString());
			session.saveOrUpdate(questionMasterTo);

			if (previousQuestionid != 0 && questionMasterTo.getFiles().size() > 0) {
				hql = "delete from QuestionImageTo where questionId=" + previousQuestionid;
				session.createQuery(hql).executeUpdate();

			}

			for (int i = 0; i < questionMasterTo.getFiles().size(); i++) {
				newFileName = "IMG" + "_" + format.format(new Date()) + i + "."
						+ questionMasterTo.getFiles().get(i).getOriginalFilename().split("\\.")[1];

				String directory = imageBasePath + questionMasterTo.getId();
				new File(directory).mkdirs();
				String fileName = directory + "/" + newFileName;

				Path filepath = Paths.get(fileName);
				try (OutputStream os = Files.newOutputStream(filepath)) {
					os.write(questionMasterTo.getFiles().get(i).getBytes());
				}

				QuestionImageTo imageTo = new QuestionImageTo();
				imageTo.setQuestionId(questionMasterTo.getId());
				imageTo.setImage(fileName);
				session.save(imageTo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<QuestionMasterTo> searchQuestion(QuestionMasterTo searchTO) {
		List<QuestionMasterTo> list = new ArrayList<QuestionMasterTo>();
		try {
			Session session = sessionFactory.getCurrentSession();

			Criteria criteria = session.createCriteria(QuestionMasterTo.class);
			criteria.add(Restrictions.eq("softDelete", 0));

			criteria.setProjection(Projections.projectionList().add(Projections.alias(Projections.property("id"), "id"))
					.add(Projections.alias(Projections.property("name"), "name"))
					.add(Projections.alias(Projections.property("key"), "key"))
					.add(Projections.alias(Projections.property("questionFrom"), "questionFrom")))
					.setResultTransformer(Transformers.aliasToBean(QuestionMasterTo.class));

			if (searchTO.getQuestionFrom() != null && Util.validate(searchTO.getQuestionFrom() + "")
					&& searchTO.getQuestionFrom() != 0) {
				criteria.add(Restrictions.eq("questionFrom", searchTO.getQuestionFrom()));
			}

			Disjunction orRes = Restrictions.disjunction();

			if (Util.validate(searchTO.getKey())) {
				orRes.add(Restrictions.ilike("key", "%" + searchTO.getKey().trim() + "%"));
			}
			if (Util.validate(searchTO.getQuestion())) {
				orRes.add(Restrictions.ilike("question", "%" + searchTO.getQuestion().trim() + "%"));
			}
			if (Util.validate(searchTO.getAnswer())) {
				orRes.add(Restrictions.ilike("answer", "%" + searchTO.getAnswer().trim() + "%"));
			}
			if (Util.validate(searchTO.getName())) {
				orRes.add(Restrictions.ilike("name", "%" + searchTO.getName().trim() + "%"));
			}
			criteria.add(orRes);

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public QuestionMasterTo view(QuestionMasterTo dto) {
		Criteria criteria = null;
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(QuestionMasterTo.class);
			criteria.add(Restrictions.eq("id", dto.getId()));
			dto = (QuestionMasterTo) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public void delete(QuestionMasterTo to) {
		Session session = null;
		String hql = null;
		try {
			session = sessionFactory.getCurrentSession();
			QuestionMasterTo questionMasterTo = (QuestionMasterTo) session.createCriteria(QuestionMasterTo.class)
					.add(Restrictions.eq("id", to.getId())).uniqueResult();

			questionMasterTo.setSoftDelete(1);
			session.update(questionMasterTo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * @SuppressWarnings("unchecked") public List<RolesTo> getAllRoles() { Criteria
	 * criteria = null; Session session = null; List<RolesTo> rolesTo = new
	 * ArrayList<RolesTo>();
	 * 
	 * try { session = sessionFactory.getCurrentSession(); criteria =
	 * session.createCriteria(RolesTo.class); rolesTo = (List<RolesTo>)
	 * criteria.list(); } catch (Exception e) { e.printStackTrace(); } return
	 * rolesTo; }
	 */

	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, String imageName) {

		String file = imageName;
		InputStream out = null;
		BufferedInputStream bufferedInputStream = null;
		InputStream fis = null;

		OutputStream outex = null;
		try {
			outex = resp.getOutputStream();
			resp.setContentType("APPLICATION/OCTET-STREAM");
			resp.setHeader("Content-Disposition", "inline; filename=\"" + imageName + "\"");

			file = file;
			logger.info("File Path:" + file);
			out = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(out);
			int i;
			while ((i = bufferedInputStream.read()) != -1) {
				outex.write(i);
			}
			logger.info("Download Completed for Image : " + imageName);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
					fis = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outex != null) {
				try {
					outex.flush();
					outex.close();
					outex = null;
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
	}

	public List<QuestionImageTo> getImageDetails(QuestionImageTo imageTo) {
		List<QuestionImageTo> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			Criteria criteria = session.createCriteria(QuestionImageTo.class)
					.add(Restrictions.eq("questionId", imageTo.getQuestionId()));

			list = criteria.list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
