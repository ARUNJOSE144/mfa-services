package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.SMSRequest;
import com.sixdee.magik.services.model.SMSResponse;
import com.sixdee.magik.services.model.SmsCategoryTO;

public interface SmsTreeDAO {

	//public SmsTreeDTO smsTree(SmsTreeDTO smsTreeDTO);

	public List<SmsCategoryTO> getCategory(int userId);

	public SMSResponse createCategory(SMSRequest req);

	public SMSResponse editCategory(SMSRequest req);

	public SMSResponse delCategory(SMSRequest req);

	public SMSResponse getLanguage(SMSRequest req);

	public SMSResponse getMenuName(SMSRequest req);

	public SMSResponse getMessage(SMSRequest req);

	public SMSResponse createMessage(SMSRequest req);

	public SMSResponse editMessage(SMSRequest req);

	public SMSResponse delMenu(SMSRequest req);

//	public List<SmsTreeDTO> getMenuName(SmsTreeDTO smsMenuTO);
//
//	public SmsTreeDTO createMessage(SmsTreeDTO smsMenuDTO);
//
//	public List<SmsTreeDTO> getMessege(SmsTreeDTO smsMenuTO);
//
//	public List<SmsTreeDTO> getLang();
//
//	public SmsTreeDTO delMenu(SmsTreeDTO smsMenuDTO);
//
//	public SmsTreeDTO editMessage(SmsTreeDTO smsMenuDTO);

}
