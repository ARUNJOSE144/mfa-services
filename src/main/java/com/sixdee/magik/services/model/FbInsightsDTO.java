/**
 * 
 */
package com.sixdee.magik.services.model;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author bhavyalakshmi.k
 *
 */
@JsonInclude(Include.NON_NULL)
@Component
public class FbInsightsDTO {

	private String data[];
	private String noOfDays;
	private String campaignId;

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	@Override
	public String toString() {
		return "FbInsightsDTO [data=" + Arrays.toString(data) + ", noOfDays=" + noOfDays + ", campaignId=" + campaignId
				+ "]";
	}
	

}
