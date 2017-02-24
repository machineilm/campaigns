package com.alilm.campaign.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class CampaignUtils {

	public static Boolean isEmpty(Map map) {
		return (map == null || map.size() == 0);
	}
	
	public static Boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

	public static Date getExpirationDatetime(Long duration) {
		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date afterAddingTenMins=new Date(t + (duration * ONE_MINUTE_IN_MILLIS));
		return afterAddingTenMins;
	}

}
