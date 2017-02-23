package com.alilm.campaign.dao;

import java.util.Map;

import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignVo;

public interface CampaignDao {

	public Map<Long, CampaignResponseVo> listAllCampaigns();

	public CampaignResponseVo create(CampaignVo campaignVo) throws Exception;
	
}
