/**
 * 
 */
package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ShortcutsTO;

/**
 * @author Arun Jose
 * April 2019
 */
public interface ShortcutsDAO {

	List<ShortcutsTO> getShortcuts();

	String createShortcuts(ShortcutsTO shortcutsTO);

	List<ShortcutsTO> getMenus();

	String deleteShortcuts(ShortcutsTO shortcutsTO);

	

}
