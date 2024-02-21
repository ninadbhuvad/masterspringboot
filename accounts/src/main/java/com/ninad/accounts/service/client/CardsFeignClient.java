package com.ninad.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ninad.accounts.dto.CardsDto;

@FeignClient("CARDS")
public interface CardsFeignClient {

	@GetMapping(value = "/api/fetch", consumes = "application/json")
	public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);
}
