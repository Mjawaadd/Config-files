package com.icicibank.loans.dto;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDto(String message, HashMap<String, String> contactDetails, List<String> onCallSupport) {

}
