package com.icicibank.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icicibank.accounts.constants.AccountsConstants;
import com.icicibank.accounts.dto.AccountsContactInfoDto;
import com.icicibank.accounts.dto.CustomerDto;
import com.icicibank.accounts.dto.ResponseDto;
import com.icicibank.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "CRUD REST APIs for Accounts",description = "CRUD REST APIs in ICICI BANK to CREATE,UPDATE,FETCH and DELETE")
public class AccountsController {
	
	private final IAccountsService iAccountsService;
	
	@Value("${build.version}")
	private String buildVersion;
	
	public AccountsController(IAccountsService iAccountsService) {
		super();
		this.iAccountsService = iAccountsService;
	}

	@Autowired
	private Environment environment;
	
	@Autowired
	private AccountsContactInfoDto accountContactInfoDto;


	@Operation(
			summary = "Create Account REST API",
			description = "REST API to create Accounts and Customer in ICICI bank"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP status CREATED"
			)
	
	@PostMapping()
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountsService.createAccount(customerDto);		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}
	
	@Operation(
			summary = "FETCH Account Details REST API",
			description = "REST API to Fetch Accounts and Customer details based on Mobile Number"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP status OK"
			)
	@GetMapping()
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam  @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")String  mobileNumber){
		CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(customerDto);
		
	}
	
	
	@Operation(
			summary = "UPDATE Account Details REST API",
			description = "REST API to update Accounts and Customer details based on account Number"
			)
	
	@ApiResponses(
		{
		@ApiResponse(
				responseCode = "200",
				description = "HTTP status OK"
				),
		@ApiResponse(
				responseCode = "500",
				description = "HTTP status Internal Server Error"
				)
		}
		
		)	
	@PutMapping()
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
		boolean isUpdated = iAccountsService.updateAccount(customerDto);
		if(isUpdated) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_201));
		}else {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
		}
		
	}
	
	@Operation(
			summary = "DELETE Account & Customer Details REST API",
			description = "REST API to delete Accounts and Customer details based on mobile Number"
			)
	
	@ApiResponses(
		{
		@ApiResponse(
				responseCode = "200",
				description = "HTTP status OK"
				),
		@ApiResponse(
				responseCode = "500",
				description = "HTTP status Internal Server Error"
				)
		}
		
		)
	@DeleteMapping()
	public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")String mobileNumber){
		boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
		
		if(isDeleted) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
		}
	}
	
	
	@Operation(
			summary = "Get Build Information",
			description = "Rest API to get Build Version"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP status OK"
			)
	@GetMapping("/build-version")
	public ResponseEntity<String> getJavaVersion(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(buildVersion);
	}

	
	@Operation(
			summary = "Get JAVA Information",
			description = "Rest API to get JAVA Version Insalled location"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP status OK"
			)
	@GetMapping("/java-version")
	public ResponseEntity<String> getBuildVersion(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(environment.getProperty("JAVA_HOME"));
	}
	
	
	@Operation(
			summary = "Get Contact Info",
			description = "Contact Info details that can be reached out in case of any issues"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP status OK"
			)
	@GetMapping("/contact-info")
	public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(accountContactInfoDto);
	}
}
