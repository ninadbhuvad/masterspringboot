package com.ninad.accounts.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
@Schema(name = "AccountsContactInfo", description = "Schema to hold Accounts contact information")
public class AccountsContactInfoDto {

	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;
}
