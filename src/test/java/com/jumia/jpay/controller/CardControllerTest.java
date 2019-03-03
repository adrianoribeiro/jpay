package com.jumia.jpay.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.jpay.model.Card;
import com.jumia.jpay.model.CardStatistics;
import com.jumia.jpay.model.EnumCardType;
import com.jumia.jpay.model.vo.CardVO;
import com.jumia.jpay.model.vo.StatVO;
import com.jumia.jpay.repository.CardRepository;
import com.jumia.jpay.service.CardServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CardControllerTest {

	@InjectMocks
	private CardController cardController;
	
	@Mock
	private CardServiceImpl cardService; 
	
	@Mock
	private CardRepository cardRepository; 	
	
	@Test
	public void verifyTest() throws JsonProcessingException {
		
		Card card = new Card("546479", true);
		card.setBank("BANCO DO BRASIL, S.A.");
		card.setScheme("mastercard");
		card.setType(EnumCardType.credit);
		
		CardVO cardVO = CardVO.create(card);
		
		String expectedString = "{\"success\":true,\"payload\":{\"bank\":\"BANCO DO BRASIL, S.A.\",\"scheme\":\"mastercard\",\"type\":\"credit\"}}";
		String resultString = (new ObjectMapper()).writeValueAsString(cardVO);
		
		assertEquals(expectedString, resultString);
	}

	
	@Test
	public void statTest() throws JsonProcessingException {
		
		List<CardStatistics> cardStatisticsList = new ArrayList<>();
		cardStatisticsList.add(new CardStatistics(5l, "111111"));
		cardStatisticsList.add(new CardStatistics(2l, "515590"));
		
		Mockito.when(cardService.findCardCount(PageRequest.of(0, 3))).thenReturn(cardStatisticsList);
		Mockito.when(cardRepository.findCardCount(PageRequest.of(0, 3))).thenReturn(cardStatisticsList);
		
		Mockito.when(cardService.cardCount()).thenReturn(10l);
		Mockito.when(cardRepository.count()).thenReturn(10l);
		
		StatVO stat = cardController.stat(1,  3);

		String expectedString = "{\"success\":true,\"start\":1,\"limit\":3,\"size\":10,\"payload\":{\"111111\":5,\"515590\":2}}";
		String resultString = (new ObjectMapper()).writeValueAsString(stat);
		
		assertEquals(expectedString, resultString);
	}

}
