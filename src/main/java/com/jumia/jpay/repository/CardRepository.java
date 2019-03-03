package com.jumia.jpay.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jumia.jpay.model.Card;
import com.jumia.jpay.model.CardStatistics;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long>{

	@Query(value = "select new com.jumia.jpay.model.CardStatistics(count(c), c.cardNumber) from Card c group by c.cardNumber")
	public List<CardStatistics> findCardCount(Pageable pageable);
	
}
