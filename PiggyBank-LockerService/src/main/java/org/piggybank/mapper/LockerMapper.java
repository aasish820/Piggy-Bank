package org.piggybank.mapper;

import org.piggybank.entity.Locker;
import org.piggybank.entity.LockerType;
import org.piggybank.model.LockerDTO;
import org.springframework.stereotype.Component;

@Component
public class LockerMapper {

    public LockerDTO toDTO(Locker locker) {
        return new LockerDTO(
            locker.getLockerId(),
            locker.getLockerType().name(),
            locker.getLockerNumber(),
            locker.getIsAvailable(),
            locker.getAccountId(),
            locker.getLockerType().getMonthlyFee()
        );
    }

    public Locker toEntity(LockerDTO lockerDTO) {
        Locker locker = new Locker();
        locker.setLockerType(LockerType.valueOf(lockerDTO.getLockerType()));
        locker.setLockerNumber(lockerDTO.getLockerNumber());
        locker.setIsAvailable(lockerDTO.getIsAvailable());
        locker.setAccountId(lockerDTO.getAccountId());
        return locker;
    }
}
