package com.ninad.accounts.service;

import com.ninad.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

	CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
