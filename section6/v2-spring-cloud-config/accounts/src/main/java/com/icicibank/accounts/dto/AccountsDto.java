package com.icicibank.accounts.dto;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


@Schema(
		name = "Accounts",
		description = "Schema to hold Account Information"
		)
public class AccountsDto {
	
	@Schema(description = "Name of the Accounts")
	@NotEmpty(message = "Account number can not be not or empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "AccountNumner must be 10 digits")
	private Long accountNumber;
	
	@Schema(description = "type of the Accounts", example = "SAVINGS")
	@NotEmpty(message = "Account type can not be not or empty")
	private String accountType;
	
	@Schema(description = "Address of the Branch")
	@NotEmpty(message = "BranchAddress can not be not or empty")
	private String branchAddress;
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, accountType, branchAddress);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountsDto other = (AccountsDto) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(accountType, other.accountType)
				&& Objects.equals(branchAddress, other.branchAddress);
	}
	@Override
	public String toString() {
		return "AccountsDto [accountNumber=" + accountNumber + ", accountType=" + accountType + ", branchAddress="
				+ branchAddress + "]";
	}
	
	
	
	
	
	

}
