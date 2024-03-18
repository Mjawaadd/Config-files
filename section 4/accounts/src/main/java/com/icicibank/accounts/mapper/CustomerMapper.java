package com.icicibank.accounts.mapper;

import com.icicibank.accounts.dto.CustomerDto;
import com.icicibank.accounts.entity.Customer;

public class CustomerMapper {
	
	public static CustomerDto mapToCustomerDto(CustomerDto customerDto, Customer customer) {
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setName(customer.getName());
		return customerDto;
		
	}
	public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setName(customerDto.getName());
		return customer;
		
	}
	

}
