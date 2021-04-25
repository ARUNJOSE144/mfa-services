package com.sixdee.magik.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.DaywiseCampaignConversionTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.service.DaywiseCampaignConversionReportService;
import com.sixdee.magik.services.util.CustomMappingStrategy;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : February, 2021
 */

@RestController
public class DaywiseCampaignConversionRPTRestController {

	static final Logger logger = Logger.getLogger(DaywiseCampaignConversionRPTRestController.class);

	@Autowired
	DaywiseCampaignConversionReportService serviceLayer;

	@GetMapping(MagikServicePath.GET_CAMPIAGN_DATA)
	public @ResponseBody RestListInfo<CampaignDefMasterTO> getCampaignRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== DaywiseCampaignConversionRPTRestController API Start =====================");
		logger.info("Class : DaywiseCampaignConversionRPTRestController | Method : getCampaignRecords");

		RestListInfo<CampaignDefMasterTO> info = new RestListInfo<CampaignDefMasterTO>();
		try {
			info.setData(serviceLayer.getCampaignData());
		} catch (Exception e) {

			ExceptionUtil.handle(e, info);
			logger.error("Class : DaywiseCampaignConversionRPTRestController  |  Method : getCampaignRecords " + e);
			e.printStackTrace();
		}

		logger.info("================== DaywiseCampaignConversionRPTRestController API End =====================");

		return info;

	}

	@GetMapping(MagikServicePath.GET_DAYWISE_CAMPCONV_RPT_DATA)
	public @ResponseBody RestListInfo<DaywiseCampaignConversionTO> getAllRecords(
			HttpServletRequest httpServletRequest) {

		logger.info("================== DaywiseCampaignConversionRPTRestController API Start =====================");
		logger.info("Class : DaywiseCampaignConversionRPTRestController | Method : getAllRecords");

		RestListInfo<DaywiseCampaignConversionTO> info = new RestListInfo<DaywiseCampaignConversionTO>();
		try {
			info.setData(serviceLayer.getData());
		} catch (Exception e) {

			ExceptionUtil.handle(e, info);
			logger.error("Class : DaywiseCampaignConversionRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}

		logger.info("================== DaywiseCampaignConversionRPTRestController API End =====================");

		return info;

	}

	@PostMapping(MagikServicePath.GET_DAYWISE_CAMPCONV_RPT_DATA_WITH_PAGINATION)
	public @ResponseBody RestInfo<PaginationDTO<DaywiseCampaignConversionTO>> getAllRecordsWithPagination(
			HttpServletRequest httpServletRequest,
			@RequestBody PaginationDTO<DaywiseCampaignConversionTO> paginationDTO) {

		logger.info("================== DaywiseCampaignConversionRPTRestController API Start =====================");
		logger.info("Class : DaywiseCampaignConversionRPTRestController | Method : getAllRecords");

		RestInfo<PaginationDTO<DaywiseCampaignConversionTO>> info = new RestInfo<PaginationDTO<DaywiseCampaignConversionTO>>();
		try {
			info.setData(serviceLayer.getAllRecordsWithPagination(paginationDTO, false));
		} catch (Exception e) {

			ExceptionUtil.handle(e, info);
			logger.error("Class : DaywiseCampaignConversionRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}

		logger.info("================== DaywiseCampaignConversionRPTRestController API End =====================");

		return info;

	}

	@PostMapping("/getDayWiseRptDataWithPaginationDowloadFile")
	public void downloadReport(HttpServletRequest httpServletRequest,
			@RequestBody PaginationDTO<DaywiseCampaignConversionTO> paginationDTO, HttpServletResponse httpResponse) {

		logger.info("================== DaywiseCampaignConversionRPTRestController API Start =====================");
		logger.info("Class : DaywiseCampaignConversionRPTRestController | Method : getAllRecords");

		RestInfo<PaginationDTO<DaywiseCampaignConversionTO>> info = new RestInfo<PaginationDTO<DaywiseCampaignConversionTO>>();
		//PaginationDTO<DaywiseCampaignConversionTO> paginationDTO = new PaginationDTO<DaywiseCampaignConversionTO>();
		try {
			// info.setData(serviceLayer.getAllRecordsWithPagination(paginationDTO, true));
			List<DaywiseCampaignConversionTO> list = serviceLayer.getAllRecordsWithPagination(paginationDTO, true)
					.getData();
			
			httpResponse.setContentType("text/csv");
			httpResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachement: fileName=UploadedOutletDetails");
			if (list != null && !list.isEmpty()) {
				CustomMappingStrategy<DaywiseCampaignConversionTO> mappingStrategy = new CustomMappingStrategy<>();
				mappingStrategy.setType(DaywiseCampaignConversionTO.class);

				final StatefulBeanToCsv<DaywiseCampaignConversionTO> beanToCsv = new StatefulBeanToCsvBuilder<DaywiseCampaignConversionTO>(
						httpResponse.getWriter()).withMappingStrategy(mappingStrategy).withSeparator(',')
								.withApplyQuotesToAll(false).build();

				beanToCsv.write(list);

			}

		} catch (Exception e) {

			ExceptionUtil.handle(e, info);
			logger.error("Class : DaywiseCampaignConversionRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}

		logger.info("================== DaywiseCampaignConversionRPTRestController API End =====================");

	}

}
