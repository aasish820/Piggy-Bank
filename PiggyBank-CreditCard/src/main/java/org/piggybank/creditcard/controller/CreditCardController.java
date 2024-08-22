package org.piggybank.creditcard.controller;

import java.util.HashMap;
import java.util.Map;

import org.piggybank.creditcard.exception.CardNotFoundException;
import org.piggybank.creditcard.model.CreditCardDto;
import org.piggybank.creditcard.service.impl.CreditCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {
	
	@Autowired
	private CreditCardServiceImpl impl;
	
	@GetMapping("/{id}/list")
	public ResponseEntity<?> getCreditCardByUserID(@PathVariable("id") int id) {
		try {
			CreditCardDto cardDTO = impl.getByUserID(id);
	        return ResponseEntity.ok(cardDTO);
		} catch (CardNotFoundException e) {
			e.printStackTrace();
			Map<String,String>response=new HashMap<>();
			response.put("message", "Credit Card does not exist");
			return ResponseEntity.status(500).body(response);
		} 
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @ModelAttribute("creditcarddto") CreditCardDto carddto) {
		try {
			CreditCardDto ccdto = impl.create(carddto);
			Map<String, String> response = new HashMap<>();
			response.put("message", "Credit Card created successfully");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String>response=new HashMap<>();
			response.put("message", "Credit Card could not be created");
			return ResponseEntity.status(500).body(response);
		}
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @ModelAttribute("creditcarddto") CreditCardDto creditcardDTO) {
		try {
			CreditCardDto ccdto = impl.update(id, creditcardDTO);
			return ResponseEntity.ok("Credit Card updated successfully");
		} catch(Exception e) {
			e.printStackTrace();
			Map<String,String>response=new HashMap<>();
			response.put("message", "Credit Card could not be updated");
			return ResponseEntity.status(500).body(response);
		}
	}
	
	@PostMapping("/{id}/delete")
	public ResponseEntity<String> delete(@PathVariable int id) {
		String msg = impl.delete(id);
		return ResponseEntity.ok(msg);
	}
}