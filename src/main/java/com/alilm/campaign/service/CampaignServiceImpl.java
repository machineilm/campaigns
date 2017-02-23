package com.alilm.campaign.service;

import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.UNIQUE_CAMPAIGN;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alilm.campaign.dao.CampaignDao;
import com.alilm.campaign.vo.CampaignErrorVo;
import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignVo;

@Service
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	CampaignDao campaignDao;
	
	@Override
	public CampaignErrorVo validate(CampaignVo campaignVo) {
		CampaignErrorVo error = new CampaignErrorVo();
		/*
		 * Validation #1: Ensure only one active campaign exists for a partner
		 */
		Map<Long, CampaignResponseVo> campaigns = campaignDao.listAllCampaigns();
		if ( campaigns.get(campaignVo.getPartnerId()) != null) {
			error.getErrors().put(UNIQUE_CAMPAIGN.getCode(), UNIQUE_CAMPAIGN.getMessage());
		}
		return error;
	}

	/** 
	 * 
	 * createCampaign service will accept the campaign JSON and creates it in persistent area
	 * 
	 */
	@Override
	public CampaignResponseVo create(CampaignVo campaignVo) {
		try { 
			return campaignDao.create(campaignVo);
		} catch (Exception e) {
			return null;
		}
	}

	/** 
	 * 
	 * listCampaign service will list all the campaigns in the system which are created so far.
	 * 
	 */
	@Override
	public Collection<CampaignResponseVo> listAllCampaigns() {
		return campaignDao.listAllCampaigns().values();
	}

	/**
	 * findCampaign service will provide the campaign which is associated to the requested partner.
	 *
	 */
	@Override
	public CampaignResponseVo find(Long partnerId) {
		Map<Long, CampaignResponseVo> campaigns = campaignDao.listAllCampaigns();
		CampaignResponseVo response;
		if ( CollectionUtils.isEmpty(campaigns) || 
				(response =campaigns.get(partnerId)) == null) {
			return null;
		}
		
		return response;
	}

}
