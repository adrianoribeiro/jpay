package com.jumia.jpay.model.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jumia.jpay.model.Card;

public class CardVO {

	private boolean success;
	private Map<String, String> payload;
	
	public static CardVO create(Card card) {
		
		CardVO cardVO = new CardVO();
		
		cardVO.setSuccess(card.isValid());
		cardVO.setPayload(new HashMap<String, String>());
		
		if(card.isValid()) {
			
			cardVO.payload.put("scheme", card.getScheme());
			cardVO.payload.put("type", card.getType().toString());
			if(StringUtils.isNotBlank(card.getBank())) {
				cardVO.payload.put("bank", card.getBank());
			}
		}
		
		return cardVO;
	}
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, String> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, String> payload) {
		this.payload = payload;
	}
}
