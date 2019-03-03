package com.jumia.jpay.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cardNumber;
	private String scheme;
	
	@Enumerated(EnumType.STRING)
	private EnumCardType type;
	private String bank;
	private boolean valid;
	
	public Card(String cardNumber, boolean valid) {
		this.setCardNumber(cardNumber);
		this.setValid(valid);
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public EnumCardType getType() {
		return type;
	}
	public void setType(EnumCardType type) {
		this.type = type;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
