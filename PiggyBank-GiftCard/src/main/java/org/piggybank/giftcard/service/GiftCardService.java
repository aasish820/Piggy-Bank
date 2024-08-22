package org.piggybank.giftcard.service;

import java.util.List;

import org.piggybank.giftcard.model.GiftCardDTO;

public interface GiftCardService {
	GiftCardDTO createGiftCard(GiftCardDTO giftCardDTO);
	GiftCardDTO getGiftCardById(Long id);
	List<GiftCardDTO> getGiftCardByUserId(long userId);
	GiftCardDTO updateGiftCard(long id, GiftCardDTO giftCardDTO);
	void deleteGiftCard(Long id);
	GiftCardDTO redeemGiftCard(String cardNumber, double amount);
	boolean isGiftCardActicve(Long id);
	
	

}