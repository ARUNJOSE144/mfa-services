package com.sixdee.magik.services.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sixdee.magik.services.model.ActionFileTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RemoteCopyUserTO;
import com.sixdee.magik.services.model.RuleBuilderTO;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.model.ScheduleTO;
import com.sixdee.magik.services.model.TokenMaster;
import com.sixdee.magik.services.service.RuleBuilderService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@RestController
public class RuleBuilderRestControllar {

	static final Logger logger = Logger.getLogger(RuleBuilderRestControllar.class);

	@Autowired
	RuleBuilderService ruleService;

	@PostMapping(MagikServicePath.SAVE_RULE)
	public @ResponseBody RestInfo<String> saveRule(HttpServletRequest httpServletRequest,
			@RequestBody RuleBuilderTO ruleTo) {

		logger.info("================== Rule Rest Controller  API Start =====================");
		logger.info("Class : RuleRestController | Method : saveRule");

		System.out.println("in save rule++++++++++++++++++++++++++++++++++++++");
		RestInfo<String> info = new RestInfo<String>();
		try {
			System.out.println("in try>>>" + ruleTo);
			ruleService.saveRule(ruleTo);
			info.setOperationCode(0);
		} catch (DataIntegrityViolationException e) {
			System.out.println("in catch rule");
			info.setOperationCode(2);
			info.setMessage("Rule " + ruleTo.getName() + " Already Exists...");
			// ExceptionUtil.handle(e, info);
			logger.error("Class : EventRestController  |  Method : saveEvent " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("in catch rule");
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : EventRestController  |  Method : saveEvent " + e);
			e.printStackTrace();
		}

		logger.info("================== Event Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.SAVE_RULE_FROM_CAMPAIGN)
	public @ResponseBody RestInfo<RuleBuilderTO> saveRuleCampaign(HttpServletRequest httpServletRequest,
			@RequestBody RuleBuilderTO ruleTo) {

		logger.info("================== Rule Rest Controller  API Start =====================");
		logger.info("Class : RuleRestController | Method : saveRule");
		System.out.println("in save rule###################");
		RestInfo<RuleBuilderTO> info = new RestInfo<RuleBuilderTO>();
		try {
			System.out.println("in try>>>" + ruleTo);
			info.setData(ruleService.saveRule(ruleTo));
			info.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : EventRestController  |  Method : saveEvent " + e);
			e.printStackTrace();
		}

		logger.info("================== Event Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.LOAD_SCDHEDULE_DATA)
	public @ResponseBody RestInfo<ScheduleTO> loadScheduleData(HttpServletRequest httpServletRequest) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : loadScheduleData");
		RestInfo<ScheduleTO> info = new RestInfo<ScheduleTO>();
		int userId = 1;
		if (httpServletRequest.getParameter("userId") != null
				&& !httpServletRequest.getParameter("userId").equalsIgnoreCase("")) {
			userId = (Integer.parseInt(httpServletRequest.getParameter("userId")));
			System.out.println("userId  :  " + userId);
		}
		try {
			info.setData(ruleService.loadScheduleData(userId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : loadScheduleData " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_RULES)
	public @ResponseBody RestListInfo<RuleTO> getRules(HttpServletRequest httpServletRequest) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : getRules");
		RestListInfo<RuleTO> info = new RestListInfo<RuleTO>();
		int type = 0;
		int campaignId = 0;
		int userId = 1;
		if (httpServletRequest.getParameter("type") != null
				&& !httpServletRequest.getParameter("type").equalsIgnoreCase("")) {
			type = (Integer.parseInt(httpServletRequest.getParameter("type")));
		}
		if (httpServletRequest.getParameter("userId") != null
				&& !httpServletRequest.getParameter("userId").equalsIgnoreCase("")) {
			userId = (Integer.parseInt(httpServletRequest.getParameter("userId")));
			System.out.println("userId  :  " + userId);
		}

		System.out.println("Campaign id>>>>>>>" + httpServletRequest.getParameter("campaignId"));
		if (httpServletRequest.getParameter("campaignId") != null
				&& !httpServletRequest.getParameter("campaignId").equalsIgnoreCase("")
				&& !httpServletRequest.getParameter("campaignId").equalsIgnoreCase("undefined")) {
			campaignId = (Integer.parseInt(httpServletRequest.getParameter("campaignId")));
		}
		// campaignId
		// =(Integer.parseInt(httpServletRequest.getParameter("campaignId")));
		try {
			info.setData(ruleService.getAllRules(type, campaignId, userId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : getRules " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.SCDHEDULE_RULE)
	public @ResponseBody RestInfo<String> scheduleRule(HttpServletRequest httpServletRequest,
			@RequestBody RuleTO ruleTO) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : scheduleRule");
		System.out.println("ruleTO  :::: " + ruleTO.toString());
		RestInfo<String> info = new RestInfo<String>();
		try {
			String Status = ruleService.scheduleRule(ruleTO);

			if (Status.equals("0")) {
				info.setOperationCode(0);
				info.setMessage("Rule Saved Successfully");
			} else {
				info.setOperationCode(1);
				info.setMessage("Application Process Error");
			}
			// info.setData(ruleService.scheduleRule(ruleTO));
		} catch (DataIntegrityViolationException e) {
			System.out.println("in catch rule");
			info.setOperationCode(2);
			info.setMessage("Rule " + ruleTO.getRuleName() + " Already Exists...");
			// ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : scheduleRule " + e);
			e.printStackTrace();
		} catch (Exception e) {
			info.setOperationCode(1);
			System.out.println("in catch rule  ::  2");
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : scheduleRule " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.GET_RULE_JSON)
	public @ResponseBody RestInfo<String> getRuleJson(HttpServletRequest httpServletRequest,
			@RequestBody RuleTO ruleTO) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : getRuleJson");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(ruleService.getRuleJson(ruleTO));
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : getRuleJson " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.DELETE_RULE)
	public @ResponseBody RestInfo<String> deleteRule(HttpServletRequest httpServletRequest,
			@RequestBody RuleTO configTO) {
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(ruleService.deleteRule(configTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@PostMapping(MagikServicePath.SAVE_ATTACHED_RULE)
	public @ResponseBody RestInfo<RuleBuilderTO> saveAttachedRule(HttpServletRequest httpServletRequest,
			@RequestBody RuleBuilderTO ruleTo) {

		logger.info("================== Rule Rest Controller  API Start =====================");
		logger.info("Class : RuleRestController | Method : saveAttachedRule");
		System.out.println("================== Rule Rest Controller  API Start =====================");
		System.out.println("Class : RuleRestController | Method : saveAttachedRule" + ruleTo);

		System.out.println("In save attached rule:::::::::::::::::::::::::::::");
		RestInfo<RuleBuilderTO> info = new RestInfo<RuleBuilderTO>();
		try {
			System.out.println("in try>>>" + ruleTo);
			info.setData(ruleService.saveAttachedRule(ruleTo));
			info.setOperationCode(0);
		} catch (DataIntegrityViolationException e) {
			System.out.println("in catch Dupicate rule Name");
			info.setOperationCode(2);
			info.setMessage("Rule " + ruleTo.getName() + " Already Exists...");
			// ExceptionUtil.handle(e, info);
			logger.error("Class : RuleRestController  |  Method : saveAttachedRule " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("in catch rule");
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class :RuleRestController  |  Method : saveAttachedRule " + e);
			e.printStackTrace();
		}

		logger.info("================== Rule Rest Controllar API End =====================");
		System.out.println("================== Rule Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_SCHEDULE_DETAILS_OF_RULE)
	public @ResponseBody RestInfo<ScheduleTO> loadScheduleDetailsOfRule(HttpServletRequest httpServletRequest) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : loadScheduleDetailsOfRule");
		RestInfo<ScheduleTO> info = new RestInfo<ScheduleTO>();
		int ruleId = 0;
		if (httpServletRequest.getParameter("ruleId") != null
				&& !httpServletRequest.getParameter("ruleId").trim().equalsIgnoreCase(""))
			ruleId = Integer.parseInt(httpServletRequest.getParameter("ruleId"));

		System.out.println(httpServletRequest.getParameter("ruleId") + "Rule id in Controller--------->" + ruleId);
		try {
			if (ruleId != 0) {
				info.setData(ruleService.loadScheduleDataOfRule(ruleId));
				info.setOperationCode(0);
			}

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : loadScheduleDetailsOfRule " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.UPDATE_RULE_STATUS)
	public @ResponseBody RestInfo<String> updateuleStatus(HttpServletRequest httpServletRequest) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : stopRule");
		RestInfo<String> info = new RestInfo<String>();
		int ruleId = 0;
		Integer status = 0;
		if (httpServletRequest.getParameter("ruleId") != null
				&& !httpServletRequest.getParameter("ruleId").trim().equalsIgnoreCase(""))
			ruleId = Integer.parseInt(httpServletRequest.getParameter("ruleId"));
		if (httpServletRequest.getParameter("status") != null
				&& !httpServletRequest.getParameter("status").trim().equalsIgnoreCase(""))
			;
		// System.out.println("Status111:::"+ httpServletRequest.getParameter("status")
		// );
		status = Integer.parseInt(httpServletRequest.getParameter("status"));

		System.out.println(ruleId + "------" + status);
		try {
			// if (ruleId != 0 && status != 0) {
			if (ruleId != 0) {
				if (ruleService.updateRuleStatus(ruleId, status))
					info.setOperationCode(0);
				info.setData(status.toString());
			} else {
				info.setOperationCode(1);
			}

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : stopRule " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.FETCH_RULE_STATUS)
	public @ResponseBody RestInfo<RuleBuilderTO> fetchRuleStatus(HttpServletRequest httpServletRequest) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : stopRule");
		RestInfo<RuleBuilderTO> info = new RestInfo<RuleBuilderTO>();
		int ruleId = 0;
		Integer status = 0;
		RuleBuilderTO builderTO = null;
		System.out.println("ruleId::::" + httpServletRequest.getParameter("ruleId"));
		if (httpServletRequest.getParameter("ruleId") != null
				&& !httpServletRequest.getParameter("ruleId").trim().equalsIgnoreCase(""))
			ruleId = Integer.parseInt(httpServletRequest.getParameter("ruleId"));
		System.out.println("ruleId11::::" + ruleId);
		try {
			if (ruleId != 0) {
				info.setData(ruleService.fetchRuleStatus(ruleId));
			}

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : stopRule " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

//	@PostMapping(MagikServicePath.UPDATE_RULE_STATUS)
//	public @ResponseBody
//	RestInfo<String> getRuleStatus(HttpServletRequest httpServletRequest, @RequestBody RuleTO ruleSchedulerTO) {
//		RestInfo<String> info = new RestInfo<String>();
//		logger.info("================== Rule Builde get Event Information API Start =====================");
//		logger.info("Class : AnalyticsRestController | Method : scheduleRule");
//		System.out.println("Id :"+ruleSchedulerTO.getRuleId());
//		System.out.println("Play  Pause Json :"+ruleSchedulerTO.getPlayPauseJson());
//		//RestInfo<GenericTO> info = new RestInfo<GenericTO>();
//		
//		try {
//			//info.setData(ruleBuilderService.getRuleStatus(ruleSchedulerTO));
//			//info.setMessage(CacheDAOImpl.messages.get(23));
//		} catch (Exception e) {
//			System.out.println("Rest contloer >>>>>>>>>>>>> Exception on Rule Status Json Processing to BL side....");
//			//ExceptionUtil.handle(e, info);
//			logger.error("Class : RuleBuilderRestController  |  Method : scheduleRule \n" + e);
//			e.printStackTrace();
//		}
//		logger.info("================== Rule Builde get Event Information API End =====================");
//		
//		return info;
//	}

	@PostMapping(MagikServicePath.GET_RULE_AUDIT_INFO)
	public @ResponseBody RestListInfo<RuleTO> getRuleAuditInfo(HttpServletRequest httpServletRequest,
			@RequestBody RuleTO ruleTO) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : getRuleAuditInfo");
		RestListInfo<RuleTO> info = new RestListInfo<RuleTO>();
		try {
			info.setData(ruleService.getRuleAuditInfo(ruleTO));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : getRuleAuditInfo " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.APPROVE_OR_REJECT_RULE)
	public @ResponseBody RestInfo<String> approveOrRejectRule(HttpServletRequest httpServletRequest,
			@RequestBody RuleTO ruleTO) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : approveOrRejectRule");
		RestInfo<String> info = new RestInfo<String>();
		try {
			String status = ruleService.approveOrRejectRule(ruleTO);
			if (status != null && status.trim().equalsIgnoreCase("0"))
				info.setOperationCode(0);
			else
				info.setOperationCode(2);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : approveOrRejectRule " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_RULES_FOR_APPROVAL)
	public @ResponseBody RestListInfo<RuleTO> getRulesForApproval(
			HttpServletRequest httpServletRequest/* ,@RequestBody RuleTO ruleTO */) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : getRulesForApproval");

		String userId = httpServletRequest.getParameter("userId");
		RestListInfo<RuleTO> info = new RestListInfo<RuleTO>();
		RuleTO ruleTO = new RuleTO();
		try {
			info.setData(ruleService.getRulesForApproval(ruleTO, userId));
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : getRulesForApproval " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.DOWNLOAD_FILE)
	public void doPerform(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, JSchException, SftpException {
		System.out.println("Inside Download ::: ");
		InputStream fis = null;
		OutputStream outex = res.getOutputStream();
		String filename = null;
		BufferedReader bufferedReader = null;
		int port = 22;
		String serverId = req.getParameter("serverName");
		String file = req.getParameter("fileName");
		// String file = "/opt/magik/Magik_Revamp/RnD/Downloads/Test.txt";
		String nodeName = req.getParameter("nodeName");
		InputStream out = null;

		System.out.println("File :: " + file);
		String host = req.getParameter("ip");
		String username = req.getParameter("userName");
		String password = req.getParameter("password");

//		String host = "10.0.0.95";
//		String username = "cmsuser";
//		String password = "cmsuser";

		System.out.println("Host Name ::  " + host);
		System.out.println("username  :: " + username);
		System.out.println("password :: " + password);

		if (host != null && file != null) {
			if (username != null && password != null) {

				JSch jsch = new JSch();
				Session session = jsch.getSession(username, host, port);
				session.setPassword(password);
				session.setConfig("StrictHostKeyChecking", "no");
				System.out.println("Password ::: " + password + "Host ::: " + host + "Port :: " + port + "UserName :: "
						+ username);
				System.out.println("Establishing Connection :: ");
				session.connect();
				System.out.println("Connection established :::  ");
				System.out.println("Crating SFTP Channel :: .");
				ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");

				sftpChannel.connect();
				System.out.println("SFTP Channel created ::: ");
				filename = file.trim();
				System.out.println("File Name :: " + filename);
				out = sftpChannel.get(filename);

			}

			filename = file.trim();

			String fname = filename.substring(filename.lastIndexOf("/") + 1);

			res.setContentType("application/x-download");
			res.setHeader("Content-Disposition", "attachment;filename=" + fname);

			System.out.println(
					"FILE NAME IN DwnldServlet :: :: " + filename + "  IP :" + host + "  Username : " + username);
			if (filename.indexOf(username) != -1) {
				filename = filename.substring(filename.indexOf("/", filename.lastIndexOf(username)), filename.length());
			}

			// sb.append(Cache.systemPropertyMap.get("RELATIVE_PATH"));
			// sb.append(filename);
			// sb.append(";type=i");
			// log.info("SB SB == "+sb.toString());

			URL url = null;
			URLConnection urlc = null;

			try {

				// url = new URL(sb.toString());
				// urlc = url.openConnection();

				// fis = new BufferedInputStream(urlc.getInputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(out));

				String line;
				String newLine = "\r\n";
				byte[] newLineBytes = newLine.getBytes();
				while ((line = bufferedReader.readLine()) != null) {
					outex.write(line.getBytes(), 0, line.getBytes().length);
					outex.write(newLineBytes);

				}

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
				urlc = null;
				url = null;
			}
		} else {
		}
	}

	@GetMapping(MagikServicePath.GET_FILE_DETAILS)
	public @ResponseBody RestListInfo<ActionFileTO> getFileDetails(HttpServletRequest httpServletRequest) {

		String actionid = httpServletRequest.getParameter("actionId");
		String ruleId = httpServletRequest.getParameter("scheduleId");
		RestListInfo<ActionFileTO> listInfo = new RestListInfo<ActionFileTO>();
		try {
			listInfo.setData(ruleService.getFileDetails(actionid, ruleId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_SESSISON_NAME)
	public @ResponseBody RestListInfo<TokenMaster> getSessionName(HttpServletRequest httpServletRequest) {

		RestListInfo<TokenMaster> listInfo = new RestListInfo<TokenMaster>();
		try {
			listInfo.setData(ruleService.getSessionName());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.REMOTE_COPY)
	public @ResponseBody RestInfo<String> remoteCopy(HttpServletRequest httpServletRequest,
			@RequestBody RemoteCopyUserTO copyTO) {
		System.out.println(" copyTO ::::  " + copyTO);
		RestInfo<String> info = new RestInfo<String>();
		HttpSession session = httpServletRequest.getSession();
		System.out.println("copyTO  ::  " + copyTO.toString());
		String status = "0";
		try {
			System.out.println("Inside Try");
			status = ruleService.remoteCopy(copyTO);
			System.out.println("status  " + status);
			if (status == "0") {
				info.setMessage("Saved Successfully");
				info.setOperationCode(0);
			} else {
				info.setMessage("failure occured ");
				info.setOperationCode(1);
			}

		} catch (

		Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage("Saving Failed ");
			info.setOperationCode(1);
			System.out.println("catch  ::: ");
			logger.error("Class : QuarantineRestController  |  Method : saveGlobalFilter \n" + e);
			e.printStackTrace();
		}

		logger.info(
				"==================  QuarantineRevamp RestController  | Method : saveGlobalFilter API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.REMOTE_PASTE)
	public @ResponseBody RestListInfo<RemoteCopyUserTO> remotePaste(HttpServletRequest httpServletRequest) {
		String sessionVal1 = httpServletRequest.getSession().getId();
		System.out.println("sessionVal1  1212   " + sessionVal1.toString());
		String token = httpServletRequest.getParameter("tokenId");
		RestListInfo<RemoteCopyUserTO> info = new RestListInfo<RemoteCopyUserTO>();
		try {
			info.setData(ruleService.remotePaste(token));
			info.setOperationCode(0);

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping(MagikServicePath.GET_PASTE_NAME)
	public @ResponseBody RestInfo<RemoteCopyUserTO> getPasteName(HttpServletRequest httpServletRequest) {

		String sessionVal1 = httpServletRequest.getSession().getId();
		System.out.println("sessionVal1  paste  " + sessionVal1.toString());
		String token = httpServletRequest.getParameter("tokenId");

		RestInfo<RemoteCopyUserTO> listInfo = new RestInfo<RemoteCopyUserTO>();
		try {
			listInfo.setData(ruleService.getPasteName(token));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.GET_RULES_WITH_PAGINATION)
	public @ResponseBody RestInfo<PaginationDTO<RuleTO>> getRulesWithPagination(HttpServletRequest httpServletRequest,
			@RequestBody PaginationDTO<RuleTO> paginationReqTo) {

		logger.info("================== RuleBuilder Rest Controllar API Start =====================");
		logger.info("Class : RuleBuilderRestControllar | Method : getRules");
		RestInfo<PaginationDTO<RuleTO>> info = new RestInfo<PaginationDTO<RuleTO>>();
		try {
			logger.info("Class : RuleBuilderRestControllar | Method : getRules inside try");
			info.setData(ruleService.getRulesWithPagination(paginationReqTo));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : RuleBuilderRestControllar  |  Method : getRules " + e);
			e.printStackTrace();
		}
		logger.info("================== RuleBuilder Rest Controllar API End =====================");
		return info;
	}

}
