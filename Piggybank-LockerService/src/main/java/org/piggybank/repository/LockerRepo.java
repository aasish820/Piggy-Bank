package org.piggybank.repository;

import org.piggybank.entity.Locker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface LockerRepo extends JpaRepository<Locker, Long> {

	@Query("SELECT l FROM Locker l WHERE l.lockerType = :lockerType")
	List<Locker> findLockersByType(@Param("lockerType") String lockerType);

	@Query("SELECT l FROM Locker l WHERE l.deleted_at IS NULL")
	List<Locker> findAllActiveLockers();

	@Query("SELECT l FROM Locker l WHERE l.isAvailable = true")
	List<Locker> findAllAvailableLockers();

	@Query("SELECT l FROM Locker l WHERE l.lockerNumber = :lockerNumber")
	Locker findLockerByNumber(@Param("lockerNumber") String lockerNumber);

//    // Custom query to count lockers of a specific type
//    @Query("SELECT COUNT(l) FROM Locker l WHERE l.lockerType = :lockerType")
//    long countLockersByType(@Param("lockerType") String lockerType);

	@Query("SELECT l FROM Locker l WHERE l.lockerType = :lockerType")
	Page<Locker> findLockersByType(@Param("lockerType") String lockerType, Pageable pageable);

	@Query("SELECT l FROM Locker l WHERE l.account.id = :accountId")
	List<Locker> findLockersByAccountId(@Param("accountId") Long accountId);

	@Modifying
	@Transactional
	@Query("UPDATE Locker l SET l.deleted_at = :deletedAt WHERE l.lockerId = :lockerId")
	void softDeleteById(@Param("lockerId") Long lockerId);
}
