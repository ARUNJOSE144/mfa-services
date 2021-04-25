package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ShortcutsTO;

/**
 * @author Arun jose
 *
 * April, 2019
 */
public interface ShortcutsService {

	List<ShortcutsTO> getShortcuts();

	String createShortcuts(ShortcutsTO shortcutsTO);

	List<ShortcutsTO> getMenus();

	String deleteShortcuts(ShortcutsTO shortcutsTO);
	
	

}
