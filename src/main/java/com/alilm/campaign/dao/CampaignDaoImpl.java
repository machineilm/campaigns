package com.alilm.campaign.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignResponseVo.CampaignResponseVoBuilder;
import com.alilm.campaign.vo.CampaignVo;

@Repository
public class CampaignDaoImpl implements CampaignDao {

	public static Long id = 0l;
	
	public static Map<Long, CampaignResponseVo> campaigns = new HashMap<Long, CampaignResponseVo>(50);
	
	/*  listCampaign - List all the campaigns from storage system. */
	@Override
	public Map<Long, CampaignResponseVo> listAllCampaigns() {
		return campaigns;
	}

	/* createCampaign  - Persist the campaign information in storage area */
	@Override
	public CampaignResponseVo create(CampaignVo campaignVo) throws Exception {
		if ( campaigns.get(campaignVo.getPartnerId()) != null ) throw new Exception();
		
		CampaignResponseVo responseVo = new CampaignResponseVoBuilder()
				.setId(generateId()).setAdVo(campaignVo).build();
		campaigns.put(campaignVo.getPartnerId(), responseVo);
		return responseVo;
	}

	private static Long generateId() {
		id = id+1;
		return id;
	}

}
