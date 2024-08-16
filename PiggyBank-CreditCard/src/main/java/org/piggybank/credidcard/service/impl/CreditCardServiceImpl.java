package org.piggybank.credidcard.service.impl;

import org.piggybank.credidcard.entity.CreditCard;
import org.piggybank.credidcard.model.CreditCardDto;
import org.piggybank.credidcard.repo.CreditCardRepo;
import org.piggybank.credidcard.service.CreditCardService;
import org.piggybank.creditcard.exception.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepo repo;
	
	private CreditCardDto convertToDTO(CreditCard card) {
		CreditCardDto cardDTO = new CreditCardDto();
		
		cardDTO.setCard_number(card.getCard_number());
		cardDTO.setExpiration_year(card.getExpiration_year());
		cardDTO.setExpiration_month(card.getExpiration_month());
		cardDTO.setCvv(card.getCvv());
		cardDTO.setCredit_limit(card.getCredit_limit());
		cardDTO.setAmount(card.getAmount());
		
		return cardDTO;
	}
	
	@Override
	public CreditCardDto create(CreditCardDto creditCardDto) {
		CreditCard card = new CreditCard();
		
		card.setCard_number(creditCardDto.getCard_number());
		card.setExpiration_month(creditCardDto.getExpiration_month());
		card.setExpiration_year(creditCardDto.getExpiration_year());
		card.setCvv(creditCardDto.getCvv());
		card.setAmount(creditCardDto.getAmount());
		card.setCredit_limit(creditCardDto.getCredit_limit());
		card.setUser_id(creditCardDto.getUser_id());
		
		CreditCard saved = repo.save(card);
		
		return convertToDTO(saved);
	}

	@Override
	public CreditCardDto update(int id, CreditCardDto creditCardDto) {
		CreditCard cc_obj = repo.findCreditCardByUserID(id).get();
		if(cc_obj != null) {
			cc_obj.setCard_number(creditCardDto.getCard_number());
			cc_obj.setExpiration_month(creditCardDto.getExpiration_month());
			cc_obj.setExpiration_year(creditCardDto.getExpiration_year());
			cc_obj.setCvv(creditCardDto.getCvv());
			cc_obj.setAmount(creditCardDto.getAmount());
			cc_obj.setCredit_limit(creditCardDto.getCredit_limit());
			cc_obj.setUser_id(creditCardDto.getUser_id());

			CreditCard saved = repo.save(cc_obj);
			
			return convertToDTO(saved);
		}
		return null;
	}

	@Override
	public CreditCardDto getByUserID(int userId) {
		CreditCard cc = repo.findCreditCardByUserID(userId).orElseThrow(()->new CardNotFoundException("Credit Card does not exist"));
		return convertToDTO(cc);
	}

	@Override
	public CreditCardDto pay(int userId, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int id) {
		String msg = "Credit Card deleted successfully";
		CreditCard cc_obj = repo.findCreditCardByUserID(id).get();
		if(cc_obj != null) {
			repo.deleteCardByID(id);
		} else {
			throw new CardNotFoundException("Credit Card does not exist");
		}
		return msg;
	}

}
