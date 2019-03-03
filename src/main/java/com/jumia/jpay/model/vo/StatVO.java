package com.jumia.jpay.model.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jumia.jpay.model.CardStatistics;

public class StatVO {

	private boolean success;
	private Integer start;
	private Integer limit;
	private Long size;
	private Map<String, Long> payload;
	
	public static StatVO create(Integer start, Integer limit, Long size, List<CardStatistics> cardStatistics) {
		
		StatVO statVO = new StatVO();
		statVO.setSuccess(true); //I didn't understand it
		statVO.setStart(start);
		statVO.setLimit(limit);
		statVO.setSize(size);
		
		statVO.setPayload(new HashMap<String, Long>());
		cardStatistics.stream().forEach(element -> {
			statVO.getPayload().put(element.getCardNumber(), element.getCount());
		});
		
		return statVO;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Map<String, Long> getPayload() {
		return payload;
	}
	public void setPayload(Map<String, Long> payload) {
		this.payload = payload;
	}
}
