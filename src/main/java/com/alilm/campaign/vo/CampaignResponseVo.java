package com.alilm.campaign.vo;

import com.alilm.campaign.helper.CampaignUtils;

public class CampaignResponseVo {

	public static class CampaignResponseVoBuilder {
		private Long id;
		private String message;
		private CampaignVo adVo;
		
		public CampaignResponseVoBuilder() {}
		
		public CampaignResponseVoBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		public CampaignResponseVoBuilder setMessage(String message) {
			this.message = message;
			return this;
		}
		public CampaignResponseVoBuilder setAdVo(CampaignVo adVo) {
			this.adVo = adVo;
			this.adVo.setExpiresOn(CampaignUtils.getExpirationDatetime(adVo.getDuration()));
			return this;
		}
		
		public CampaignResponseVo build() {
			return new CampaignResponseVo(this.id, this.message,this.adVo);
		}
	}
	
	private Long id;
	private String message;
	private CampaignVo adVo;

	private CampaignResponseVo(Long id, String msg, CampaignVo adVo) {
		this.id = id;
		this.message = msg;
		this.adVo = adVo;
	}
	
	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public CampaignVo getAdVo() {
		return adVo;
	}

}
