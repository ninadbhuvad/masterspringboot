package com.ninad.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ninad.accounts.constants.AccountsConstants;
import com.ninad.accounts.dto.AccountsDto;
import com.ninad.accounts.dto.CustomerDto;
import com.ninad.accounts.entity.Accounts;
import com.ninad.accounts.entity.Customer;
import com.ninad.accounts.exception.CustomerAlreadyExistsException;
import com.ninad.accounts.exception.ResourceNotFoundException;
import com.ninad.accounts.mapper.AccountsMapper;
import com.ninad.accounts.mapper.CustomerMapper;
import com.ninad.accounts.reposiroty.AccountsRepository;
import com.ninad.accounts.reposiroty.CustomerRepository;
import com.ninad.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountsRepository accountsRepository;

	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> checkMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if (checkMobileNumber.isPresent())
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
		customerRepository.save(customer);
		Accounts accounts = createNewAccount(customer);
		accountsRepository.save(accounts);
	}

	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setId(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Accounts", "Customer Number", customer.getId().toString()));

		AccountsDto accountDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(accountDto);

		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
							accountsDto.getAccountNumber().toString()));
			AccountsMapper.mapToAccounts(accountsDto, accounts);
			accounts = accountsRepository.save(accounts);

			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));
			CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		accountsRepository.deleteByCustomerId(customer.getId());
		customerRepository.deleteById(customer.getId());
		return true;
	}
}
