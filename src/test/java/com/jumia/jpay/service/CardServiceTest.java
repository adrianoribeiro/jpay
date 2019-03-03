package com.jumia.jpay.service;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jumia.jpay.model.Card;
import com.jumia.jpay.model.EnumCardType;
import com.jumia.jpay.repository.CardRepository;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

	@InjectMocks
	private CardServiceImpl cardService; 
	
	@Mock
	private ApiServiceImpl apiService;
	
	@Mock
	private CardRepository cardRepository;
	
	private static final String jsonSuccess = "{\"number\":{},\"scheme\":\"mastercard\",\"type\":\"credit\",\"brand\":\"Platinium\",\"country\":{\"numeric\":\"076\",\"alpha2\":\"BR\",\"name\":\"Brazil\",\"emoji\":\"߇§߇·\",\"currency\":\"BRL\",\"latitude\":-10,\"longitude\":-55},\"bank\":{\"name\":\"BANCO SANTANDER (BRASIL), S.A.\",\"url\":\"www.ri.santander.com.br\",\"phone\":\"55 (11) 3553 3300\"}}";
	private static final String jsonFailure = StringUtils.EMPTY;
	@Test
	public void getCardFromAPISuccessTest() {
		
		Mockito.when(apiService.getJsonFromAPI("515590")).thenReturn(jsonSuccess);
		
		Card card = cardService.getCardFromAPI("515590");
		
		assertEquals("515590", card.getCardNumber());
		assertEquals("mastercard", card.getScheme());
		assertEquals(EnumCardType.credit, card.getType());
		assertEquals("BANCO SANTANDER (BRASIL), S.A.", card.getBank());
		assertEquals(true, card.isValid());
	}	
	
	@Test
	public void getCardFromAPIFailureTest() {
		
		Mockito.when(apiService.getJsonFromAPI("111111")).thenReturn(jsonFailure);
		
		Card card = cardService.getCardFromAPI("111111");
		
		assertEquals("111111", card.getCardNumber());
		assertEquals(null, card.getScheme());
		assertEquals(null, card.getType());
		assertEquals(null, card.getBank());
		assertEquals(false, card.isValid());
	}
}
