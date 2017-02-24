package com.alilm.campaign.endpoints;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CampaignEPTest {

	@Autowired
    private MockMvc mvc;

	@Test
    public void testBooting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/ad").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

	@Test
    public void testCampaignCreation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/ad")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content("{   \"partnerId\":\"523523\",   \"duration\":\"15\",   \"adContent\":\"Exciting offer, click here\"}"))
                .andExpect(status().is(200));
    }
	
	@Test
    public void testPartnerCampaign() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/ad").pathInfo("/523523").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
	public void testCampaigns() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/ad").accept(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk());
	}

	@Test
    public void testDuplicateCampaign() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/ad")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content("{   \"partnerId\":\"523523\",   \"duration\":\"15\",   \"adContent\":\"Exciting offer, click here\"}"))
                .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
    }
	
}
