/**
 * 
 */
package com.sixdee.magik.services.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.Action;
import com.sixdee.magik.services.model.Sampling;
import com.sixdee.magik.services.model.SamplingTO;
import com.sixdee.magik.services.model.StratSegProfilesTO;
import com.sixdee.magik.services.model.UserSessionTO;
import com.sixdee.magik.services.service.SamplingService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Vinay Kariyappa
 *
 *         Dec 12, 2018
 */
@RestController
public class SamplingRestController {

	static final Logger logger = Logger.getLogger(SamplingRestController.class);

	@Autowired
	SamplingService samplingService;

	@GetMapping("GetSamplingTypes")
	public @ResponseBody RestInfo<String> getSamplingTypes(HttpServletRequest httpServletRequest) {
		logger.info("================== SamplingRestController getSamplesList API Start =====================");
		return samplingService.getSamplingNodes();
	}

	@PostMapping("CreateSampling")
	public @ResponseBody RestInfo<String> saveSampling(HttpServletRequest httpServletRequest,
			@RequestBody SamplingTO samplingTO) throws UnsupportedEncodingException {
		logger.info("================== SamplingRestController getSamplesList API Start =====================");

		RestInfo<String> info = new RestInfo<String>();

		try {
			samplingTO.setNodeData(java.net.URLDecoder.decode(samplingTO.getNodeData(), "UTF-8"));
			samplingTO.setLinkedData(java.net.URLDecoder.decode(samplingTO.getLinkedData(), "UTF-8"));
			samplingTO.setSegmentData(java.net.URLDecoder.decode(samplingTO.getSegmentData(), "UTF-8"));
			samplingTO.setStratifiedData(java.net.URLDecoder.decode(samplingTO.getStratifiedData(), "UTF-8"));
		} catch (Exception e) {

		}
		samplingTO.setNodeData(samplingTO.getNodeData().replaceAll(":", "|"));
		if (samplingTO.getLinkedData() != null)
			samplingTO.setLinkedData(samplingTO.getLinkedData().replaceAll(":", "|"));
		else
			samplingTO.setLinkedData("[]");

		if (samplingTO.getSegmentData() != null)
			samplingTO.setSegmentData(samplingTO.getSegmentData().replaceAll(":", "|"));
		else
			samplingTO.setSegmentData("[]");

		String flag = null;
		if (samplingTO.getId() != 0)
			flag = "UPDATE";
		else
			flag = "SAVE";

		try {
			samplingService.saveSampling(samplingTO, flag);
			if (flag.equalsIgnoreCase("SAVE"))
				info.setMessage("Sampling Saved Succesfully....");
			else
				info.setMessage("Sampling Modified Successfully...");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SamplingRestController  |  Method : saveSampling \n" + e);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping("GetStratifiedSamplingProfiles")
	public @ResponseBody RestListInfo<StratSegProfilesTO> getStratSamplingProfiles(
			HttpServletRequest httpServletRequest) {
		logger.info(
				"================== SamplingRestController getStratSamplingProfiles API Start =====================");

		RestListInfo<StratSegProfilesTO> info = new RestListInfo<StratSegProfilesTO>();
		try {
			info.setOperationCode(0);
			info.setData(samplingService.getStratSamplingProfiles());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e);
		}

		logger.info("================== Rule Builde get getStratSamplingProfiles API End =====================");

		return info;
	}

	@GetMapping("GetProfileOptions")
	public @ResponseBody RestListInfo<String> getProfileOptions(HttpServletRequest httpServletRequest) {
		logger.info("================== SamplingRestController getProfileOptions API Start =====================");
		String profile = httpServletRequest.getParameter("profile");
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			info.setOperationCode(0);
			info.setData(samplingService.getProfileOptions(profile));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e);
		}

		logger.info("================== Rule Builde get getProfileOptions API End =====================");

