package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.dao.impl.CacheDaoImpl;
import com.sixdee.magik.services.model.BLGroupDetailsDTO;
import com.sixdee.magik.services.model.DataTypeTO;
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.service.KPIService;
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
public class KPIRestControllar {

	static final Logger logger = Logger.getLogger(KPIRestControllar.class);

	@Autowired
	KPIService kpiService;

	/*
	 * Add KPI
	 */
	@GetMapping(MagikServicePath.ADD_KPI)
	public @ResponseBody RestInfo<GenericTO> addKPI(HttpServletRequest httpServletRequest, @RequestBody KPITO to) {

		logger.info("================== KPI Rest Controllar API Start =====================");
		logger.info("Class : KPIRestControllar | Method : addKPI");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();

		try {
			info.setData(kpiService.addKPI(to));
			info.setMessage(to.getName()+" KPI "+CacheDaoImpl.messages.get(8));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : KPIRestControllar  |  Method : addKPI " + e);
			e.printStackTrace();
		}

		logger.info("================== KPI Rest Controllar API End =====================");

		return info;
	}

	/*
	 * Get KPI's
	 */
	@GetMapping(MagikServicePath.GET_KPIS)
	public @ResponseBody RestListInfo<KPITO> getKPIS(HttpServletRequest httpServletRequest) {

		logger.info("================== KPI Rest Controllar API Start =====================");
		logger.info("Class : KPIRestControllar | Method : getKPIS");

		RestListInfo<KPITO> info = new RestListInfo<KPITO>();

		try {
			info.setData(kpiService.getKPIS());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : KPIRestControllar  |  Method : getKPIS " + e);
			e.printStackTrace();
		}

		logger.info("================== KPI Rest Controllar API End =====================");

		return info;
	}

	/*
	 * Authenticate Credentials
	 */
	@GetMapping(MagikServicePath.GRT_DATA_TYPES)
	public @ResponseBody RestListInfo<DataTypeTO> getDataTypes(HttpServletRequest httpServletRequest) {

		logger.info("================== KPI Rest Controllar API Start =====================");
		logger.info("Class : KPIRestControllar | Method : getDataTypes");

		RestListInfo<DataTypeTO> info = new RestListInfo<DataTypeTO>();

		try {
			info.setData(kpiService.getDataTypes());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : KPIRestControllar  |  Method : getDataTypes " + e);
			e.printStackTrace();
		}

		logger.info("================== KPI Rest Controllar API End =====================");

		return info;
	}

	@GetMapping("/getBLGroup/{mode}")
	public @ResponseBody RestListInfo<BLGroupDetailsDTO> getBLGroup(HttpServletRequest httpServletRequest,
			@PathVariable String mode) {

		logger.info("================== KPI Rest Controllar API Start =====================");
		logger.info("Class : KPIRestControllar | Method : getBLGroup");

		BLGroupDetailsDTO blGroupDetailsDTO = new BLGroupDetailsDTO();

		String groupId = httpServletRequest.getParameter("groupId");
		RestListInfo<BLGroupDetailsDTO> info = new RestListInfo<BLGroupDetailsDTO>();
		try {
			blGroupDetailsDTO.setMode(mode);
			blGroupDetailsDTO.setGroupId(groupId);
			info.setData(kpiService.getBLGroup(blGroupDetailsDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : KPIRestControllar  |  Method : getKPIS " + e);
			e.printStackTrace();
		}

		logger.info("================== KPI Rest Controllar API End =====================");

		return info;
	}
	
	/*
	 * Get KPI's
	 */
	@GetMapping("getAllKPIs")
	public @ResponseBody RestListInfo<KPITO> getAllKPIs(HttpServletRequest httpServletRequest) {

		logger.info("================== KPI Rest Controllar API Start =====================");
		logger.info("Class : KPIRestControllar | Method : getAllKPIs");

		RestListInfo<KPITO> info = new RestListInfo<KPITO>();

		try {
			info.setData(kpiService.getAllKPIs());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : KPIRestControllar  |  Method : getAllKPIs " + e);
			e.printStackTrace();
		}

		logger.info("================== KPI Rest Controllar API End =====================");

		return info;
	}
	
	/*
	 * Edit KPI's
	 */
	@PostMapping("updateKPIs")
	public @ResponseBody RestListInfo<GenericTO> updateKPIs(HttpServletRequest httpServletRequest,@RequestBody GenericGroupDTO genericGroupDTO) {

		logger.info("================== KPI Rest Controllar API Start =====================");
		logger.info("Class : KPIRestControllar | Method : updateKPIs");

		RestListInfo<GenericTO> info = new RestListInfo<GenericTO>();

		try {
			kpiService.updateKPIs(genericGroupDTO);
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			logger.error("Class : KPIRestControllar  |  Method : updateKPIs " + e);
			e.printStackTrace();
		}

		logger.info("================== KPI Rest Controllar API End =====================");

		return info;
	}

	@PostMapping("deleteGroupVariable")
	public @ResponseBody RestListInfo<GenericTO> deleteKPIs(HttpServletRequest httpServletRequest,@RequestBody GenericGroupDTO genericGroupDTO) {

		logger.info("================== KPI Rest Controllar API Start =====================");
		logger.info("Class : KPIRestControllar | Method : updateKPIs");

		RestListInfo<GenericTO> info = new RestListInfo<GenericTO>();

		try {
			kpiService.deleteKPIs(genericGroupDTO);
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			logger.error("Class : KPIRestControllar  |  Method : updateKPIs " + e);
			e.printStackTrace();
		}

		logger.info("================== KPI Rest Controllar API End =====================");

		return info;
	}
}
