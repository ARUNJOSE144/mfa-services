package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.CreateAccountCustmCareTO;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
public interface CreateAccountCustmCareService {

	CreateAccountCustmCareTO CreateAccount(CreateAccountCustmCareTO createAccountTO);

	CreateAccountCustmCareTO deleteAccount(CreateAccountCustmCareTO deleteAccountTO);

}
