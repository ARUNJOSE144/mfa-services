package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.adaptor.BlackListApiTO;

/**
 * @author Nakhil Kurian
 * @Date : April 2020
 *
 */
public interface BlackListApiDao {

	BlackListApiTO updateBlackListApi(BlackListApiTO blackListTO);

	BlackListApiTO getStatus(BlackListApiTO blackListTO);

}
