package com.alilm.campaign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alilm.campaign.vo.CampaignErrorVo;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.GONE, reason="Invalid input")
public class CampaignExpiredException extends CampaignException {
	
	public CampaignException addErrors(CampaignErrorVo errorVo) {
		setErrors(errorVo);
		return this;
	}
	
}