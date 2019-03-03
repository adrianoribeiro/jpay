package com.jumia.jpay.model;

public class CardStatistics {

	private String cardNumber;
	private Long count;

	public CardStatistics(Long cnt, String cardNumber) {
		this.cardNumber = cardNumber;
		this.count = cnt;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public Long getCount() {
		return count;
	}
}