		return info;
	}

	@GetMapping("getSamplingJson")
	public @ResponseBody RestInfo<Sampling> getSamplingJson(HttpServletRequest httpServletRequest) {

		logger.info("================== Rule Builde get Event Information API Start =====================");
		logger.info("Class : AnalyticsRestController | Method : getActionListNew");
		int id = Integer.parseInt(httpServletRequest.getParameter("samplingId"));
		RestInfo<Sampling> info = new RestInfo<Sampling>();
		try {
			info.setOperationCode(0);
			info.setMessage("Success");
			info.setData(samplingService.getSamplingJson(id));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e);
		}

		logger.info("================== Rule Builde get Event Information API End =====================");

		return info;
	}

	@GetMapping("GetSamplesList")
	public @ResponseBody RestListInfo<SamplingTO> getSamplesList(HttpServletRequest httpServletRequest) {
		logger.info("================== SamplingRestController getSamplesList API Start =====================");

		String userId = httpServletRequest.getParameter("userId");
		RestListInfo<SamplingTO> info = new RestListInfo<SamplingTO>();
		UserSessionTO user = new UserSessionTO();
		user.setUserId(Integer.parseInt(userId));
		try {
			info.setData(samplingService.getSamplesList(user));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SamplingRestController  |  Method : getSamplesList \n" + e);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping("GetSamplingDetails")
	public @ResponseBody RestInfo<String> getSamplingDetails(HttpServletRequest httpServletRequest) {
		logger.info("================== SamplingRestController getSamplingDetails API Start =====================");
		int sampleId = Integer.parseInt(httpServletRequest.getParameter("samplingId"));
		return samplingService.getSamplingDetails(sampleId);
	}

	@GetMapping("DeleteSampling")
	public @ResponseBody RestInfo<SamplingTO> deleteSampling(HttpServletRequest httpServletRequest) {
		logger.info("================== SamplingRestController deleteSampling API Start =====================");
		RestInfo<SamplingTO> info = new RestInfo<SamplingTO>();
		int id = Integer.parseInt(httpServletRequest.getParameter("samplingId"));
		try {
			samplingService.deleteSampling(id);
			info.setMessage("Sampling Deleted Successfully");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SamplingRestController  |  Method : deleteSampling \n" + e);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping(MagikServicePath.GET_SAMPLING_LIST)
	public @ResponseBody RestListInfo<SamplingTO> getSamplingList(HttpServletRequest httpServletRequest) {
		logger.info("================== SamplingRestController getSamplingList API Start =====================");
		RestListInfo<SamplingTO> info = new RestListInfo<SamplingTO>();
		SamplingTO samplingTO = new SamplingTO();
		if (httpServletRequest.getParameter("userId") != null
				&& !httpServletRequest.getParameter("userId").trim().equalsIgnoreCase(""))
			samplingTO.setUserId(Integer.parseInt(httpServletRequest.getParameter("userId")));
		if (httpServletRequest.getParameter("name") != null
				&& !httpServletRequest.getParameter("name").trim().equalsIgnoreCase(""))
			samplingTO.setSamplingName(httpServletRequest.getParameter("name"));
		try {
			info.setData(samplingService.getSamplingList(samplingTO));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : SamplingRestController  |  Method : getSamplingList \n" + e);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping("gerRuleActionJson")
	public @ResponseBody RestInfo<Action> getRuleActionJson(HttpServletRequest httpServletRequest) {

		Action action = new Action();

		action.setActionId(Integer.parseInt(httpServletRequest.getParameter("actionId")));
		if (!httpServletRequest.getParameter("samplingId").equals("null")
				&& httpServletRequest.getParameter("samplingId") != null
				&& !httpServletRequest.getParameter("samplingId").equalsIgnoreCase("")
				&& !httpServletRequest.getParameter("samplingId").equalsIgnoreCase("-1"))
			action.setSamplingId(Integer.parseInt(httpServletRequest.getParameter("samplingId")));
		if (!httpServletRequest.getParameter("leadPolicyIds").equals("null")
				&& httpServletRequest.getParameter("leadPolicyIds") != null
				&& !httpServletRequest.getParameter("leadPolicyIds").equalsIgnoreCase(""))
			action.setLeadPolicyIds(httpServletRequest.getParameter("leadPolicyIds"));

		RestInfo<Action> info = new RestInfo<Action>();
		try {
			info.setOperationCode(0);
			info.setMessage("Success");
			info.setData(samplingService.getRuleActionJson(action));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e);
		}

		logger.info("================== Rule Builde get Event Information API End =====================");

		return info;
	}
}
