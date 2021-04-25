package com.sixdee.magik.services.service;

import com.sixdee.magik.services.adaptor.BlackListApiTO;

/**
 * @author Nakhil Kurian
 * @Date : April 2020
 *
 */
public interface BlackListApiService {
	public BlackListApiTO updateBlackListApi(BlackListApiTO blackListTO);

	public BlackListApiTO getStatus(BlackListApiTO blackListTO);


}
