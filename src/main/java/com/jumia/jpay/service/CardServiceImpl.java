package com.jumia.jpay.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.jpay.model.Card;
import com.jumia.jpay.model.CardStatistics;
import com.jumia.jpay.model.EnumCardType;
import com.jumia.jpay.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private ApiService serviceApi;
	
	@Override
	public Card getCardFromAPI(String cardNumber) {
		
		String body = serviceApi.getJsonFromAPI(cardNumber);
		
		if(StringUtils.isEmpty(body)) {
			
			return new Card(cardNumber, false);
		}
		
		try {
			Card card = new Card(cardNumber, true);
			transformJsonToObject(card, body);
			
			return card;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
	private void transformJsonToObject(Card card, String json) throws IOException {
		
		JsonNode jsonNodeParent = (new ObjectMapper()).readTree(json);
		JsonNode typeNode = jsonNodeParent.get("type");
		JsonNode schemeNode = jsonNodeParent.get("scheme");
		JsonNode bankNode = jsonNodeParent.at("/bank/name");
		
		card.setBank(bankNode.asText());
		card.setScheme(schemeNode.asText());
		card.setType(EnumCardType.valueOf(typeNode.asText()));
	}	
	
	@Override
	public Card save(Card card) {
		
		return cardRepository.save(card);
	}
	
	@Override
	public List<CardStatistics> findCardCount(Pageable pageable) {
		
		return cardRepository.findCardCount(pageable);
	}
	
	@Override
	public long cardCount() {
		
		return cardRepository.count();
	}
}
