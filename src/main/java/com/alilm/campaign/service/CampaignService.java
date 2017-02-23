package com.alilm.campaign.service;

import java.util.Collection;

import com.alilm.campaign.vo.CampaignErrorVo;
import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignVo;

public interface CampaignService {

	public CampaignErrorVo validate(CampaignVo campaignVo);

	public CampaignResponseVo create(CampaignVo campaignVo);
	
	public Collection<CampaignResponseVo> listAllCampaigns();
	
	public CampaignResponseVo find(Long partnerId);
	
}
