package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.MobileAppCategoryTO;
import com.sixdee.magik.services.model.MobileAppMenusTO;
import com.sixdee.magik.services.model.MobileAppTreeDTO;

public interface MobileAppTreeDAO {


	public List<MobileAppCategoryTO> getCategory(MobileAppTreeDTO mobileAppTreeDTO);

	public MobileAppCategoryTO createCategory(MobileAppCategoryTO mobileAppTreeDTO);
	
	public MobileAppCategoryTO editCategory(MobileAppCategoryTO mobileAppTreeDTO);
	
	public MobileAppTreeDTO delCategory(MobileAppTreeDTO mobileAppTreeDTO);
	
	public MobileAppTreeDTO getLanguage(MobileAppTreeDTO mobileAppTreeDTO);
	
	public MobileAppTreeDTO getMenuName(MobileAppTreeDTO mobileAppTreeDTO);
	
	public MobileAppTreeDTO getMessage(MobileAppTreeDTO mobileAppTreeDTO);
	
	public MobileAppMenusTO createMessage(MobileAppMenusTO mobileAppTreeDTO);
	
	public MobileAppMenusTO editMessage(MobileAppMenusTO mobileAppTreeDTO);
	
	public MobileAppTreeDTO delMenu(MobileAppTreeDTO mobileAppTreeDTO);

}
