package com.icicibank.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.icicibank.accounts.constants.AccountsConstants;
import com.icicibank.accounts.dto.AccountsDto;
import com.icicibank.accounts.dto.CustomerDto;
import com.icicibank.accounts.entity.Accounts;
import com.icicibank.accounts.entity.Customer;
import com.icicibank.accounts.exception.CustomerAlreadyExistsException;
import com.icicibank.accounts.exception.ResourceNotFoundException;
import com.icicibank.accounts.mapper.AccountsMapper;
import com.icicibank.accounts.mapper.CustomerMapper;
import com.icicibank.accounts.repository.AccountsRepository;
import com.icicibank.accounts.repository.CustomerRepository;
import com.icicibank.accounts.service.IAccountsService;

@Service
public class AccountsServiceImpl implements IAccountsService{

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	
	
	
	public AccountsServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
		super();
		this.accountsRepository = accountsRepository;
		this.customerRepository = customerRepository;
	}



	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
		}
//		implemented Auditing to update the fields automatically
//		customer.setCreatedAt(LocalDateTime.now());
//		customer.setCreatedBy("Anonymous");
		Customer savedCustomer = customerRepository.save(customer);

		accountsRepository.save(createNewAccount(savedCustomer));

	}
	
	
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccounts = new Accounts();
		newAccounts.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		
		newAccounts.setAccountNumber(randomAccNumber);
		newAccounts.setAccountType(AccountsConstants.SAVINGS);
		newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
//		implemented Auditing to update the fields automatically
//		newAccounts.setCreatedAt(LocalDateTime.now());
//		newAccounts.setCreatedBy("Anonymous");
		return newAccounts;
		
	}



	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
			()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)	
				);
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())	
				);
		
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto(), customer);
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		
		return customerDto;
	}



	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if(accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
					() -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
					);
			
			AccountsMapper.mapToAccounts(accounts, accountsDto);
			accounts = accountsRepository.save(accounts);
			
			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId).orElseThrow(
					() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
					);
			CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}
		
		
		return isUpdated;
	}



	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)	
					);
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		
		return true;
	}
	

}
