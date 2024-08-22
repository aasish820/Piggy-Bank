package org.piggybank.giftcard.controller;

import java.util.List;

import org.piggybank.giftcard.model.GiftCardDTO;
import org.piggybank.giftcard.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/giftcard")
public class GiftCardController {
	@Autowired
	private GiftCardService giftCardService;

	@PostMapping("/create")
	public ResponseEntity<GiftCardDTO> createGiftCard(@RequestBody GiftCardDTO giftCardDTO) {
		GiftCardDTO createGiftCard = giftCardService.createGiftCard(giftCardDTO);
		return ResponseEntity.ok(createGiftCard);

	}

	@GetMapping("/{id}")
	public ResponseEntity<GiftCardDTO> getGiftCardByUserId(@PathVariable Long id) {
		GiftCardDTO giftCardDTO = giftCardService.getGiftCardById(id);
		return ResponseEntity.ok(giftCardDTO);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<GiftCardDTO>> getGiftCardsUserId(@PathVariable Long userId) {
		List<GiftCardDTO> giftCards = giftCardService.getGiftCardByUserId(userId);

		return ResponseEntity.ok(giftCards);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<GiftCardDTO> updateGiftCard(@PathVariable Long id, @RequestBody GiftCardDTO giftCardDTO) {
		GiftCardDTO updatedGiftCard = giftCardService.updateGiftCard(id, giftCardDTO);
		return ResponseEntity.ok(updatedGiftCard);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteGiftCard(@PathVariable Long id) {
		giftCardService.deleteGiftCard(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/redeem")
	public ResponseEntity<GiftCardDTO> redeemGiftCard(@RequestParam String cardnumber, @RequestParam double amount) {
		GiftCardDTO updateGiftCard = giftCardService.redeemGiftCard(cardnumber, amount);
		return ResponseEntity.ok(updateGiftCard);
	}

	@GetMapping("/{id}/active")
	public ResponseEntity<Boolean> isGiftcardActive(@PathVariable Long id) {
		boolean isActive = giftCardService.isGiftCardActicve(id);
		return ResponseEntity.ok(isActive);

	}

}