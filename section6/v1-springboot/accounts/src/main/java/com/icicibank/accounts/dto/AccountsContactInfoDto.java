package com.icicibank.accounts.dto;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "cards")
public record AccountsContactInfoDto(String message, HashMap<String, String> contactDetails, List<String> onCallSupport) {

}
