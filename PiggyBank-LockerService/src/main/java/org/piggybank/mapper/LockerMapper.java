package org.piggybank.mapper;

import org.piggybank.entity.Locker;
import org.piggybank.entity.LockerType;
import org.piggybank.model.LockerDTO;
import org.springframework.stereotype.Component;

@Component
public class LockerMapper {

    public LockerDTO toDTO(Locker locker) {
        return new LockerDTO(
            locker.getLockerType().name(),
            locker.getLockerNumber(),
            locker.getIsAvailable(),
            locker.getAccount_id(),
            locker.getLockerType().getMonthlyFee()
        );
    }

    public Locker toEntity(LockerDTO lockerDTO) {
        Locker locker = new Locker();
        locker.setLockerType(LockerType.valueOf(lockerDTO.getLockerType()));
        locker.setLockerNumber(lockerDTO.getLockerNumber());
        locker.setIsAvailable(lockerDTO.getIsAvailable());
        locker.setAccount_id(lockerDTO.getAccount_id());
        return locker;
    }
}
