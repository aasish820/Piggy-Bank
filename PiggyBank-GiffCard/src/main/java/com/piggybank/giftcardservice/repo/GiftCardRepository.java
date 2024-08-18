package com.piggybank.giftcardservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piggybank.giftcardservice.entity.GiftCard;
@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {
	List<GiftCard> findByUserId(Long userId);
	GiftCard findByCardNumber(String CardNumber);
	

}
