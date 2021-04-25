/**
 * 
 */
package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.model.RedeemPointTO;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */
public interface RedeemPointDao {

	RedeemPointTO getPackage(RedeemPointTO redeemPointTO);

	RedeemPointTO getRedeempoint(RedeemPointTO redeemPointTO);

}
