package org.piggybank.creditcard.repo;

import java.util.Optional;

import org.piggybank.creditcard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCard, Integer>{
	@Query("SELECT c from CreditCard c WHERE c.user_id = :id and c.deleted_at IS NULL")
	Optional<CreditCard> findCreditCardByUserID(int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE CreditCard c SET c.deleted_at = CURRENT_TIMESTAMP WHERE c.id = :id AND c.deleted_at IS NULL", nativeQuery = true)
	void deleteCardByID(@Param("id") int id);
}
