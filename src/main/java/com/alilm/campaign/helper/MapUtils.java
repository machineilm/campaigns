package com.alilm.campaign.helper;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class MapUtils {

	public static Boolean isEmpty(Map map) {
		return (map == null || map.size() == 0);
	}
	
	public static Boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}
	
}
