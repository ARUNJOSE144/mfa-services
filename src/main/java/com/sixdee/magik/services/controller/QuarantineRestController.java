package com.sixdee.magik.services.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sixdee.magik.services.model.QuarantineTO;
import com.sixdee.magik.services.service.QuarantineService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;
import com.sixdee.magik.services.model.QuarantineBlackListTO;
import com.sixdee.magik.services.model.QuarantineDetailsTO;

/**
 * @author arun.jose
 * @Date : January, 2019
 *
 */

@RestController
public class QuarantineRestController {

	static final Logger logger = Logger.getLogger(QuarantineRestController.class);

	@Autowired
	QuarantineService quarantineService;

	@PostMapping(MagikServicePath.CREATE_QUARANTINE_POLICY)
	public @ResponseBody RestInfo<String> createQuarantinePolicy(HttpServletRequest httpServletRequest,@RequestBody QuarantineTO quarantineTO) {

		logger.info("==================  QuarantineRestController Controllar API Start =====================");
		logger.info("Class : QuarantineRestController | Method : getApplicationProperties");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(quarantineService.createQuarantinePolicy(quarantineTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : QuarantineRestController  |  Method : QuarantineRestController " + e);
			e.printStackTrace();
		}
		logger.info("================== QuarantineRestController Rest Controllar API End =====================");
		return info;
	}
	
	
	@PostMapping(MagikServicePath.DELETE_QUARANTINE_POLICY)
	public @ResponseBody RestInfo<String> deleteQuarantinePolicy(HttpServletRequest httpServletRequest,@RequestBody QuarantineTO quarantineTO) {

		logger.info("==================  QuarantineRestController Controllar API Start =====================");
		logger.info("Class : QuarantineRestController | Method : getApplicationProperties");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(quarantineService.deleteQuarantinePolicy(quarantineTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : QuarantineRestController  |  Method : QuarantineRestController " + e);
			e.printStackTrace();
		}
		logger.info("================== QuarantineRestController Rest Controllar API End =====================");
		return info;
	}
	
	
	@GetMapping(MagikServicePath.GET_QUARANTINE_POLICY_LIST)
	public @ResponseBody RestListInfo<QuarantineTO> getQuarantinePolicyList(HttpServletRequest httpServletRequest) {

		logger.info("==================  QuarantineRestController Controllar API Start =====================");
		logger.info("Class : QuarantineRestController | Method : getApplicationProperties");
		RestListInfo<QuarantineTO> info = new RestListInfo<QuarantineTO>();
		try {
			info.setData(quarantineService.getQuarantinePolicyList());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : QuarantineRestController  |  Method : QuarantineRestController " + e);
			e.printStackTrace();
		}
		logger.info("================== QuarantineRestController Rest Controllar API End =====================");
		return info;
	}
	
	@PostMapping(MagikServicePath.UPLOAD_BLACKLIST_FILE_DETAILS)
	public @ResponseBody RestInfo<String> uploadBlacklist(HttpServletRequest httpServletRequest,
			@RequestParam("file") MultipartFile file) throws Exception {
		RestInfo<String> info = new RestInfo<String>();
		try {
			if (file.getSize() == 0) {
				info.setOperationCode(0);
				info.setMessage("file contain anything");
				System.out.println(" file contain anythings");
			} else {
				System.out.println("File Name >>> :" + file.getOriginalFilename());
				if (!file.getOriginalFilename().isEmpty()) {
					System.out.println("inside else");
					System.out.println("File copied start .......");
					System.out.println("File Name >>> :" + file.getOriginalFilename());
					BufferedReader br;
					try {
						String line = "";
						InputStream is = file.getInputStream();
						br = new BufferedReader(new InputStreamReader(is));
						List<QuarantineBlackListTO> to = new ArrayList<QuarantineBlackListTO>();
						info.setOperationCode(1);
						info.setMessage("file Reading  start");

						while ((line = br.readLine()) != null) {
							System.out.println("line>>>" + line);
							String arr[] = line.split(",");
							for (int i = 0; i < arr.length; i++) {
								QuarantineBlackListTO testto = new QuarantineBlackListTO();
								testto.setFileName(file.getOriginalFilename());
								testto.setMsisdn(arr[i]);
								testto.setIsFromFile("true");
								to.add(testto);
								System.out.println("file content " + testto);
								info.setData(quarantineService.uploadBlacklist(testto));
							}
						}
						br.close();
						info.setOperationCode(2);
						info.setMessage("success");
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("File copied done .......");
				}
			}
		} catch (Exception e) {
			System.out.println("operationCode>>>>>>   " + info.getOperationCode());
		}

		return info;
	}
	
	@GetMapping(MagikServicePath.EDIT_QUARANTINE_DETAILS)
	public @ResponseBody RestListInfo<QuarantineDetailsTO> editQuarantineDetails(HttpServletRequest httpServletRequest) {

		logger.info("================== Quarantine Configurations Rest Controllar API Start =====================");
		logger.info("Class : editQuarantineDetailsRestControllar | Method : editQuarantineDetails");

		RestListInfo<QuarantineDetailsTO> info = new RestListInfo<QuarantineDetailsTO>();
		int id = Integer.parseInt(httpServletRequest.getParameter("id"));

		try {
			info.setOperationCode(0);
			info.setMessage("editQuarantineDetails successfuly.");
			info.setData(quarantineService.editQuarantineDetails(id));
		} catch (Exception e) {
			info.setOperationCode(1);
			info.setMessage("editQuarantineDetails process Failed.");
			ExceptionUtil.handle(e, info);
			logger.error("Class : editQuarantineDetailsRestControllar  |  Method : editQuarantineDetails " + e);
			e.printStackTrace();
		}

		logger.info("================== Quarantine  Rest Controllar API End =====================");

		return info;
	}
}
