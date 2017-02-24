package com.alilm.campaign.helper;

public interface CampaignMessagesHelper {

	public static String ACTION_SUCCESS = "Adverstisement is created successfully.";
	
	public enum Errors {
		
		GENERAL("C0001", "Apologies, system didn't work as anticipated right now, please try again later."),
		UNIQUE_CAMPAIGN("C0002", "Lovely! Currently we support only one campaign for a partner. We value your interest, please check later."),
		NOT_FOUND("C0404", "We couldn't locate any campaign for this partner at this point of time."),
		INVALID("C0400", "We couldn't locate any campaign for this partner at this point of time."),
		CAMPAIGN_EXPIRED("C0410", "We couldn't locate any campaign for this partner at this point of time.");
		
		Errors(String code, String message) {
			this.code = code;
			this.message = message;
		};
		
		String code;
		String message;
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
}
