package com.alilm.campaign.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.alilm.campaign.endpoints.CampaignException;

public class CampaignExceptionFactory {

	private CampaignExceptionFactory() {}
	
	private static Map<Integer, CampaignException> errorStatusHandlers = new HashMap<Integer, CampaignException>(3);

	static {
		errorStatusHandlers.put(HttpStatus.NOT_FOUND.value(), new CampaignNotFoundException());
		errorStatusHandlers.put(HttpStatus.EXPECTATION_FAILED.value(), new CampaignInvalidException());
		errorStatusHandlers.put(HttpStatus.NOT_ACCEPTABLE.value(), new CampaignFailedException());
	}
	
	public static CampaignException get(int httpStatusCode) {
		return errorStatusHandlers.get(httpStatusCode);
	}
	
}
