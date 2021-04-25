package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.HierarchyDao;
import com.sixdee.magik.services.model.ChannelTypeHeirarchy;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.DesignationRoles;

/**
 * @author arun.jose
 * @Date : October, 2018
 *
 */

@Repository
public class HierarchyDaoImpl implements HierarchyDao {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;

	@Override
	public ChannelTypeHeirarchy viewHierarchy() {
		String query = "";

		List<ChannelTypeHeirarchy> list = new ArrayList<ChannelTypeHeirarchy>();
		ChannelTypeHeirarchy result = new ChannelTypeHeirarchy();
		ChannelTypeHeirarchy out = null;
		try {

			Session session = sessionFactory.getCurrentSession();
			query = "from ChannelTypeHeirarchy ";
			list = (List<ChannelTypeHeirarchy>) session.createQuery(query).list();

			out = formatToTreeJson(list, 0, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public ChannelTypeHeirarchy formatToTreeJson(List<ChannelTypeHeirarchy> list, int parent,
			ChannelTypeHeirarchy result) {

		if (parent == 0) {
			for (ChannelTypeHeirarchy channelTypeHeirarchy : list) {
				if (channelTypeHeirarchy.getParentId() == 0) {
					result = channelTypeHeirarchy;
					parent = channelTypeHeirarchy.getChannelType();
				}
			}
		}

		List<ChannelTypeHeirarchy> tos = new ArrayList<ChannelTypeHeirarchy>();

		for (ChannelTypeHeirarchy channelTypeHeirarchy : list) {
			if (channelTypeHeirarchy.getParentId() == parent && parent != 0) {
				tos.add(channelTypeHeirarchy);
				formatToTreeJson(list, channelTypeHeirarchy.getChannelType(), channelTypeHeirarchy);
			}
		}

		result.setChildren(tos);
		return result;
	}

	@Override
	public ChannelTypeHeirarchy createHierarchyNode(ChannelTypeHeirarchy channelTypeHeirarchy) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.save(channelTypeHeirarchy);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return channelTypeHeirarchy;
	}

	@Override
	public ChannelTypeHeirarchy deleteHierarchyNode(ChannelTypeHeirarchy channelTypeHeirarchy) {
		String query = "";
		List<ChannelTypeHeirarchy> list = new ArrayList<ChannelTypeHeirarchy>();
		try {

			Session session = sessionFactory.getCurrentSession();
			query = "from ChannelTypeHeirarchy where  parentId=" + channelTypeHeirarchy.getChannelType();
			list = (List<ChannelTypeHeirarchy>) session.createQuery(query).list();

			if (list.size() == 0)
				session.delete(channelTypeHeirarchy);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelTypeHeirarchy;
	}

	@Override
	public DesignationHierarchy viewDesignationHierarchy(DesignationHierarchy designationHierarchy) {
		String query = "";

		List<DesignationHierarchy> list = new ArrayList<DesignationHierarchy>();
		DesignationHierarchy result = new DesignationHierarchy();
		DesignationHierarchy out = null;
		try {

			Session session = sessionFactory.getCurrentSession();
			query = "from DesignationHierarchy where channelType =" + designationHierarchy.getChannelType();
			list = (List<DesignationHierarchy>) session.createQuery(query).list();

			if (list != null && list.size() > 0)
				out = formatToDesignationTreeJson(list, 0, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public DesignationHierarchy formatToDesignationTreeJson(List<DesignationHierarchy> list, long parent,
			DesignationHierarchy result) {

		if (parent == 0) {
			for (DesignationHierarchy designationHierarchy : list) {
				if (designationHierarchy.getParentId() == 0) {
					result = designationHierarchy;
					parent = designationHierarchy.getDesignationId();
				}
			}
		}

		// ChannelTypeHeirarchy to = new ChannelTypeHeirarchy();

		List<DesignationHierarchy> tos = new ArrayList<DesignationHierarchy>();

		for (DesignationHierarchy designationHierarchy : list) {
			if (designationHierarchy.getParentId() == parent && parent != 0) {
				tos.add(designationHierarchy);
				formatToDesignationTreeJson(list, designationHierarchy.getDesignationId(), designationHierarchy);
			}
		}

		result.setChildren(tos);
		return result;
	}

	@Override
	public DesignationHierarchy createDesignationHierarchyNode(DesignationHierarchy designationHierarchy) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.save(designationHierarchy);

			System.out
					.println("--------------createDesignationHierarchyNode ===> : " + designationHierarchy.toString());
			DesignationRoles designationRoles = new DesignationRoles();
			designationRoles.setDesignationId(designationHierarchy.getDesignationId());
			designationRoles.setRoleId(designationHierarchy.getRoleId());
			session.save(designationRoles);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return designationHierarchy;
	}

	@Override
	public DesignationHierarchy deleteDesignationHierarchyNode(DesignationHierarchy designationHierarchy) {
		String query = "";
		SQLQuery sqlQuery = null;
		List<DesignationHierarchy> list = new ArrayList<DesignationHierarchy>();
		try {

			Session session = sessionFactory.getCurrentSession();
			query = "from DesignationHierarchy where  parentId=" + designationHierarchy.getDesignationId();
			list = (List<DesignationHierarchy>) session.createQuery(query).list();

			if (list.size() == 0) {
				session.delete(designationHierarchy);

				query = "DELETE FROM DesignationRoles WHERE designationId=" + designationHierarchy.getDesignationId();
				session.createQuery(query).executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return designationHierarchy;
	}
}
