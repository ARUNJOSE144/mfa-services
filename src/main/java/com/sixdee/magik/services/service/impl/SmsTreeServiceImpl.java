package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.SmsTreeDAO;
import com.sixdee.magik.services.model.SMSRequest;
import com.sixdee.magik.services.model.SMSResponse;
import com.sixdee.magik.services.model.SmsCategoryTO;
import com.sixdee.magik.services.service.SmsTreeService;

@Service
@Transactional
public class SmsTreeServiceImpl implements SmsTreeService {

	@Autowired
	private SmsTreeDAO smsTreeDAO;

	@Override
	public List<SmsCategoryTO> getCategory(int userId) {
		// TODO Auto-generated method stub
		return smsTreeDAO.getCategory(userId);
	}

	@Override
	public SMSResponse createCategory(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.createCategory(req);
	}

	@Override
	public SMSResponse editCategory(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.editCategory(req);
	}

	@Override
	public SMSResponse delCategory(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.delCategory(req);
	}

	@Override
	public SMSResponse getLanguage(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.getLanguage(req);
	}

	@Override
	public SMSResponse getMenuName(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.getMenuName(req);
	}

	@Override
	public SMSResponse getMessage(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.getMessage(req);
	}

	@Override
	public SMSResponse createMessage(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.createMessage(req);
	}

	@Override
	public SMSResponse editMessage(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.editMessage(req);
	}

	@Override
	public SMSResponse delMenu(SMSRequest req) {
		// TODO Auto-generated method stub
		return smsTreeDAO.delMenu(req);
	}
	
	//================================previous===================
//	@Override
//	 public SmsTreeDTO smsTree(SmsTreeDTO smstreedto){
//		smstreedto = smsTreeDAO.smsTree(smstreedto);
//		return smstreedto;
//	}


//	@Override
//	public List<CategoryTO> getCategory() {
//		// TODO Auto-generated method stub
//		return smsTreeDAO.getCategory();
//	}
//
//
//	
//
//	@Override
//	public List<SmsTreeDTO> getMenuName(SmsTreeDTO smsMenuTO) {
//		// TODO Auto-generated method stub
//		return smsTreeDAO.getMenuName(smsMenuTO);
//	}
//
//
//
//
//	@Override
//	public SmsTreeDTO createMessage(SmsTreeDTO smsMenuDTO) {
//		// TODO Auto-generated method stub
//		smsMenuDTO=smsTreeDAO.createMessage(smsMenuDTO);
//		return smsMenuDTO;
//	}
//
//
//
//
//	@Override
//	public List<SmsTreeDTO> getMessege(SmsTreeDTO smsMenuTO) {
//		// TODO Auto-generated method stub
//		return smsTreeDAO.getMessege(smsMenuTO);
//	}
//
//
//
//
//	@Override
//	public List<SmsTreeDTO> getLang() {
//		// TODO Auto-generated method stub
//		return smsTreeDAO.getLang();
//	}
//
//
//
//
//	@Override
//	public SmsTreeDTO delMenu(SmsTreeDTO smsMenuDTO) {
//		// TODO Auto-generated method stub
//		return smsTreeDAO.delMenu(smsMenuDTO);
//	}




//	@Override
//	public SmsTreeDTO editMessage(SmsTreeDTO smsMenuDTO) {
//		// TODO Auto-generated method stub
//		return smsTreeDAO.editMessage(smsMenuDTO);
//	}


	
}
