package com.icicibank.cards.dto;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(name = "Cards",
description = "Schema to hold Card information"
)
public class CardsDto {

@NotEmpty(message = "Mobile Number can not be a null or empty")
@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
@Schema(
    description = "Mobile Number of Customer", example = "4354437687"
)
private String mobileNumber;

@NotEmpty(message = "Card Number can not be a null or empty")
@Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
@Schema(
    description = "Card Number of the customer", example = "100646930341"
)
private String cardNumber;

@NotEmpty(message = "CardType can not be a null or empty")
@Schema(
    description = "Type of the card", example = "Credit Card"
)
private String cardType;

@Positive(message = "Total card limit should be greater than zero")
@Schema(
    description = "Total amount limit available against a card", example = "100000"
)
private int totalLimit;

@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
@Schema(
    description = "Total amount used by a Customer", example = "1000"
)
private int amountUsed;

@PositiveOrZero(message = "Total available amount should be equal or greater than zero")
@Schema(
    description = "Total available amount against a card", example = "90000"
)
private int availableAmount;

public String getMobileNumber() {
	return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public String getCardNumber() {
	return cardNumber;
}

public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
}

public String getCardType() {
	return cardType;
}

public void setCardType(String cardType) {
	this.cardType = cardType;
}

public int getTotalLimit() {
	return totalLimit;
}

public void setTotalLimit(int totalLimit) {
	this.totalLimit = totalLimit;
}

public int getAmountUsed() {
	return amountUsed;
}

public void setAmountUsed(int amountUsed) {
	this.amountUsed = amountUsed;
}

public int getAvailableAmount() {
	return availableAmount;
}

public void setAvailableAmount(int availableAmount) {
	this.availableAmount = availableAmount;
}

@Override
public int hashCode() {
	return Objects.hash(amountUsed, availableAmount, cardNumber, cardType, mobileNumber, totalLimit);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CardsDto other = (CardsDto) obj;
	return amountUsed == other.amountUsed && availableAmount == other.availableAmount
			&& Objects.equals(cardNumber, other.cardNumber) && Objects.equals(cardType, other.cardType)
			&& Objects.equals(mobileNumber, other.mobileNumber) && totalLimit == other.totalLimit;
}

@Override
public String toString() {
	return "CardsDto [mobileNumber=" + mobileNumber + ", cardNumber=" + cardNumber + ", cardType=" + cardType
			+ ", totalLimit=" + totalLimit + ", amountUsed=" + amountUsed + ", availableAmount=" + availableAmount
			+ "]";
}



}
