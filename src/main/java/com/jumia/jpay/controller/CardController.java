package com.jumia.jpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.jpay.model.Card;
import com.jumia.jpay.model.CardStatistics;
import com.jumia.jpay.model.vo.CardVO;
import com.jumia.jpay.model.vo.StatVO;
import com.jumia.jpay.service.CardService;

@RestController
@RequestMapping("/card-scheme")
public class CardController {

	@Autowired
	private CardService cardService;
	
	@GetMapping("/verify/{cardNumber}")
	public CardVO verify(@PathVariable final String cardNumber) {
		
		Card card = cardService.getCardFromAPI(cardNumber);
		cardService.save(card);
		
		return CardVO.create(card);
	}
	
	@GetMapping("/stats")
	public StatVO stat(
			@RequestParam Integer start, 
			@RequestParam Integer limit) {
		
		List<CardStatistics> mapGroupCards = cardService.findCardCount(PageRequest.of(start.intValue()-1, limit));
		Long count = cardService.cardCount();
		
		return StatVO.create(start, limit, count, mapGroupCards);
	}	
}
