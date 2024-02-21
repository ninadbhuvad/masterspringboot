package com.ninad.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ninad.accounts.dto.AccountsDto;
import com.ninad.accounts.dto.CardsDto;
import com.ninad.accounts.dto.CustomerDetailsDto;
import com.ninad.accounts.dto.LoansDto;
import com.ninad.accounts.entity.Accounts;
import com.ninad.accounts.entity.Customer;
import com.ninad.accounts.exception.ResourceNotFoundException;
import com.ninad.accounts.mapper.AccountsMapper;
import com.ninad.accounts.mapper.CustomerMapper;
import com.ninad.accounts.reposiroty.AccountsRepository;
import com.ninad.accounts.reposiroty.CustomerRepository;
import com.ninad.accounts.service.ICustomersService;
import com.ninad.accounts.service.client.CardsFeignClient;
import com.ninad.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;

	/**
	 * @param mobileNumber - Input Mobile Number
	 * @return Customer Details based on a given mobileNumber
	 */
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString()));

		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,
				new CustomerDetailsDto());
		customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

		ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
		customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

		ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
		customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

		return customerDetailsDto;
	}
}
