package com.inno.mfa.services.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.LoginTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class AuthenticationDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	public LoginTo login() {
		// TODO Auto-generated method stub
		LoginTo loginTo = new LoginTo();
		loginTo.setResultCode("0");
		loginTo.setResponseMsg("Success");
		loginTo.setToken("12467e1b-e135-41a3-85a8-d229794308f9");
		loginTo.setRefreshToken("d00aeaef-ae18-4fdd-bbce-9825773026c7");
		loginTo.setUserName("c3lzYWRtaW4=");
		loginTo.setUserId(1);
		loginTo.setFullName("SysAdmin SysAdmin");
		int[] privilages = new int[] { 80000, 27009, 7010, 7011, 7012, 7020, 7021, 7022, 6000, 22000, 102001, 22001,
				6001, 102000, 6002, 22002, 102003, 6003, 22003, 102002, 7030, 27000, 27001, 27002, 27003, 102010, 27004,
				27006 };

		loginTo.setPrivilages(privilages);

		return loginTo;
	}
}
