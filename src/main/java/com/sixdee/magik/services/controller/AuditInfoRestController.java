package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.UrlConfigTO;
import com.sixdee.magik.services.service.AuditInfoService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Nakhil Kurian
 * @Date : March, 2020
 *
 */

@RestController
public class AuditInfoRestController {

	static final Logger logger = Logger.getLogger(AuditInfoRestController.class);

	@Autowired
	AuditInfoService auditInfoService;

	@PostMapping(MagikServicePath.GET_AUDIT_INFO)
	public @ResponseBody RestListInfo<AuditInfoTO> getAuditInfo(HttpServletRequest httpServletRequest,
			@RequestBody AuditInfoTO auditInfoTO) {

		logger.info("==================  AuditInfoRestController Controllar API Start =====================");
		logger.info("Class : AuditInfoRestController | Method : getAuditInfo");
		RestListInfo<AuditInfoTO> info = new RestListInfo<AuditInfoTO>();
		try {
			info.setData(auditInfoService.getAuditInfo(auditInfoTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuditInfoRestController  |  Method : getAuditInfo " + e);
			e.printStackTrace();
		}
		logger.info("================== AuditInfoRestController Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_AUDIT_DEFAULT_INFO)
	public @ResponseBody RestListInfo<AuditInfoTO> viewDefaultAudit(HttpServletRequest httpServletRequest) {

		logger.info("================== AuditInfoRestController API Start =====================");
		logger.info("Class : AuditInfoRestController | Method : viewDefaultAudit");

		RestListInfo<AuditInfoTO> info = new RestListInfo<AuditInfoTO>();

		try {
			info.setOperationCode(0);
			info.setMessage("view DefaultAudit successfuly.");
			info.setData(auditInfoService.viewDefaultAudit());
		} catch (Exception e) {
			info.setOperationCode(1);
			info.setMessage("  view viewDefaultAudit process Failed.");
			ExceptionUtil.handle(e, info);
			logger.error("Class : viewDefaultAudit  |  Method : viewDefaultAudit " + e);
			e.printStackTrace();
		}

		logger.info("================== viewDefaultAudit API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.SAVE_RULE_AUDIT)
	public @ResponseBody RestInfo<String> saveRuleAudit(HttpServletRequest httpServletRequest,
			@RequestBody AuditInfoTO auditTO) {
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(auditInfoService.saveRuleAudit(auditTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

}
