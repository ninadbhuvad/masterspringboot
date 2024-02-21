package com.ninad.loans.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
@Schema(name = "LoansContactInfo", description = "Schema to hold Loans contact information")
public class LoansContactInfoDto {

	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;
}
