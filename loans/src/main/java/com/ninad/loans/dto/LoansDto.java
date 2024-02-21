package com.ninad.loans.dto;

import com.ninad.loans.constants.LoansConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loan", description = "Schema to hold loan information")
public class LoansDto {

	@Schema(description = "Customer's mobile number", example = "9876543210")
	@Pattern(regexp = "^$|[0-9]{10}", message = LoansConstants.VALID_MOBILE_MESSAGE)
	private String mobileNumber;

	@Schema(description = "Loan number", example = "987654321098")
	@Pattern(regexp = "^$|[0-9]{12}", message = LoansConstants.VALID_MOBILE_MESSAGE)
	@NotEmpty(message = "Loan number can not be null or empty")
	private String loanNumber;

	@Schema(description = "Loan Type", example = "Home Loan")
	@NotEmpty(message = "Loan type can not be null or empty")
	private String loanType;

	@Schema(description = "Total loan amount", example = "10000")
	@Positive(message = "Loan amount should be greater than zero")
	private int totalLoan;

	@Schema(description = "Amount paid", example = "100")
	@PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
	private int amountPaid;

	@Schema(description = "Outstanding amount", example = "9900")
	@PositiveOrZero(message = "Total outstanding amount against a loan")
	private int outstandingAmount;
}
