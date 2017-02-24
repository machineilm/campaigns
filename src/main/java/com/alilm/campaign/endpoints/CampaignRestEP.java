package com.alilm.campaign.endpoints;

import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.GENERAL;
import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.INVALID;
import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.NOT_FOUND;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alilm.campaign.exception.CampaignExceptionFactory;
import com.alilm.campaign.helper.CampaignMessagesHelper.Errors;
import com.alilm.campaign.helper.MapUtils;
import com.alilm.campaign.service.CampaignService;
import com.alilm.campaign.vo.CampaignErrorVo;
import com.alilm.campaign.vo.CampaignResponseVo;
import com.alilm.campaign.vo.CampaignVo;

@RestController
@RequestMapping("/ad")
/**
 * 
 * Rest endpoint or Controller for Campaign services. This EP expose 3 actions:
 * 1. List of all campaigns (GET)
 * 2. Create a new campaign (POST)
 * 3. Locate a campaign (GET)
 * 
 * @date 22 Feb 2017 10.15 PM
 * @author jhulfikarali
 *
 */
public class CampaignRestEP {

	public static Logger logger = LoggerFactory.getLogger(CampaignRestEP.class);
	
	@Autowired
	private CampaignService campaignService;
	
	/**
	 * findCampaign REST service will provide the campaign which is associated to the requested partner.
	 * 
	 * @url http://<host>:<port>/ad/$partner_id
	 * @method GET
	 * 
	 * @param partnerId
	 * @return
	 */
	@RequestMapping(method=GET,path="/{partner_id}", produces=APPLICATION_JSON,consumes=APPLICATION_JSON)
	public CampaignResponseVo find(@PathVariable("partner_id") String partnerId) {
		try {
			logger.debug("Searching campaign for the partner >> " + partnerId);
			Long adId = Long.valueOf(partnerId);
			CampaignResponseVo campaign = campaignService.find(adId);
			if ( campaign == null ) {
				logger.debug("Campaign not found (404)");
				CampaignErrorVo errVo = generateCampaignError(NOT_FOUND, HttpStatus.NOT_FOUND.value());
				throw CampaignExceptionFactory.get(HttpStatus.NOT_FOUND.value()).addErrors(errVo);
			}
			return campaign;
		} catch (NumberFormatException ex) {
			logger.debug("Invalid input");
			CampaignErrorVo errVo = generateCampaignError(INVALID, HttpStatus.EXPECTATION_FAILED.value());
			throw CampaignExceptionFactory.get(HttpStatus.EXPECTATION_FAILED.value()).addErrors(errVo);
		}
	}
	
	/** 
	 * 
	 * listCampaign service will list all the campaigns in the system which are created so far.
	 * 
	 * @url http://<host>:<port>/ad
	 * @method GET
	 * 
	 */
	@RequestMapping(method=GET,produces=APPLICATION_JSON)
	public List<CampaignResponseVo> listAll() {
		logger.debug("List all the campaigns in the system.");
		return campaignService.listAll();
	}
	
	/** 
	 * 
	 * createCampaign rest service will accept the campaign JSON and creates it in persistent area
	 * 
	 * @url http://<host>:<port>/ad
	 * @method POST
	 * 
	 * @param campaignVo
	 * @return
	 */
	@RequestMapping(method=POST,produces=APPLICATION_JSON,consumes=APPLICATION_JSON)
	public CampaignResponseVo create(@RequestBody CampaignVo campaignVo) {
		logger.debug("create campaign service initiated");
		/* 
		 * Step 1: validate the campaign for creation
		 */
		CampaignErrorVo errVo = campaignService.validate(campaignVo);
		if ( errVo != null && MapUtils.isNotEmpty(errVo.getErrors()) ) {
			throw CampaignExceptionFactory.get(HttpStatus.NOT_ACCEPTABLE.value()).addErrors(errVo);
		}
		
		/*
		 * Step 2: Create the campaign and verify if everything worked as expected
		 */
		CampaignResponseVo  campaignResponseVo = null;
		try {
			campaignResponseVo = campaignService.create(campaignVo);
		} catch (Exception ex) {
			errVo = generateCampaignError(GENERAL, HttpStatus.EXPECTATION_FAILED.value());
			throw CampaignExceptionFactory.get(HttpStatus.EXPECTATION_FAILED.value()).addErrors(errVo);
		}
		
		/*
		 * Step 3: Respond the client with created campaign information
		 */
		logger.debug("Created campaign: " +  campaignResponseVo);
		return campaignResponseVo;
	}
	
	/*
	 * Helper method for generating errors
	 * @param campaignError
	 * @param statusCode
	 * @return
	 */
	private CampaignErrorVo generateCampaignError(Errors campaignError, int statusCode) {
		CampaignErrorVo errVo;
		errVo = new CampaignErrorVo();
		errVo.getErrors().put(campaignError.getCode(), campaignError.getMessage());
		return errVo;
	}

}
