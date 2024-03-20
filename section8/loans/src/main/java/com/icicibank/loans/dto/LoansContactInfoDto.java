package com.icicibank.loans.dto;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans")
public class LoansContactInfoDto {

	private String message;  
	private HashMap<String, String> contactDetails;
	private	List<String> onCallSupport;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HashMap<String, String> getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(HashMap<String, String> contactDetails) {
		this.contactDetails = contactDetails;
	}
	public List<String> getOnCallSupport() {
		return onCallSupport;
	}
	public void setOnCallSupport(List<String> onCallSupport) {
		this.onCallSupport = onCallSupport;
	}
	@Override
	public String toString() {
		return "LoansContactInfoDto [message=" + message + ", contactDetails=" + contactDetails + ", onCallSupport="
				+ onCallSupport + "]";
	}
	
	
}
