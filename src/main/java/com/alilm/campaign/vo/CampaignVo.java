package com.alilm.campaign.vo;

public class CampaignVo {

	private Long partnerId;
	private Long duration;
	private String adContent;
	
	public CampaignVo(Long partnerId,	Long duration, 	String adContent){
		this.partnerId = partnerId;
		this.duration = duration;
		this.adContent = adContent;
	}
	
	public CampaignVo(){}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

}
