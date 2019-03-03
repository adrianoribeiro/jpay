package com.jumia.jpay.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jumia.jpay.model.Card;
import com.jumia.jpay.model.CardStatistics;

public interface CardService {

	public Card save(Card card);

	public List<CardStatistics> findCardCount(Pageable pageable);

	public long cardCount();

	public Card getCardFromAPI(String cardNumber);
	
}
