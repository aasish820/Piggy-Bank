package org.piggybank.giftcard.repo;

import java.util.List;

import org.piggybank.giftcard.entity.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {
	List<GiftCard> findByUserId(Long userId);
	GiftCard findByCardNumber(String CardNumber);
	

}
