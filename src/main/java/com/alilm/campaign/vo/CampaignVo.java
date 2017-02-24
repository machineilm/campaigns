package com.alilm.campaign.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CampaignVo {

	private Long partnerId;
	private Long duration;
	private String adContent;
	@JsonIgnore
	private Date expiresOn;
	
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

	public Date getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}

	@JsonIgnore
	public boolean isExpired() {
		return getExpiresOn().before(new Date());
	}

}
