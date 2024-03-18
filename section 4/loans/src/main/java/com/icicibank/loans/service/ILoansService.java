package com.icicibank.loans.service;

import com.icicibank.loans.dto.LoansDto;

public interface ILoansService {

	void createLoan(String mobileNumber);
	
	LoansDto fetchLoan(String mobileNumber);
	
	boolean updateLoan(LoansDto loansDto);
	
	boolean deleteLoan(String mobileNumber);
	
}
