package com.alilm.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
/**
 * 
 * @date 22 Feb 2017 10.05 PM
 * @author jhulfikarali
 *
 */
public class CampaignRestBootApplication extends SpringBootServletInitializer {
	public static Logger logger = LoggerFactory.getLogger(CampaignRestBootApplication.class);
	 public static void main(String[] args) {
		 	logger.info("Starting campaign application...");
	        SpringApplication.run(CampaignRestBootApplication.class, args);
	 }
}
