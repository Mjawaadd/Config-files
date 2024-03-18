package com.icicibank.accounts.mapper;

import com.icicibank.accounts.dto.AccountsDto;
import com.icicibank.accounts.entity.Accounts;

public class AccountsMapper {

//	Entity To Dto
	public static AccountsDto mapToAccountsDto(Accounts accounts,AccountsDto accountsDto) {
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}
	
//	Dto to Entity
	public static Accounts mapToAccounts(Accounts accounts,AccountsDto accountsDto) {
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}
}
