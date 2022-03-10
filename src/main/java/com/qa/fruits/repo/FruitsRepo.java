package com.qa.fruits.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.fruits.domain.Fruits;

@Repository
public interface FruitsRepo extends JpaRepository<Fruits, Integer> {

	
	List<Fruits> findByNameIgnoreCase(String name);
	
	List<Fruits> findByQuantity(Integer quantity);
	
	List<Fruits> findBySeed(boolean seed);
	
}
