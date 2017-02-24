package com.alilm.campaign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alilm.campaign.endpoints.CampaignException;
import com.alilm.campaign.vo.CampaignErrorVo;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource could not found.")
public class CampaignNotFoundException extends CampaignException {
	public CampaignNotFoundException() {}
	
	public CampaignException addErrors(CampaignErrorVo errorVo) {
		setErrors(errorVo);
		return this;
	}
}