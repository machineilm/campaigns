package com.alilm.campaign.vo;

import java.util.HashMap;
import java.util.Map;

public class CampaignErrorVo {

	private Map<String, String> errors = new HashMap<String, String>(5);

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
}
