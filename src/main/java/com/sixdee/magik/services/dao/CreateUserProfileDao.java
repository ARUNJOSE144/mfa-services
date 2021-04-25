package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.CreateUSerProfileTO;

/**
 * @author Nakhil Kurian
 * @Date : February 2020
 *
 */
public interface CreateUserProfileDao {

	CreateUSerProfileTO getUserProfiles(CreateUSerProfileTO profileTO);

}
