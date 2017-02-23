package com.alilm.campaign.endpoints;

import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.GENERAL;
import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.NOT_FOUND;
import static com.alilm.campaign.helper.CampaignMessagesHelper.Errors.INVALID;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Response findCampaign(@PathVariable("partner_id") String partnerId) {
		try {
			Long adId = Long.valueOf(partnerId);
			CampaignResponseVo campaign = campaignService.find(adId);
			if ( campaign == null ) {
				return generateCampaignError(NOT_FOUND, 404); // 404 = Not found
			}
			return Response.ok(200).entity(campaign).build();
		} catch (NumberFormatException ex) {
			return generateCampaignError(INVALID, 412); // 412 - Precondition Failed
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
	public Response listAllCampaigns() {
		return Response.ok().entity(campaignService.listAllCampaigns()).build();
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
	public Response createCampaign(@RequestBody CampaignVo campaignVo) {
		
		/* 
		 * Step 1: validate the campaign for creation
		 */
		CampaignErrorVo errVo = campaignService.validate(campaignVo);
		if ( errVo != null && MapUtils.isNotEmpty(errVo.getErrors()) ) {
			return Response.status(404).entity(errVo.getErrors()).build();
		}
		
		/*
		 * Step 2: Create the campaign and verify if everything worked as expected
		 */
		CampaignResponseVo  campaignResponseVo = campaignService.create(campaignVo);
		if ( campaignResponseVo == null  ) {
			return generateCampaignError(GENERAL, 417); // 417 - Expectation Failed
		}
		
		/*
		 * Step 3: Respond the client with created camapign information
		 */
		return Response.ok(201).entity(campaignResponseVo).build();
	}

	/*
	 * Helper method for generating errors
	 * @param campaignError
	 * @param statusCode
	 * @return
	 */
	private Response generateCampaignError(Errors campaignError, int statusCode) {
		CampaignErrorVo errVo;
		errVo = new CampaignErrorVo();
		errVo.getErrors().put(campaignError.getCode(), campaignError.getMessage());
		return Response.status(statusCode).entity(errVo.getErrors()).build();
	}

}
