package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.ads.adwords.axis.v201809.cm.Function;
import com.google.gson.Gson;
import com.sixdee.magik.services.dao.SamplingDAO;
import com.sixdee.magik.services.model.Action;
import com.sixdee.magik.services.model.ActionHeaderParameterTo;
import com.sixdee.magik.services.model.Exclude;
import com.sixdee.magik.services.model.Field;
import com.sixdee.magik.services.model.LeadPolicy;
import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.model.LogicalStep;
import com.sixdee.magik.services.model.MultiParam;
import com.sixdee.magik.services.model.Param;
import com.sixdee.magik.services.model.Sampling;
import com.sixdee.magik.services.model.SamplingLDTO;
import com.sixdee.magik.services.model.SamplingNDTO;
import com.sixdee.magik.services.model.SamplingTO;
import com.sixdee.magik.services.model.SamplingTypesTO;
import com.sixdee.magik.services.model.Step;
import com.sixdee.magik.services.model.StratSegProfilesBandsTO;
import com.sixdee.magik.services.model.StratSegProfilesTO;
import com.sixdee.magik.services.model.UserSessionTO;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Rajesh
 * @category SamplingDAO Implements
 * 
 */

@Repository
@Transactional
public class SamplingDAOImpl implements SamplingDAO {

	static Logger logger = Logger.getLogger(SamplingDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	private Session session = null;
	private String query = null;
	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Override
	public RestInfo<String> getSamplingNodes() {
		logger.info("Class : SamplingDAOImpl | Method : getSamplingNodes");
		RestInfo<String> res = new RestInfo<String>();
		res.setOperationCode(1);

		Gson gson = null;
		String jsonString = "";
		List<SamplingTypesTO> samplingTypesList = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "FROM SamplingTypesTO";
			System.out.println("SQL Query : " + query);
			samplingTypesList = (List<SamplingTypesTO>) session.createQuery(query).list();

			if (!samplingTypesList.isEmpty()) {
				gson = new Gson();
				jsonString = gson.toJson(samplingTypesList.toArray());
			}

			res.setOperationCode(0);
			res.setMessage("Sampling Nodes Getting Successfully");
			res.setData(jsonString);
		} catch (Exception e) {
			System.out.println("Exception in getSamplingNodes ");
			res.setMessage("Try Again...");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void saveSampling(SamplingTO samplingTO, String flag) {
		logger.info("Class : SamplingDAOImpl | Method : saveSampling");
		RestInfo<String> info = new RestInfo<String>();

		session = sessionFactory.getCurrentSession();

		if (flag.equals("UPDATE")) {

			SamplingTO to = (SamplingTO) session.get(SamplingTO.class, samplingTO.getId());
			to.setNodeData(samplingTO.getNodeData());
			to.setLinkedData(samplingTO.getLinkedData());
			to.setSegmentData(samplingTO.getSegmentData());
			to.setStratifiedData(samplingTO.getStratifiedData());

			// samplingTO.setId(samplingTO.getId());
			session.update(to);
		} else {
			/*
			 * samplingTO.setSamplingName(samplingTO.getSamplingName());
			 * samplingTO.setNodeData(samplingTO.getNodeData());;
			 * samplingTO.setLinkedData(samplingTO.getLinkedData());
			 * samplingTO.setSegmentData(samplingTO.getSegmentData());
			 */
			samplingTO.setCreateDate(new Date());
			session.save(samplingTO);
		}
	}

	@Override
	public RestInfo<String> getSamplingDetails(int samplingId) {

		logger.info("Class : SamplingDAOImpl | Method : getSamplingDetails");
		RestInfo<String> res = new RestInfo<String>();
		res.setOperationCode(1);

		Gson gson = null;
		String jsonString = "";

		SamplingTO samplingTO = null;

		try {
			session = sessionFactory.getCurrentSession();
			System.out.println("SQL Query : " + query);
			samplingTO = (SamplingTO) session.get(SamplingTO.class, samplingId);
			gson = new Gson();
			jsonString = gson.toJson(samplingTO);
			res.setOperationCode(0);
			res.setMessage("Sample details getting Successfully");
			res.setData(jsonString);
		} catch (Exception e) {
			System.out.println("Exception in getSamplingDetails ");
			res.setMessage("Try Again...");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void renameSampling(int id, String name) {
		logger.info("Class : SamplingDAOImpl | Method : renameSampling");

		session = sessionFactory.getCurrentSession();
		SamplingTO samplingTO = (SamplingTO) session.get(SamplingTO.class, id);
		samplingTO.setSamplingName(name);
		session.update(samplingTO);
	}

	@Override
	public void deleteSampling(int id) {
		logger.info("Class : SamplingDAOImpl | Method : deleteSampling");

		logger.info("Class : SamplingDAOImpl | Method : renameSampling()");
		session = sessionFactory.getCurrentSession();

		SamplingTO samplingTO = (SamplingTO) session.load(SamplingTO.class, id);
		if (null != samplingTO) {
			session.delete(samplingTO);
		}
	}

	@Override
	public List<SamplingTO> getSamplesList(UserSessionTO user) {
		logger.info("Class : SamplingDAOImpl | Method : getSamplesList");
		RestListInfo<SamplingTO> res = new RestListInfo<SamplingTO>();
		res.setOperationCode(1);

		List<SamplingTO> samplingList = new ArrayList<SamplingTO>();
		SamplingTO to = null;
		session = sessionFactory.getCurrentSession();
		query = "SELECT id,samplingName,createDate FROM SamplingTO ";
		if (user.getUserId() != 1)
			query += " where userId=" + user.getUserId();
		query += " order by createDate desc";
		System.out.println("SQL Query : " + query);
		List<Object[]> list = (List<Object[]>) session.createQuery(query).list();

		for (Object[] obj : list) {
			to = new SamplingTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setSamplingName(obj[1] + "");
			to.setStrCreateDate(dateConvertion(obj[2] + ""));

			samplingList.add(to);
		}

		return samplingList;
	}

	@Override
	public Sampling getSamplingJson(int samplingId) {
		logger.info("Class : RuleBuilderDAOImpl | Method : getSamplesList");

		String jsonString = null;
		Sampling sampling = new Sampling();

		SamplingTO samplingTo = null;
		try {
			session = sessionFactory.getCurrentSession();
			samplingTo = (SamplingTO) session.get(SamplingTO.class, samplingId);
			if (samplingTo != null) {
				String linkedData = samplingTo.getLinkedData().replace('|', ':');
				String nodeData = samplingTo.getNodeData().replace('|', ':');
				String segmentData = samplingTo.getSegmentData().replace('|', ':').replace('?', ':');

				ObjectMapper mapper = new ObjectMapper();
				List<Step> list = new ArrayList<Step>();
				List<SamplingNDTO> nodeDataTO = null;
				List<SamplingLDTO> linkDataTO = null;

				nodeDataTO = mapper.readValue(nodeData, new TypeReference<List<SamplingNDTO>>() {
				});
				linkDataTO = mapper.readValue(linkedData, new TypeReference<List<SamplingLDTO>>() {
				});

				sampling.setName(samplingTo.getSamplingName());
				List<Param> paramsList = new ArrayList<Param>();
				Param param = new Param();
				param.setName("SAMPLING_ID");
				param.setValue(samplingTo.getId() + "");
				paramsList.add(param);

				Param uniqueParam = new Param();
				uniqueParam.setName("SAMPLING_UNIQUE_ID");
				uniqueParam.setValue("012345" + samplingTo.getId());
				paramsList.add(uniqueParam);

				sampling.setParam(paramsList);

				for (SamplingNDTO to : nodeDataTO) {
					System.out.println("SamplingNDTO>>>>>" + to);
					Step step = new Step();
					step.setName(to.getKey() + "");
					step.setMethod(to.getXmlValue());
					step.setValue(to.getValue());
					step.setTile(to.getSplitValue());
					step.setOrderBy(to.getKpis());
					step.setSortType(to.getOrder());

					if (to.getSelectedProfilesList() != null) {
						String profile = "";
						for (int i = 0; i < to.getSelectedProfilesList().length; i++) {
							if (profile.equalsIgnoreCase(""))
								profile = to.getSelectedProfilesList()[i];
							else
								profile += "," + to.getSelectedProfilesList()[i];
						}
						step.setProfiles(profile);
					}
					step.setLogicalStep(getLogicalStep(linkDataTO, to.getKey()));
					list.add(step);
					sampling.setStep(list);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in getting Sampling List ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return sampling;
	}

	String getProfiles(String segmentData, int id) {
		String profiles = null;
		try {

			JsonFactory factory = new JsonFactory();
			ObjectMapper segmentMapper = new ObjectMapper(factory);
			JsonNode rootNode = segmentMapper.readTree(segmentData);

			JsonNode rootNode1 = segmentMapper.readTree(rootNode.get(id + "") + "");

			for (String split1 : rootNode1.toString().split(",")) {
				String val = split1.split(":")[1];
				if (profiles == null)
					profiles = val.substring(0, val.length() - 1);
				else
					profiles += "," + val.substring(0, val.length() - 1);
			}
		} catch (Exception e) {
			System.out.println("Exception in getting Sampling List ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return profiles.substring(0, profiles.length() - 1);
	}

	List<LogicalStep> getLogicalStep(List<SamplingLDTO> to, int id) {
		List<LogicalStep> list1 = new ArrayList<LogicalStep>();
		for (SamplingLDTO ldTo : to) {
			if (ldTo.getTo() == id) {
				LogicalStep logicalStep = new LogicalStep();
				logicalStep.setFrom(ldTo.getFrom());
				logicalStep.setInput(ldTo.getText().toLowerCase());

				list1.add(logicalStep);
			}
		}
		return list1;
	}

	@Override
	public List<StratSegProfilesTO> getStratSamplingProfiles() {

		logger.info("Class : SamplingDAOImpl | Method : getStratSamplingProfiles");

		Session session = null;
		List<StratSegProfilesTO> toList = null;
		List<StratSegProfilesBandsTO> toBandList = null;
		StratSegProfilesTO to = null;
		try {
			session = sessionFactory.getCurrentSession();
			String query = "SELECT s.ID as id, s.COLUMN_NAME as columnName, s.ENGLISH_NAME as engName, s.TYPE as type, g.NAME as name, s.OPTION_VALUES as options FROM RE_STRTFD_SAMP_PROF s, RE_GROUPS g WHERE s.GROUP_ID = g.ID";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("columnName", StringType.INSTANCE);
			sqlQuery.addScalar("engName", StringType.INSTANCE);
			sqlQuery.addScalar("type", IntegerType.INSTANCE);
			sqlQuery.addScalar("name", StringType.INSTANCE);
			sqlQuery.addScalar("options", StringType.INSTANCE);

			List<Object[]> results = (List<Object[]>) sqlQuery.list();

			query = "select ID,PROFILE_ID,BAND from RE_STRTFD_SAMP_BANDS";
			sqlQuery = session.createSQLQuery(query);
			List<Object[]> bands = (List<Object[]>) sqlQuery.list();
			toBandList = new ArrayList<StratSegProfilesBandsTO>();
			System.out.println("gggggggggggggggggggggggggggg11111" + bands.toString());
			for (Object[] band : bands) {
				StratSegProfilesBandsTO to1 = new StratSegProfilesBandsTO();
				
				String id = band[0].toString();
				int idValue = Integer.parseInt(id);
				
				String groupId = band[1].toString();
				int groupidValue = Integer.parseInt(id);
				
				to1.setId(idValue) ;
				to1.setGroupId(groupidValue);
				to1.setBand((String) band[2]);
				toBandList.add(to1);
			}

			if (results != null && results.size() > 0) {
				toList = new ArrayList<StratSegProfilesTO>();
				for (Object[] aRow : results) {
					to = new StratSegProfilesTO();
					to.setId((Integer) aRow[0]);
					to.setProfile((String) aRow[1]);
					to.setEnglishName((String) aRow[2]);
					to.setType((Integer) aRow[3]);
					to.setGroupName((String) aRow[4]);
					to.setOptValues(getBandsFromId(toBandList, to.getId()));
					toList.add(to);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toList;
	}

	@Override
	public List<String> getProfileOptions(String profile) {

		logger.info("Class : SamplingDAOImpl | Method : getStratSamplingProfiles");

		session = sessionFactory.getCurrentSession();

		String query = "select optValues from StratSegProfilesTO where profile = '" + profile + "'";

		String obj = (String) session.createQuery(query).uniqueResult();
		List<String> toList = new ArrayList<String>();

		if (!(obj == null)) {
			String[] options = obj.split(",");
			for (int i = 0; i < options.length; i++) {
				toList.add(options[i]);
			}
		}

		return toList;
	}

	/*
	 * Date convertion
	 */
	public String dateConvertion(String strDate) {
		String[] str = strDate.split(" ");
		return str[0];
	}

	@Override
	public List<SamplingTO> getSamplingList(SamplingTO samplingTO) {
		// TODO Auto-generated method stub
		logger.info("Class : SamplingDAOImpl | Method : getSamplingList");

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		criteria = session.createCriteria(SamplingTO.class);

		criteria.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
				.add(Projections.property("samplingName"), "samplingName")
				.add(Projections.property("createDate"), "createDate"))
				.setResultTransformer(Transformers.aliasToBean(SamplingTO.class));
		criteria.addOrder(Order.desc("id"));
		List<SamplingTO> samplingList = criteria.list();
		if (!samplingList.isEmpty()) {

			for (SamplingTO to : samplingList) {
				to.setStrCreateDate(simpleDateFormat.format(to.getCreateDate()));

			}

		}

		return samplingList;
	}

	@Override
	public Action getRuleActionJson(Action action) {

		action.setField(getHeaderParams(action.getActionId()));
		if (action.getSamplingId() != 0)
			action.setSampling(getSamplingJson(action.getSamplingId()));
//		if(action.getLeadPolicyIds()!=null && !action.getLeadPolicyIds().equalsIgnoreCase(""))
//			action.setLeadPolicyList(getLeadPolicyJson(action.getLeadPolicyIds()));

		Gson g = new Gson();
		System.out.println("Action Id : " + action.getActionId());
		System.out.println(" Action Json  :  " + g.toJson(action));

		return action;
	}

	@SuppressWarnings("unchecked")
	List<Field> getHeaderParams(int actionId) {

		logger.info("Class : SegmentsDAOImpl | Method : getGroupsInfo");
		List<ActionHeaderParameterTo> paramList = null;
		List<Field> list = new ArrayList<Field>();
		String json = "";
		JSONArray jsonObj = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "FROM ActionHeaderParametersTO WHERE actionId=" + actionId;
			System.out.println("SQL Query : " + query);
			paramList = (List<ActionHeaderParameterTo>) session.createQuery(query).list();

			for (ActionHeaderParameterTo to : paramList) {
				Field field = new Field();
				field.setName(to.getParamName());
				field.setValue(to.getParamValue());
				list.add(field);
			}

			// Array of Array
			/*
			 * json="{\"fileds\": ["; int i=0; for(ActionHeaderParametersTO to:paramList){
			 * if(i!=0){ json+=","; }
			 * 
			 * i++; json+= "{\"field\":{ \"name\": \""; json+= to.getParamName(); json+=
			 * "\", \"value\": \""; json+= to.getParamValue(); json+= "\" }}";
			 * 
			 * } json+="]}"; System.out.println("String Json : "+json); jsonObj = new
			 * JSON(json); System.out.println("Generated Json : "+jsonObj);
			 */

		} catch (Exception e) {
			System.out.println("Exception in get groups info list ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	String getBandsFromId(List<StratSegProfilesBandsTO> list, int bandId) {
		String bands = "";

		for (StratSegProfilesBandsTO to : list) {
			if (to.getGroupId() == bandId) {
				if (bands == "")
					bands = to.getBand();
				else
					bands += "," + to.getBand();

			}
		}

		return bands;

	}

//	List<LeadPolicy> getLeadPolicyJson(String ids){
//		List<Param> leadPolicies=null;
//		
//		List<LeadPolicy> leadPolicyList=new ArrayList<LeadPolicy>();
//		
//		
//		session = sessionFactory.getCurrentSession();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		for(String split:ids.split(",")){
//			int id=Integer.parseInt(split);
//			System.out.println("ID : "+id);
//			LeadPolicyTO leadPolicyTO=(LeadPolicyTO)session.get(LeadPolicyTO.class, id);
//			if(leadPolicyTO!=null){
//				LeadPolicy leadPolicy=new LeadPolicy();
//				leadPolicies=new ArrayList<Param>();
//				
//				Param leadPilicyId=new Param();
//				leadPilicyId.setName("LeadPolicyId");
//				leadPilicyId.setValue(leadPolicyTO.getId()+"");
//				leadPolicies.add(leadPilicyId);
//				
//				Param leadPilicyName=new Param();
//				leadPilicyName.setName("LeadPolicyName");
//				leadPilicyName.setValue(leadPolicyTO.getPolicyName());
//				leadPolicies.add(leadPilicyName);
//				
//				Param startTime=new Param();
//				startTime.setName("StartTime");
//				startTime.setValue(dateFormat.format(leadPolicyTO.getStartDate()));
//				leadPolicies.add(startTime);
//				
//				Param endTime=new Param();
//				endTime.setName("EndTime");
//				endTime.setValue(dateFormat.format(leadPolicyTO.getEndDate()));
//				leadPolicies.add(endTime);
//				
//				Param recency=new Param();
//				recency.setName("Recency");
//				recency.setValue(leadPolicyTO.getExpiryDays());
//				leadPolicies.add(recency);
//				
//				//Creating Marketing Plan Exclude Json
//				if(leadPolicyTO.getMarketingPlan()!=null && !leadPolicyTO.getMarketingPlan().trim().equalsIgnoreCase("")){
//					Exclude exclude=new Exclude();
//					if(leadPolicyTO.getMarketingPlan().equalsIgnoreCase("all") ){
//						Param all=new Param();	
//						all.setName("ServiceDetails");
//						all.setValue("ALL");
//						exclude.setParam(all);
//					}
//					else{
//							Param param=new Param();
//							param.setName("ServiceDetails");
//							List<MultiParam>multiParamList=new ArrayList<MultiParam>();
//							for(String planId:leadPolicyTO.getMarketingPlan().split(",")){
//								MultiParam multiParam=new MultiParam();
//								Param param2=new Param();
//									param2.setName("ServiceId");
//									param2.setValue(planId);
//								multiParam.setParam(param2);
//								multiParamList.add(multiParam);
//							}
//							param.setMultiParam(multiParamList);
//							exclude.setParam(param);
//					}
//					leadPolicy.setExclude(exclude);
//				}
//				
//				
//				//Creating Action Key Exclude Json
//				else if(leadPolicyTO.getActionKey()!=null  && !leadPolicyTO.getActionKey().trim().equalsIgnoreCase("")){
//					Exclude exclude=new Exclude();
//					if(leadPolicyTO.getActionKey().equalsIgnoreCase("all")){
//						Param all=new Param();	
//						all.setName("ActionKeys");
//						all.setValue("ALL");
//						exclude.setParam(all);
//					}
//					else{
//							Param param=new Param();
//							param.setName("ActionKeys");
//							List<MultiParam>multiParamList=new ArrayList<MultiParam>();
//							for(String planId:leadPolicyTO.getActionKey().split(",")){
//								MultiParam multiParam=new MultiParam();
//								Param param2=new Param();
//									param2.setName("ActionKey");
//									param2.setValue(planId);
//								multiParam.setParam(param2);
//								multiParamList.add(multiParam);
//							}
//							param.setMultiParam(multiParamList);
//							exclude.setParam(param);
//					}
//					leadPolicy.setExclude(exclude);
//				}
//				
//				
//				leadPolicy.setParams(leadPolicies);
//				leadPolicyList.add(leadPolicy);
//				
//			}
//			Gson g =new Gson();
//			System.out.println("Final for Lead Policy Json  :  "+g.toJson(leadPolicyList));
//		}
//		return leadPolicyList;
//	}
//	
//	

}
