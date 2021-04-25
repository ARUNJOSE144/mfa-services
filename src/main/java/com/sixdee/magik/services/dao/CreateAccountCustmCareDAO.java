package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.CreateAccountCustmCareTO;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
public interface CreateAccountCustmCareDAO {

	CreateAccountCustmCareTO CreateAccount(CreateAccountCustmCareTO createAccountTO);

	CreateAccountCustmCareTO deleteAccount(CreateAccountCustmCareTO deleteAccountTO);

}
