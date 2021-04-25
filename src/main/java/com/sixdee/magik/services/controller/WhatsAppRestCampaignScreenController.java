package com.sixdee.magik.services.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.OptionTransientTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WhatsAppDetailsCategoryTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;
import com.sixdee.magik.services.model.WhatsAppMessageDetailsTO;
import com.sixdee.magik.services.service.WhatsAppCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.FileUploadUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
@RestController
public class WhatsAppRestCampaignScreenController {
	
	static final Logger logger = Logger.getLogger(WhatsAppRestCampaignScreenController.class);
	
	//Replace filepath in WhatsAppDAOImpl ....
	private static  final String FILE_PATH = "C:\\Magik_3.0\\DMS\\WhatsAppData\\"; 
	private static final String SERVER=null;
	private static final String USERNAME=null;
	private static final String PASSWORD=null;
	
	@Autowired
	SystemProperties properties;
	
	@Autowired
	WhatsAppCampaignScreenService whatsAppservices;
		
	
	
	@GetMapping(MagikServicePath.GETLANGUAGEs_DATA)
	public @ResponseBody RestListInfo<MessageLanguagesTO> getLanguages(HttpServletRequest httpServletRequest) {

		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : getLanguages");
		RestListInfo<MessageLanguagesTO> info = new RestListInfo<MessageLanguagesTO>();
		try {
			info.setData(whatsAppservices.getLanguages());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
	
		return info;

    }
	
	@GetMapping(MagikServicePath.GET_WHATSAPP_MSG_DATA)
	public @ResponseBody RestListInfo<WhatsAppDetailsTO> getWhatsAppData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId,@RequestParam(value = "categoryId") int categoryId) 
	{
		
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : getWhatsAppData");
		RestListInfo<WhatsAppDetailsTO> info = new RestListInfo<WhatsAppDetailsTO>();
		try {
			  info.setData(whatsAppservices.getWhatsAppData(userId,categoryId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : getWhatsAppData " + e);
			e.printStackTrace();
		}
		logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
		return info;
		
	}
	
	@GetMapping(MagikServicePath.GETWHATSAPP_CATEGORGYs_DATA)
	public @ResponseBody RestListInfo<WhatsAppDetailsCategoryTO> getWhatsAppCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : getWhatsAppCategory");
		RestListInfo<WhatsAppDetailsCategoryTO> info = new RestListInfo<WhatsAppDetailsCategoryTO>();
		try {
			  info.setData(whatsAppservices.getWhatsAppCategories(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : getWhatsAppCategory " + e);
			e.printStackTrace();
		}
		logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
		return info;
		
	}
	
	
	@GetMapping(value=MagikServicePath.GET_EDIT_CATEGORY_DATA)
	public @ResponseBody RestInfo<String> getEditWhatsAppCategoryData(HttpServletRequest httpServletRequest,@RequestParam(value = "categoryAutoID") int autoId) 
	{
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : getEditWhatsAppCategoryData");
		RestInfo<String> info = new RestInfo<String>();
		String whatsAppdata ="";
		try {
			   whatsAppdata=whatsAppservices.getSelectedCategoryRecord(autoId);
			   info.setData(whatsAppdata);
			   info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : getEditWhatsAppCategoryData " + e);
			e.printStackTrace();
		}
		logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
		return info;
		
	}	
	
	@GetMapping(value=MagikServicePath.GET_EDIT_RECORD_DATA)
	public @ResponseBody RestInfo<WhatsAppDetailsTO> getEditWhatsAppData(HttpServletRequest httpServletRequest,@RequestParam(value = "messageAutoID") int autoId) 
	{
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : getWhatsAppData");
		RestInfo<WhatsAppDetailsTO> info = new RestInfo<WhatsAppDetailsTO>();
		WhatsAppDetailsTO whatsAppdata =null;
		try {
			   whatsAppdata=whatsAppservices.getSelectedRecord(autoId);
			   info.setData(whatsAppdata);
			   info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : getWhatsAppData " + e);
			e.printStackTrace();
		}
		logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
		return info;
		
	}	
	
	@PostMapping(value=MagikServicePath.SAVE_UPDATE_WHATSAPP_CATEGORY)
	public @ResponseBody RestInfo<String> SaveorUpdateWhatsAppCategory(HttpServletRequest httpServletRequest,@RequestBody WhatsAppDetailsCategoryTO whatsAppRequestBodyTO
							) throws IOException 
	{
		
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : SaveorUpdateWhatsAppCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
		try 
		{
			statuscode=whatsAppservices.saveorUpdateWhatsAppCategoryData(whatsAppRequestBodyTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} 
	    catch (Exception e) {
			logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : saveWhatsAppMessage " + e);
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
	     }
	    logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
        return info;
	}
	
	@PostMapping(value=MagikServicePath.SAVE_UPDATE_WHATSAPP_MSG)
	public @ResponseBody RestInfo<String> SaveorUpdateWhatsAppMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO whatsAppRequestBodyTO
							) throws IOException 
	{
		
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : saveWhatsAppMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=whatsAppservices.saveorUpdateWhatsAppData(whatsAppRequestBodyTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : saveWhatsAppMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
			
					
		return info;
			
		
	}
	
	
	
	@GetMapping(value = MagikServicePath.DELETE_WHATSAPP_MSG)
	public @ResponseBody RestInfo<String> deleteWhatsAppMsg(HttpServletRequest httpServletRequest,
			@RequestParam(value = "messageAutoID") int autoId) {
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : deleteWhatsAppMsg");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(whatsAppservices.deleteWhatsAppMsg(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : deleteWhatsAppMsg " + e);
			e.printStackTrace();
		}
		logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
		return info;
	}
	
	
	
//==================================Old methods with MultiPart====================================================================================.	
	
	//@PostMapping(value =MagikServicePath.SAVE_WHATSAPP_MSG,consumes = {"multipart/form-data"})
	public @ResponseBody RestInfo<String> saveWhatsAppMessage(HttpServletRequest httpServletRequest,
							@RequestParam(value = "messageName") String messageName,
							@RequestParam(value = "languages") String Jsonlanguages,
							@RequestParam(value = "contentEditable") String messageDesc,
							@RequestParam(value = "userId") String userId,
							@RequestParam(value = "files") MultipartFile [] files) throws IOException 
	{
		
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : saveWhatsAppMessage");
		RestInfo<String> info = new RestInfo<String>();
		List<WhatsAppMessageDetailsTO>  whatsApp_MSG_DTLS_LIST =new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<OptionTransientTO> languages = mapper.readValue(Jsonlanguages, new TypeReference<List<OptionTransientTO>>(){});
		
         
		//		Save Messages and set that to folderName of attached doc's
					try 
					{
						whatsApp_MSG_DTLS_LIST = whatsAppservices.saveWhatsAppMsg(messageName, languages, messageDesc, userId);
						if(whatsApp_MSG_DTLS_LIST.size()>0 && files.length>0)
						{ 
							 for(WhatsAppMessageDetailsTO childObj:whatsApp_MSG_DTLS_LIST)
							   {
								 String unique_Folder_PATH =childObj.getWhatsAppDetailsTO().getAutoId()+"\\"+childObj.getLangagueTO().getAutoId();
								 String mainFolder_PATH = properties.getFilePath().trim()+"WhatsAppData\\"+unique_Folder_PATH+"\\";
									for(MultipartFile fileObj : files) 
									{
										 
										 String unique_fileName =childObj.getLangagueTO().getAutoId()+"-"+fileObj.getOriginalFilename();
										 
										 FileUploadUtil.saveFilesToServerFolder(mainFolder_PATH,fileObj,unique_fileName,false);
									
									 
									
									}
								 
							   }	
							
						}
						info.setOperationCode(0);
						//info.setData(CacheDaoImpl.messages.get(statusCode));
						info.setMessage("Success");
					} 
				    catch (Exception e) {
				    	ExceptionUtil.handle(e, info);
						logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : saveWhatsAppMessage " + e);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
			
					
		return info;
			
		
	}
	
	
	
	///@PostMapping(value =MagikServicePath.UPDATE_WHATSAPP_MSG,consumes = {"multipart/form-data"})
	public @ResponseBody RestInfo<String> updateWhatsAppMessage(HttpServletRequest httpServletRequest,
			                @RequestParam(value = "messageAutoId") int messageAutoId,
							@RequestParam(value = "messageName") String messageName,
							@RequestParam(value = "languages") String Jsonlanguages,
							@RequestParam(value = "contentEditable") String messageDesc,
							@RequestParam(value = "userId") String userId,
							@RequestParam(value = "files") MultipartFile [] files) throws IOException 
	{
		
		logger.info("================== WhatsAppRestCampaignScreenController API Start =====================");
		logger.info("Class : WhatsAppRestCampaignScreenController | Method : saveWhatsAppMessage");
		RestInfo<String> info = new RestInfo<String>();
		List<WhatsAppMessageDetailsTO>  whatsApp_MSG_DTLS_LIST =new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<OptionTransientTO> languages = mapper.readValue(Jsonlanguages, new TypeReference<List<OptionTransientTO>>(){});
		
         
		//		Save Messages and set that to folderName of attached doc's
					try 
					{
						whatsApp_MSG_DTLS_LIST = whatsAppservices.updateWhatsAppMsg(messageAutoId,messageName, languages, messageDesc, userId);
						if(whatsApp_MSG_DTLS_LIST.size()>0 && files.length>0)
						{ 
							 for(WhatsAppMessageDetailsTO childObj:whatsApp_MSG_DTLS_LIST)
							   {
								 String unique_Folder_PATH =childObj.getWhatsAppDetailsTO().getAutoId()+"\\"+childObj.getLangagueTO().getAutoId();
								 String mainFolder_PATH = properties.getFilePath().trim()+"WhatsAppData\\"+unique_Folder_PATH+"\\";
									for(MultipartFile fileObj : files) 
									{
										
                                         String unique_fileName =childObj.getLangagueTO().getAutoId()+"-"+fileObj.getOriginalFilename();
                                         FileUploadUtil.saveFilesToServerFolder(mainFolder_PATH,fileObj,unique_fileName,true);
									}
								 
							   }	
							
						}
						info.setOperationCode(0);
						//info.setData(CacheDaoImpl.messages.get(statusCode));
						info.setMessage("Success");
					} 
				    catch (Exception e) {
				    	ExceptionUtil.handle(e, info);
						logger.error("Class : WhatsAppRestCampaignScreenController  |  Method : saveWhatsAppMessage " + e);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== WhatsAppRestCampaignScreenController API End =====================");
			
					
		return info;
			
		
	}
	

	
	
}