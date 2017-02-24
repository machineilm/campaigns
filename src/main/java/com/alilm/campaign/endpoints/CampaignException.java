package com.alilm.campaign.endpoints;

import com.alilm.campaign.vo.CampaignErrorVo;

@SuppressWarnings("serial")
public abstract class CampaignException extends RuntimeException {
	
	public abstract CampaignException addErrors(CampaignErrorVo errorVo);

	public CampaignException() {}
	
	public void setErrors(CampaignErrorVo errorVo) {
		this.errorVo = errorVo;
	}
	
	CampaignErrorVo errorVo;

	public CampaignErrorVo getErrorVo() {
		return errorVo;
	}

	public void setErrorVo(CampaignErrorVo errorVo) {
		this.errorVo = errorVo;
	}
	
}
