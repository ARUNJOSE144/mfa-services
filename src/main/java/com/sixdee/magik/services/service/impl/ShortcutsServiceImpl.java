package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ShortcutsDAO;
import com.sixdee.magik.services.model.ShortcutsTO;
import com.sixdee.magik.services.service.ShortcutsService;

/**
 * @author Arun jose
 *
 *         April, 2019
 */
@Service
@Transactional
public class ShortcutsServiceImpl implements ShortcutsService {

	@Autowired
	ShortcutsDAO shortcutsDAO;

	@Override
	public List<ShortcutsTO> getShortcuts() {
		// TODO Auto-generated method stub
		return shortcutsDAO.getShortcuts();
	}

	@Override
	public String createShortcuts(ShortcutsTO shortcutsTO) {
		// TODO Auto-generated method stub
		return shortcutsDAO.createShortcuts(shortcutsTO);
	}

	@Override
	public List<ShortcutsTO> getMenus() {
		// TODO Auto-generated method stub
		return shortcutsDAO.getMenus();
	}

	@Override
	public String deleteShortcuts(ShortcutsTO shortcutsTO) {
		// TODO Auto-generated method stub
		return shortcutsDAO.deleteShortcuts(shortcutsTO);
	}

}
