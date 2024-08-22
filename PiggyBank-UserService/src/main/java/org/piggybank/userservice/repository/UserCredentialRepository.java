package org.piggybank.userservice.repository;

import java.util.Optional;

import org.piggybank.userservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByUsername(String username);
}
