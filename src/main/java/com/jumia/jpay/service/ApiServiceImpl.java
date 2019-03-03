package com.jumia.jpay.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceImpl implements ApiService {

	@Value("${url.api}")
	private String urlApi;
	
	@Override
	public String getJsonFromAPI(String cardNumber) {
		
		RestTemplate restTemplate = new RestTemplate(); 
		String url = urlApi+cardNumber;
		ResponseEntity<String> response = null;
		
		try {
			response = restTemplate.getForEntity(url, String.class);
		} catch (HttpClientErrorException httpE) {
			return StringUtils.EMPTY;
		}
		
		return response.getBody();
	}
}
