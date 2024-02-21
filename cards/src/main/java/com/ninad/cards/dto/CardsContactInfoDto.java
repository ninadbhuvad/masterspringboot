package com.ninad.cards.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "cards")
@Getter
@Setter
@Schema(name = "CardsContactInfo", description = "Schema to hold Cards contact information")
public class CardsContactInfoDto {

	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;
}
