package com.icicibank.accounts.dto;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
		name = "Customer",
		description = "Schema to hold Customer and Account Information"
		)
public class CustomerDto {

	@Schema(description = "Name of the Customer", example = "Mohammed Jawaad")
	@NotEmpty(message = "Name can not be null or empty")
	@Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
	private String name;
	
	@Schema(description = "Email address of the Customer", example = "Mdjawad678@gmail.com"	)
	@NotEmpty(message = "Email address can not be null or empty")
	@Email(message = " Email address should be a valid value")
	private String email;
	
	@Schema(description = "Name of the Customer", example = "9916403863")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
	private String mobileNumber;
	
	@Schema(description = "Accounts Details of the Customer")
	private AccountsDto accountsDto;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public AccountsDto getAccountsDto() {
		return accountsDto;
	}
	public void setAccountsDto(AccountsDto accountsDto) {
		this.accountsDto = accountsDto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountsDto, email, mobileNumber, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDto other = (CustomerDto) obj;
		return Objects.equals(accountsDto, other.accountsDto) && Objects.equals(email, other.email)
				&& Objects.equals(mobileNumber, other.mobileNumber) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", accountsDto="
				+ accountsDto + "]";
	}
	
	
	
	
}
