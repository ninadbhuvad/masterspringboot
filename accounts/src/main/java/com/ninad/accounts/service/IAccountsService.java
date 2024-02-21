package com.ninad.accounts.service;

import com.ninad.accounts.dto.CustomerDto;

public interface IAccountsService {
	/**
	 * 
	 * @param customerDto - CustomerDto Object
	 */
	void createAccount(CustomerDto customerDto);

	/**
	 * 
	 * @param mobileNumber
	 * @return CustomerDto
	 */
	CustomerDto fetchAccount(String mobileNumber);

	/**
	 * 
	 * @param customerDto
	 * @return boolean if account update successful
	 */
	boolean updateAccount(CustomerDto customerDto);

	/**
	 * 
	 * @param mobileNumber
	 * @return if data deleted successfully
	 */
	boolean deleteAccount(String mobileNumber);

}
