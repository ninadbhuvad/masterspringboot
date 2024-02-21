package com.ninad.loans.service;

import com.ninad.loans.dto.LoansDto;

public interface ILoansService {

	void createLoan(String mobileNumber);

	LoansDto fetchLoan(String mobileNumber);

	boolean updateLoan(LoansDto loansDto);

	boolean deleteLoan(String mobileNumber);
}
