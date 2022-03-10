package com.qa.fruits.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.fruits.domain.Fruits;
import com.qa.fruits.service.FruitsService;



@RestController
public class FruitsController {

	
	
    private FruitsService service;
    
    @Autowired
    public FruitsController(FruitsService service) {
    	super();
    	this.service=service;
    	
    }

	
	
	
	
	@PostMapping("/create")
	public ResponseEntity<Fruits> createFruits(@RequestBody Fruits f) 
	{
		Fruits created = this.service.createFruits(f);
		ResponseEntity<Fruits> response = new ResponseEntity<Fruits>(created, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Fruits>> getAllfru() {
		return ResponseEntity.ok(this.service.getAllFru());
		
	}
	
	@GetMapping("/get/{id}")
	public Fruits getFruits(@PathVariable Integer id) {
		return this.service.getFru(id);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Fruits> replaceFruits(@PathVariable Integer id, @RequestBody Fruits newFruits) {
		Fruits body = this.service.replaceFruits(id, newFruits);
		ResponseEntity<Fruits> response = new ResponseEntity<Fruits>(body, HttpStatus.ACCEPTED);
		return response;
		
		}
	
	@DeleteMapping("/remove/{id}") 
	public ResponseEntity<?> removeFruits(@PathVariable Integer id) {
		this.service.removeFruits(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		
	
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Fruits>> getFruitsByName(@PathVariable String name) {
	List<Fruits> found = this.service.getFruByName(name);
	return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getbyQuantity/{quantity}")
	public ResponseEntity<List<Fruits>> getFruitsByQuantity(@PathVariable Integer quantity) {
		List<Fruits> found = this.service.getAllFruByQuantity(quantity);
		return ResponseEntity.ok(found);
	
	}
	
	@GetMapping("/getbySeed/{seed}")
	public ResponseEntity<List<Fruits>> getFruitsBySeed(@PathVariable boolean seed) {
		List<Fruits> found = this.service.getFruBySeed(seed);
		return ResponseEntity.ok(found);
	}
	
	}

