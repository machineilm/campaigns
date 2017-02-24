package com.alilm.campaign.service;

import java.util.List;

import com.alilm.campaign.vo.CampaignErrorVo;
import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignVo;

/**
 * 
 * @date 22 Feb 2017 10.17 PM
 * @author jhulfikarali
 *
 */
public interface CampaignService {

	public CampaignErrorVo validate(CampaignVo campaignVo);

	public CampaignResponseVo create(CampaignVo campaignVo) throws Exception;
	
	public List<CampaignResponseVo> listAll();
	
	public CampaignResponseVo find(Long partnerId);
	
}
