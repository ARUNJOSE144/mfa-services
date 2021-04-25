package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ShortcutsDAO;
import com.sixdee.magik.services.model.MenuDetailDTO;
import com.sixdee.magik.services.model.ShortcutsTO;

/**
 * @author Arun Jose April 2019
 */
@Repository
public class ShortcutsDAOImpl implements ShortcutsDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;

	@Override
	public List<ShortcutsTO> getShortcuts() {
		String query = "";
		Session session = sessionFactory.getCurrentSession();

		query = "from ShortcutsTO order by id ";
		List<ShortcutsTO> list = (List<ShortcutsTO>) session.createQuery(query).list();

		return list;
	}

	@Override
	public String createShortcuts(ShortcutsTO shortcutsTO) {
		String statusCode = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		session.save(shortcutsTO);
		statusCode = "SC0000";
		return statusCode;

	}

	@Override
	public List<ShortcutsTO> getMenus() {
		String query = "";
		Session session = sessionFactory.getCurrentSession();
		List<ShortcutsTO> shortcutsTO = new ArrayList<ShortcutsTO>();
		Map<Integer, String> imageMap = new HashMap<Integer, String>();

		query = "from MenuDetailDTO order by parentId,menuOrder ";
		
		List<MenuDetailDTO> list = (List<MenuDetailDTO>) session.createQuery(query).list();
		
		for (MenuDetailDTO dto : list) {
			
			if (dto.getParentId() == 0) {
				imageMap.put(dto.getMenuId(), dto.getIconName());
			}

			if (dto.isShortcut()) {
				ShortcutsTO to = new ShortcutsTO();
				to.setId(dto.getMenuId());
				to.setName(dto.getMenuName());
				to.setUrl(dto.getUrl());
				to.setImageName(imageMap.get(dto.getParentId()));

				shortcutsTO.add(to);
			}
		}

		return shortcutsTO;
	}

	@Override
	public String deleteShortcuts(ShortcutsTO shortcutsTO) {
		String statusCode = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		session.delete(shortcutsTO);
		statusCode = "SC0000";
		return statusCode;
	}
	
}
