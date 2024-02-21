package com.ninad.accounts.dto;

import com.ninad.accounts.constants.AccountsConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class CustomerDto {

	@NotEmpty(message = "name can not be null or empty.")
	@Size(min = 5, max = 25, message = "name length should be between 5 and 25.")
	@Schema(description = "Name of the customer", example = "Ninad Bhuvad")
	private String name;

	@NotEmpty(message = "email can not be null or empty.")
	@Email(message = "please enter valid email.")
	@Schema(description = "Email address of the customer", example = "ninadbhuvad@gmail.com")
	private String email;

	@Pattern(regexp = "(^$|[0-9]{10})", message = AccountsConstants.VALID_MOBILE_MESSAGE)
	@Schema(description = "Mobile Number of the customer", example = "9876543210")
	private String mobileNumber;

	@Schema(description = "Account details of the Customer")
	private AccountsDto accountsDto;

}
