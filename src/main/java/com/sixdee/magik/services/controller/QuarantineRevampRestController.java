package com.sixdee.magik.services.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sixdee.magik.services.model.SpecialDateTO;
import com.sixdee.magik.services.model.WeekDayTO;
import com.sixdee.magik.services.model.WhiteListTO;
import com.sixdee.magik.services.service.QuarantineRevampService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;
import com.sixdee.magik.services.model.BlackListTO;
import com.sixdee.magik.services.model.DNDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.GlobalFilterTO;

/**
 * @author Nakhil Kurian
 * @Date : August 2020
 *
 */

@SuppressWarnings("unused")
@RestController
public class QuarantineRevampRestController {

	static final Logger logger = Logger.getLogger(QuarantineRevampRestController.class);

	@Autowired
	QuarantineRevampService quarantinRevampeService;

	@PostMapping(MagikServicePath.SPECIAL_DATE_CREATE)
	public @ResponseBody RestInfo<GenericTO> saveSpecialDates(HttpServletRequest httpServletRequest,
			@RequestBody SpecialDateTO specialDateTO) {

		logger.info("Class : QuarantineRevamp RestController Start | Method : SaveSpecialDate");

		System.out.println("RestController ::::  " + specialDateTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		HttpSession session = httpServletRequest.getSession();
		try {
			System.out.println("Inside Try");
			specialDateTO.setIsFromFile("false");
			GenericTO saveValue = quarantinRevampeService.saveSpecialDates(specialDateTO);
			info.setData(saveValue);
			info.setMessage("Saved Successfully");
			info.setOperationCode(0);
			System.out.println("saveValue.getCode()" + saveValue.getRespCode());
			if (saveValue.getRespCode() == "1") {
				info.setOperationCode(2);
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage("Saving Failed ");
			info.setOperationCode(1);
			System.out.println("catch  ::: ");
			logger.error("Class : QuarantineRestController  |  Method : saveQuarantineSpecialDates \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  Quarantine SPecial Dates save Rule API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_SPECIAL_DATE_LIST)
	public @ResponseBody RestListInfo<SpecialDateTO> getSpecialDatesList(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== Quarantine Revamp RestController getSpecialDatesList API Start =====================");
		RestListInfo<SpecialDateTO> listInfo = new RestListInfo<SpecialDateTO>();
		try {
			listInfo.setData(quarantinRevampeService.getSpecialDatesList());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : QuarantineRestController  |  Method : getSpecialDatesList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.DELETE_SPECIAL_DATE)
	public @ResponseBody RestInfo<GenericTO> deleteSpecialDate(HttpServletRequest httpServletRequest,
			@RequestBody SpecialDateTO specialDaysTO) {

		logger.info("Class : Quarantine Revamp RestController | Method : deleteSpecialDate");
		System.out.println("specialDaysTO delete :::  " + specialDaysTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		GenericTO genericTO = null;
		try {
			specialDaysTO.setIsFromFile("false");
			genericTO = quarantinRevampeService.deleteSpecialDate(specialDaysTO);
			info.setOperationCode(0);
			info.setMessage("Special Date Delete Successfull");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Special Date Delete Failed");
			logger.error("Class : QuarantineRestController  |  Method : deleteQuarantineSpecialDates \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  Quarantine SPecial Dates save Rule API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.WEEK_DAY_CREATE)
	public @ResponseBody RestInfo<GenericTO> saveWeekDay(HttpServletRequest httpServletRequest,
			@RequestBody WeekDayTO weekDayTO) {

		logger.info("Class : QuarantineRevamp RestController Start | Method : saveWeekDay");

		System.out.println("RestController ::::  " + weekDayTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		HttpSession session = httpServletRequest.getSession();
		try {
			System.out.println("Inside Try");
			GenericTO saveValue = quarantinRevampeService.saveWeekDay(weekDayTO);
			info.setData(saveValue);
			info.setMessage("Saved Successfully");
			info.setOperationCode(0);
			System.out.println("saveValue.getCode()" + saveValue.getRespCode());
			if (saveValue.getRespCode() == "1") {
				info.setOperationCode(2);
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage("Saving Failed ");
			info.setOperationCode(1);
			System.out.println("catch  ::: ");
			logger.error("Class : QuarantineRestController  |  Method : saveWeekDay \n" + e);
			e.printStackTrace();
		}

		logger.info(
				"==================  QuarantineRevamp RestController Start | Method : saveWeekDay API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_WEEK_DAY_LIST)
	public @ResponseBody RestListInfo<WeekDayTO> getWeekDayList(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== Quarantine Revamp RestController getWeekDayList API Start =====================");
		RestListInfo<WeekDayTO> listInfo = new RestListInfo<WeekDayTO>();
		try {
			listInfo.setData(quarantinRevampeService.getWeekDayList());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : QuarantineRestController  |  Method : getWeekDayList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.DELETE_WEEK_DAY)
	public @ResponseBody RestInfo<GenericTO> deleteWeekDay(HttpServletRequest httpServletRequest,
			@RequestBody WeekDayTO weekDayTo) {

		logger.info("Class : Quarantine Revamp RestController | Method : deleteSpecialDate");
		System.out.println("deleteWeekDay  :::  " + weekDayTo);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		GenericTO genericTO = null;
		try {
			genericTO = quarantinRevampeService.deleteWeekDay(weekDayTo);
			info.setOperationCode(0);
			info.setMessage("WeekDay Delete Successfull");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("WeekDay  Delete Failed");
			logger.error("Class : QuarantineRestController  |  Method : deleteWeekDay \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  Quarantine deleteWeekDay save Rule API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.CREATE_BLACK_LIST)
	public @ResponseBody RestInfo<GenericTO> saveBlackList(HttpServletRequest httpServletRequest,
			@RequestBody BlackListTO blackListTO) {
		logger.info("Class : QuarantineRevamp RestController | Method : saveBlackList");
		System.out.println("saveBlackList  :: " + blackListTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		HttpSession session = httpServletRequest.getSession();
		try {
			GenericTO saveValue = quarantinRevampeService.saveBlackList(blackListTO);
			info.setData(saveValue);
			info.setMessage("Saved Successfully");
			info.setOperationCode(0);
			System.out.println("saveValue.getRespCode() :: " + saveValue.getRespCode());
			if (saveValue.getRespCode() == "1") {
				info.setOperationCode(2);
			}
		} catch (Exception e) {
			System.out.println("EXCEPTION:::::::::::::::::::::::");
			ExceptionUtil.handle(e, info);
			logger.error("Class : QuarantineRevamp RestController  |  Method : saveBlackList \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  QuarantineRevamp RestController  saveBlackList API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_BLACK_LIST)
	public @ResponseBody RestListInfo<BlackListTO> getBlackList(HttpServletRequest httpServletRequest) {

		logger.info("================== Quarantine Revamp RestController getBlackList API Start =====================");
		RestListInfo<BlackListTO> listInfo = new RestListInfo<BlackListTO>();
		try {
			listInfo.setData(quarantinRevampeService.getBlackList());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : QuarantineRestController  |  Method : getBlackList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.DELETE_BLACK_LIST)
	public @ResponseBody RestInfo<GenericTO> deleteBlackLsit(HttpServletRequest httpServletRequest,
			@RequestBody BlackListTO blackListTo) {

		logger.info("Class : Quarantine Revamp RestController | Method : deleteBlackLsit");
		System.out.println("deleteBlackLsit  :::  " + blackListTo);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		GenericTO genericTO = null;
		try {
			genericTO = quarantinRevampeService.deleteBlackLsit(blackListTo);
			info.setOperationCode(0);
			info.setMessage("BlackLsit Delete Successfull");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("BlackLsit  Delete Failed");
			logger.error("Class : QuarantineRestController  |  Method : deleteBlackLsit \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  Quarantine deleteBlackLsit Rule API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.CREATE_DND)
	public @ResponseBody RestInfo<GenericTO> saveDND(HttpServletRequest httpServletRequest, @RequestBody DNDTO dndTo) {
		logger.info("Class : QuarantineRevamp RestController Start | Method : saveDND");
		System.out.println("RestController ::::  " + dndTo);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		HttpSession session = httpServletRequest.getSession();
		try {
			System.out.println("Inside Try");
			GenericTO saveValue = quarantinRevampeService.saveDND(dndTo);
			info.setData(saveValue);
			info.setMessage("Saved Successfully");
			info.setOperationCode(0);
			System.out.println("saveValue.getCode()" + saveValue.getRespCode());
			if (saveValue.getRespCode() == "1") {
				info.setOperationCode(2);
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage("Saving Failed ");
			info.setOperationCode(1);
			System.out.println("catch  ::: ");
			logger.error("Class : QuarantineRestController  |  Method : saveDND \n" + e);
			e.printStackTrace();
		}

		logger.info(
				"==================  QuarantineRevamp RestController  | Method : saveDND API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_DND)
	public @ResponseBody RestListInfo<DNDTO> getDND(HttpServletRequest httpServletRequest) {

		logger.info("================== Quarantine Revamp RestController getDND API Start =====================");
		RestListInfo<DNDTO> listInfo = new RestListInfo<DNDTO>();
		try {
			listInfo.setData(quarantinRevampeService.getDND());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : QuarantineRestController  |  Method : getDND \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.DELETE_DND)
	public @ResponseBody RestInfo<GenericTO> deleteDND(HttpServletRequest httpServletRequest,
			@RequestBody DNDTO dndTO) {

		logger.info("Class : Quarantine Revamp RestController | Method : deleteDND");
		System.out.println("deleteDND  :::  " + dndTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		GenericTO genericTO = null;
		try {
			genericTO = quarantinRevampeService.deleteDND(dndTO);
			info.setOperationCode(0);
			info.setMessage("BlackLsit Delete Successfull");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("BlackLsit  Delete Failed");
			logger.error("Class : QuarantineRestController  |  Method : deleteDND \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  QuarantineRevamp deleteDND Rule API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.SAVE_FILe_SPECIAL_DATE)
	public @ResponseBody RestInfo<GenericTO> specialDateFileUpload(HttpServletRequest httpServletRequest,
			@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("================== SpecialDates  Upload File API Start =====================");
		System.out.println("Class : QuarantineRevamp | Method : specialDateFileUpload");
		System.out.println("File Size ::: " + file.getSize());
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		try {
			if (file.getSize() == 0) {
				info.setOperationCode(2);
				info.setMessage("Empty File");
				System.out.println("Empty File ");
			} else {
				System.out.println("File Name :::: " + file.getOriginalFilename());
				if (!file.getOriginalFilename().isEmpty()) {
					System.out.println("File copying Started ");
					BufferedReader br;
					try {
						String line = "";
						InputStream is = file.getInputStream();
						br = new BufferedReader(new InputStreamReader(is));
						List<SpecialDateTO> dateDescTOs = new ArrayList<SpecialDateTO>();
						info.setOperationCode(3);

						while ((line = br.readLine()) != null) {
							System.out.println("Line By Line ::::  " + line);
							String arr[] = line.split("_");
							SpecialDateTO dateDescTO = new SpecialDateTO();
							dateDescTO.setSpecialDate(arr[0]);
							dateDescTO.setDescription(arr[1]);
							dateDescTOs.add(dateDescTO);
						}
						br.close();
						SpecialDateTO daysTO = new SpecialDateTO();
						HttpSession session = httpServletRequest.getSession();
						String specialDayName = httpServletRequest.getParameter("policyName");
						String marketingPlanId = httpServletRequest.getParameter("marketingPlanId");
						System.out.println("Special Date Name  ::  " + specialDayName);
						System.out.println("Special Date Name marketingPlanId  ::  " + marketingPlanId);

						daysTO.setPolicyName(specialDayName);
						daysTO.setMarketingPlanId(marketingPlanId);
						daysTO.setFileName(file.getOriginalFilename());

						System.out.println("File List ::: " + daysTO.toString());

						daysTO.setDesclist(dateDescTOs);
						daysTO.setIsFromFile("true");
						System.out.println("Days To :: " + daysTO);
						info.setOperationCode(0);

						GenericTO SaveFile = quarantinRevampeService.saveSpecialDates(daysTO);
						if (SaveFile.getRespCode() == "1") {
							System.out.println("File Date");
							info.setOperationCode(5);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("File copied done .......");
				}
			}
		} catch (Exception e) {
			System.out.println("Operation Code Last Catch  :::  " + info.getOperationCode());
			if (info.getOperationCode() != 3) {
				ExceptionUtil.handle(e, info);
				logger.error("Class : QuarantineRevamp RestController  |  Method : specialDateFileUpload \n" + e);
				e.printStackTrace();
			}
		}

		logger.info(
				"================== QuarantineRevamp RestController specialDateFileUpload  API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.SAVE_FILE_BLACK_LIST)
	public @ResponseBody RestInfo<GenericTO> blacklistFileUpload(HttpServletRequest httpServletRequest,
			@RequestParam("file") MultipartFile file) throws IOException {
		logger.info(
				"================== QuarantineRevamp RestController blacklistFileUpload File API Start =====================");
		System.out.println(" blacklistFileUpload :::  ");
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		try {
			if (file.getSize() == 0) {
				info.setOperationCode(2);
				info.setMessage("Empty File");
				System.out.println("Empty File ");
			} else {
				System.out.println("File Name  :::" + file.getOriginalFilename());
				if (!file.getOriginalFilename().isEmpty()) {
					System.out.println("File copying Started ::::");
					System.out.println("File Name ::::" + file.getOriginalFilename());
					HttpSession session = httpServletRequest.getSession();
					String policyName = httpServletRequest.getParameter("policyName");
					String marketingPlanId = httpServletRequest.getParameter("marketingPlanId");
					BlackListTO blackListTO = new BlackListTO();

					blackListTO.setId(0);
					blackListTO.setPolicyName(policyName);
					blackListTO.setMarketingPlanId(marketingPlanId);

					List<BlackListTO> blackListNumDateList = new ArrayList<BlackListTO>();
					BufferedReader br;
					info.setOperationCode(3);
					try {
						String line = "";
						InputStream is = file.getInputStream();
						br = new BufferedReader(new InputStreamReader(is));
						while ((line = br.readLine()) != null) {
							System.out.println("Line By Line :: " + line);
							BlackListTO blackListNumDate = new BlackListTO();
							String arr[] = line.split("_");
							blackListNumDate.setMsisdn(arr[0]);
							// blackListNumDate.setDescription(arr[1]);
							blackListNumDate.setExpiryDate(arr[1]);
							blackListNumDateList.add(blackListNumDate);
						}
						blackListTO.setMsisdnList(blackListNumDateList);
						blackListTO.setFileUploadName(file.getOriginalFilename());
						br.close();
						info.setOperationCode(0);
						GenericTO SaveFileBK = quarantinRevampeService.saveBlackList(blackListTO);
						if (SaveFileBK.getRespCode() == "1") {
							System.out.println("File Data");
							info.setOperationCode(6);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("File Copying Done ::: ");
				}
			}
		} catch (Exception e) {
			System.out.println("Operation Code :::   " + info.getOperationCode());
			if (info.getOperationCode() != 3) {
				ExceptionUtil.handle(e, info);
				logger.error("Class : QQuarantineRevamp RestController  |  Method : blacklistFileUpload \n" + e);
				e.printStackTrace();
			}
		}

		logger.info("==================  RblacklistFileUpload  API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.CREATE_WHITELIST)
	public @ResponseBody RestInfo<GenericTO> saveWhiteList(HttpServletRequest httpServletRequest,
			@RequestBody WhiteListTO whitelistTO) {
		logger.info("Class : QuarantineRevamp RestController | Method : saveWhiteList");
		System.out.println("saveWhiteList  :: " + whitelistTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		HttpSession session = httpServletRequest.getSession();
		try {
			GenericTO saveValue = quarantinRevampeService.saveWhiteList(whitelistTO);
			info.setData(saveValue);
			info.setMessage("Saved Successfully");
			info.setOperationCode(0);
			System.out.println("saveValue.getRespCode() :: " + saveValue.getRespCode());
			if (saveValue.getRespCode() == "1") {
				info.setOperationCode(2);
			}
		} catch (Exception e) {
			System.out.println("EXCEPTION:::::::::::::::::::::::");
			ExceptionUtil.handle(e, info);
			logger.error("Class : QuarantineRevamp RestController  |  Method : saveWhiteList \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  QuarantineRevamp RestController  saveWhiteList API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_WHITELIST)
	public @ResponseBody RestListInfo<WhiteListTO> getWhiteList(HttpServletRequest httpServletRequest) {

		logger.info("================== Quarantine Revamp RestController getWhiteList API Start =====================");
		RestListInfo<WhiteListTO> listInfo = new RestListInfo<WhiteListTO>();
		try {
			listInfo.setData(quarantinRevampeService.getWhiteList());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : QuarantineRestController  |  Method : getWhiteList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.DELETE_WHITELIST)
	public @ResponseBody RestInfo<GenericTO> deleteWhiteList(HttpServletRequest httpServletRequest,
			@RequestBody WhiteListTO whitelistTO) {

		logger.info("Class : Quarantine Revamp RestController | Method : deleteWhiteList");
		System.out.println("deleteWhiteList  :::  " + whitelistTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		GenericTO genericTO = null;
		try {
			genericTO = quarantinRevampeService.deleteWhiteList(whitelistTO);
			info.setOperationCode(0);
			info.setMessage("WhiteList Delete Successfull");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("WhiteList  Delete Failed");
			logger.error("Class : QuarantineRestController  |  Method : deleteWhiteList \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  QuarantineRevamp deleteWhiteList Rule API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.SAVE_FILE_WHITELIST)
	public @ResponseBody RestInfo<GenericTO> whiteListFileUpload(HttpServletRequest httpServletRequest,
			@RequestParam("file") MultipartFile file) throws IOException {
		logger.info(
				"================== QuarantineRevamp RestController whiteListFileUpload File API Start =====================");
		System.out.println(" whiteListFileUpload :::  ");
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		try {
			if (file.getSize() == 0) {
				info.setOperationCode(2);
				info.setMessage("Empty File");
				System.out.println("Empty File ");
			} else {
				System.out.println("File Name  :::" + file.getOriginalFilename());
				if (!file.getOriginalFilename().isEmpty()) {
					System.out.println("File copying Started ::::");
					System.out.println("File Name ::::" + file.getOriginalFilename());
					HttpSession session = httpServletRequest.getSession();
					String policyName = httpServletRequest.getParameter("policyName");
					String description = httpServletRequest.getParameter("description");
					WhiteListTO blackListTO = new WhiteListTO();

					blackListTO.setId(0);
					blackListTO.setPolicyName(policyName);
					blackListTO.setDescription(description);

					List<WhiteListTO> blackListNumDateList = new ArrayList<WhiteListTO>();
					BufferedReader br;
					info.setOperationCode(3);
					try {
						String line = "";
						InputStream is = file.getInputStream();
						br = new BufferedReader(new InputStreamReader(is));
						while ((line = br.readLine()) != null) {
							System.out.println("Line By Line :: " + line);
							WhiteListTO blackListNumDate = new WhiteListTO();
							String arr[] = line.split("_");
							blackListNumDate.setMsisdn(arr[0]);
							blackListNumDate.setExpiryDate(arr[1]);
							blackListNumDate.setDescription(arr[2]);
							blackListNumDateList.add(blackListNumDate);
						}
						blackListTO.setMsisdnList(blackListNumDateList);
						blackListTO.setFileUploadName(file.getOriginalFilename());
						br.close();
						info.setOperationCode(0);
						GenericTO SaveFileBK = quarantinRevampeService.saveWhiteList(blackListTO);
						if (SaveFileBK.getRespCode() == "1") {
							System.out.println("File Data");
							info.setOperationCode(6);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("File Copying Done ::: ");
				}
			}
		} catch (Exception e) {
			System.out.println("Operation Code :::   " + info.getOperationCode());
			if (info.getOperationCode() != 3) {
				ExceptionUtil.handle(e, info);
				logger.error("Class : QQuarantineRevamp RestController  |  Method : saveWhiteList \n" + e);
				e.printStackTrace();
			}
		}

		logger.info("==================  saveWhiteList FileUpload  API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.SAVE_GLOBAL_FILTER)
	public @ResponseBody RestInfo<GenericTO> saveGlobalFilter(HttpServletRequest httpServletRequest,
			@RequestBody GlobalFilterTO globalFilterTo) {
		logger.info("Class : QuarantineRevamp RestController Start | Method : saveGlobalFilter");
		System.out.println("RestController ::::  " + globalFilterTo);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		HttpSession session = httpServletRequest.getSession();
		try {
			System.out.println("Inside Try");
			GenericTO saveValue = quarantinRevampeService.saveGlobalFilter(globalFilterTo);
			info.setData(saveValue);
			info.setMessage("Saved Successfully");
			info.setOperationCode(0);
			System.out.println("saveValue.getCode()" + saveValue.getRespCode());
			if (saveValue.getRespCode() == "1") {
				info.setOperationCode(2);
			}
		} catch (Exception e) {
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

	@GetMapping(MagikServicePath.GET_GLOBAL_FILTER)
	public @ResponseBody RestListInfo<GlobalFilterTO> getGlobalFilter(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== Quarantine Revamp RestController getGlobalFilter API Start =====================");
		RestListInfo<GlobalFilterTO> listInfo = new RestListInfo<GlobalFilterTO>();
		try {
			listInfo.setData(quarantinRevampeService.getGlobalFilter());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : QuarantineRestController  |  Method : getGlobalFilter \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping(MagikServicePath.DELETE_GLOBAL_FILTER)
	public @ResponseBody RestInfo<GenericTO> deleteGlobalFilter(HttpServletRequest httpServletRequest,
			@RequestBody GlobalFilterTO globalFilterTO) {

		logger.info("Class : Quarantine Revamp RestController | Method : deleteGlobalFilter");
		System.out.println("GlobalFilte delete :::  " + globalFilterTO);
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		GenericTO genericTO = null;
		try {
			genericTO = quarantinRevampeService.deleteGlobalFilter(globalFilterTO);
			info.setOperationCode(0);
			info.setMessage("Special Date Delete Successfull");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Special Date Delete Failed");
			logger.error("Class : QuarantineRestController  |  Method : deleteGlobalFilter \n" + e);
			e.printStackTrace();
		}

		logger.info("==================  Quarantine SPecial Dates save Rule API End =====================");

		return info;
	}

}
