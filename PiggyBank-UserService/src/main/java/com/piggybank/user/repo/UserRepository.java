package com.piggybank.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.piggybank.user.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.deletedAt IS NULL")

	Optional<User> findByUsername(String username);
    Optional<User> findByIdAndDeleteAtIsNull(Long id);
    List<User> findAllByDeleteAtIsNull();



	


	
}
 