package org.piggybank.service;

//import org.piggybank.entity.Locker;
import org.piggybank.model.LockerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface LockerService {

    LockerDTO createLocker(LockerDTO lockerDTO);

    LockerDTO updateLocker(Long lockerId, LockerDTO lockerDTO);

    LockerDTO findLockerById(Long lockerId);

    List<LockerDTO> findLockersByType(String lockerType);

    Page<LockerDTO> findLockersByType(String lockerType, Pageable pageable);

    List<LockerDTO> findAllActiveLockers();

    List<LockerDTO> findAllAvailableLockers();

    LockerDTO findLockerByNumber(String lockerNumber);

    List<LockerDTO> findLockersByAccountId(Long accountId);

    void softDeleteLocker(Long lockerId);

    void deleteLocker(Long lockerId);
}
