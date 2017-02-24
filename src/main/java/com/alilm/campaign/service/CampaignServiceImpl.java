package com.alilm.campaign.service;

import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.UNIQUE_CAMPAIGN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alilm.campaign.dao.CampaignDao;
import com.alilm.campaign.vo.CampaignErrorVo;
import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignVo;

@Service
/**
 * 
 * @date 22 Feb 2017 10.18 PM
 * @author jhulfikarali
 *
 */
public class CampaignServiceImpl implements CampaignService {
	
	public static Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);

	@Autowired
	CampaignDao campaignDao;
	
	@Override
	public CampaignErrorVo validate(CampaignVo campaignVo) {
		logger.debug("Validating campaign " + campaignVo);
		CampaignErrorVo error = new CampaignErrorVo();
		/*
		 * Validation #1: Ensure only one active campaign exists for a partner
		 */
		Map<Long, CampaignResponseVo> campaigns = campaignDao.listAllCampaigns();
		if ( campaigns.get(campaignVo.getPartnerId()) != null) {
			error.getErrors().put(UNIQUE_CAMPAIGN.getCode(), UNIQUE_CAMPAIGN.getMessage());
			logger.debug("Validation error: " + campaignVo);
		}
		return error;
	}

	/** 
	 * 
	 * createCampaign service will accept the campaign JSON and creates it in persistent area
	 * @throws Exception 
	 * 
	 */
	@Override
	public CampaignResponseVo create(CampaignVo campaignVo) throws Exception {
			logger.debug("Creating the campaign: " + campaignVo);
			return campaignDao.create(campaignVo);
	}

	/** 
	 * 
	 * listCampaign service will list all the campaigns in the system which are created so far.
	 * 
	 */
	@Override
	public List<CampaignResponseVo> listAll() {
		logger.debug("Listing all the campaigns - EntryPoint" );
		List<CampaignResponseVo> responses = new ArrayList<CampaignResponseVo>(5);
		for (CampaignResponseVo responseVo : campaignDao.listAllCampaigns().values()) {
			responses.add(responseVo);
		}
		logger.debug("CampaignList: " + responses);
		return responses;
	}

	/**
	 * findCampaign service will provide the campaign which is associated to the requested partner.
	 *
	 */
	@Override
	public CampaignResponseVo find(Long partnerId) {
		logger.debug("Searching for campaign using partnerId >> " + partnerId);
		Map<Long, CampaignResponseVo> campaigns = campaignDao.listAllCampaigns();
		CampaignResponseVo response;
		if ( CollectionUtils.isEmpty(campaigns) || 
				(response =campaigns.get(partnerId)) == null) {
			logger.debug("No campaign found for partnerId >> " + partnerId);
			return null;
		}
		logger.debug("Found campaign: " + response);
		return response;
	}

}
