package org.piggybank.giftcard.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.piggybank.giftcard.entity.GiftCard;
import org.piggybank.giftcard.model.GiftCardDTO;
import org.piggybank.giftcard.repo.GiftCardRepository;
import org.piggybank.giftcard.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftCardServiceImpl implements GiftCardService {
	
	@Autowired
	private GiftCardRepository giftCardRepo;

	@Override
	public GiftCardDTO createGiftCard(GiftCardDTO giftCardDTO) {
		System.out.println(giftCardDTO.getUserID());
		GiftCard giftCard = convertToEntity(giftCardDTO);
		GiftCard savedGiftCard = giftCardRepo.save(giftCard);
		return convertToDTO(savedGiftCard);
	}
	
	private GiftCardDTO convertToDTO(GiftCard giftCard) {
		GiftCardDTO giftCardDTO = new GiftCardDTO();
		giftCardDTO.setId(giftCard.getId());
		giftCardDTO.setCardNumber(giftCard.getCardNumber());
		giftCardDTO.setBalance(giftCard.getBalance());
		giftCardDTO.setExpireDate(giftCard.getExpireDate());
		giftCardDTO.setUserID(giftCard.getUserId());
		giftCardDTO.setActive(giftCard.isActive());
		return giftCardDTO;
		
		}

	private GiftCard convertToEntity(GiftCardDTO giftCardDTO) {
		GiftCard giftCard = new GiftCard();
		giftCard.setCardNumber(giftCardDTO.getCardNumber());
		giftCard.setBalance(giftCardDTO.getBalance());
		giftCard.setExpireDate(giftCardDTO.getExpireDate());
		giftCard.setUserId(giftCardDTO.getUserID());
		giftCard.setActive(giftCardDTO.isActive());
		return giftCard;
	}

	@Override
	public GiftCardDTO getGiftCardById(Long id) {
		GiftCard giftCard = giftCardRepo.findById(id).orElseThrow(()-> new RuntimeException("Gift Card not found"));
			return convertToDTO(giftCard);
		
	}

	@Override
	public List<GiftCardDTO> getGiftCardByUserId(long userId) {
		return giftCardRepo.findByUserId(userId).stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}


	@Override
	public GiftCardDTO updateGiftCard(long id, GiftCardDTO giftCardDTO) {
		GiftCard giftCard = giftCardRepo.findById(id).orElseThrow(() -> new RuntimeException("GiftCard Not Found"));
		giftCard.setCardNumber(giftCardDTO.getCardNumber());
		giftCard.setBalance(giftCardDTO.getBalance());
		giftCard.setExpireDate(giftCardDTO.getExpireDate());
		giftCard.setActive(giftCardDTO.isActive());
		GiftCard updateGiftCard = giftCardRepo.save(giftCard);
		return convertToDTO(updateGiftCard);
	}

	@Override
	public void deleteGiftCard(Long id) {
		giftCardRepo.deleteById(id);

	}

	@Override
	public GiftCardDTO redeemGiftCard(String cardNumber, double amount) {
		GiftCard giftCard = giftCardRepo.findByCardNumber(cardNumber);
		if(giftCard== null) {
			throw new RuntimeException("Gift Card not Found");
		}
		if(!giftCard.isActive()) {
			throw new RuntimeException("Gift Card isn't active");
		}
		if(giftCard.getExpireDate().isBefore(LocalDate.now())) {
			throw new RuntimeException ("Gift Card is expired");
		}
		if(giftCard.getBalance()<amount) {
			throw new RuntimeException("Insufficient Fund");
		}
		giftCard.setBalance(giftCard.getBalance()-amount);
		GiftCard updateGiftCard = giftCardRepo.save(giftCard);
		return convertToDTO(updateGiftCard);
	}

	@Override
	public boolean isGiftCardActicve(Long id) {
		GiftCard giftCard = giftCardRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Gift Card not Found"));
		return giftCard.isActive() && giftCard.getExpireDate().isAfter(LocalDate.now());
	}

}