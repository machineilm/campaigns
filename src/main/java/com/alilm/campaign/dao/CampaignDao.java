package com.alilm.campaign.dao;

import java.util.Map;

import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignVo;

/**
 * 
 * @date 22 Feb 2017 10.25 PM
 * @author jhulfikarali
 *
 */
public interface CampaignDao {

	public Map<Long, CampaignResponseVo> listAllCampaigns();

	public CampaignResponseVo create(CampaignVo campaignVo) throws Exception;
	
}
