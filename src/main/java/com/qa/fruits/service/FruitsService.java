package com.qa.fruits.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.fruits.domain.Fruits;
import com.qa.fruits.repo.FruitsRepo;



@Service
public class FruitsService {

	
    private FruitsRepo repo;
    
    @Autowired
    public FruitsService(FruitsRepo repo) {
    	super();
    	this.repo=repo;
    }
    
	
	public Fruits createFruits(Fruits f) {
		Fruits created = this.repo.save(f);
		return created;
	}

	
	
    public List<Fruits> getAllFru() {
    	return this.repo.findAll();
	
    }

    public Fruits getFru(Integer id) {
    	Optional<Fruits> found = this.repo.findById(id);
    	return found.get();
    }
    
    
    public List<Fruits> getAllFruByQuantity(Integer quantity) {
    	List<Fruits> found = this.repo.findByQuantity(quantity);
    	return found;
    }
    
    public List<Fruits> getFruByName(String name) {
    	List<Fruits> found = this.repo.findByNameIgnoreCase(name);
    	return found;
    }
    
    public List<Fruits> getFruBySeed(boolean seed) {
    	List<Fruits> found = this.repo.findBySeed(seed);
    	return found;
    }
    
    public Fruits replaceFruits(Integer id, Fruits newFruits) {
         Fruits existing = this.repo.findById(id).get();
         existing.setName(newFruits.getName());
         existing.setQuantity(newFruits.getQuantity());
         existing.setSeed(newFruits.getSeed());
         Fruits updated = this.repo.save(existing);
         return updated; 
         
    }
    	
    
    
   public void removeFruits(@PathVariable Integer id) {
	   this.repo.deleteById(id);
   }
	   
   }