package org.piggybank.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.piggybank.entity.Locker;
import org.piggybank.entity.LockerType;
import org.piggybank.exception.LockerNotFoundException;
import org.piggybank.mapper.LockerMapper;
import org.piggybank.model.LockerDTO;
import org.piggybank.repository.LockerRepo;
import org.piggybank.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LockerServiceImpl implements LockerService {

	@Autowired
	private LockerRepo lockerRepo;

	@Autowired
	private LockerMapper lockerMapper;

	@Autowired
	private RestTemplate restTemplate;

	private static final String ACCOUNT_SERVICE_URL = "http://account-service/api/accounts/";

//	@SuppressWarnings("unchecked")
	@Override
	public LockerDTO createLocker(LockerDTO lockerDTO) {
//		Long accountId = lockerDTO.getAccountId();
//        Map<String, Object> accountData = restTemplate.getForObject(ACCOUNT_SERVICE_URL + accountId, Map.class);
//
//        if (accountData == null || !accountData.containsKey("id")) {
//            throw new RuntimeException("Account not found with id " + accountId);
//        }

        Locker locker = lockerMapper.toEntity(lockerDTO);
//        locker.setAccountId(accountId);
        locker.setAccountId(1L);
        locker = lockerRepo.save(locker);
        return lockerMapper.toDTO(locker);
	}

	@SuppressWarnings("unchecked")
	@Override
	public LockerDTO updateLocker(Long lockerId, LockerDTO lockerDTO) {
		Locker locker = lockerRepo.findById(lockerId)
                .orElseThrow(() -> new LockerNotFoundException("Locker not found with id " + lockerId));

        Long accountId = lockerDTO.getAccountId();
        Map<String, Object> accountData = restTemplate.getForObject(ACCOUNT_SERVICE_URL + accountId, Map.class);

        if (accountData == null || !accountData.containsKey("id")) {
            throw new RuntimeException("Account not found with id " + accountId);
        }

        locker.setLockerType(LockerType.valueOf(lockerDTO.getLockerType()));
        locker.setLockerNumber(lockerDTO.getLockerNumber());
        locker.setIsAvailable(lockerDTO.getIsAvailable());
        locker.setAccountId(accountId);

        locker = lockerRepo.save(locker);
        return lockerMapper.toDTO(locker);
	}

	@Override
	public LockerDTO findLockerById(Long lockerId) {
		return lockerRepo.findById(lockerId)
				.map(lockerMapper::toDTO)
				.orElseThrow(() -> new LockerNotFoundException("Locker not found with id " + lockerId));
	}

	@Override
	public List<LockerDTO> findLockersByType(String lockerType) {
		// lockerRepo.findLockersByType(lockerType).stream().map(lockerMapper::toDTO).collect(Collectors.toList());
		List<Locker> lockers = lockerRepo.findLockersByType(lockerType);
		List<LockerDTO> lockerDTOs = new ArrayList<>();

		for (Locker locker : lockers) {
			lockerDTOs.add(lockerMapper.toDTO(locker));
		}

		return lockerDTOs;
	}

	@Override
	public Page<LockerDTO> findLockersByType(String lockerType, Pageable pageable) {
		return lockerRepo.findLockersByType(lockerType, pageable)
				.map(lockerMapper::toDTO);
	}

	@Override
	public List<LockerDTO> findAllActiveLockers() {
		// lockerRepo.findAllActiveLockers().stream().map(lockerMapper::toDTO).collect(Collectors.toList());
		List<Locker> lockers = lockerRepo.findAllActiveLockers();
		List<LockerDTO> lockerDTOs = new ArrayList<>();

		for (Locker locker : lockers) {
			lockerDTOs.add(lockerMapper.toDTO(locker));
		}

		return lockerDTOs;
	}

	@Override
	public List<LockerDTO> findAllAvailableLockers() {
		// return
		// lockerRepo.findAllAvailableLockers().stream().map(lockerMapper::toDTO).collect(Collectors.toList());
		List<Locker> lockers = lockerRepo.findAllAvailableLockers();
		List<LockerDTO> lockerDTOs = new ArrayList<>();

		for (Locker locker : lockers) {
			lockerDTOs.add(lockerMapper.toDTO(locker));
		}

		return lockerDTOs;
	}

	@Override
	public LockerDTO findLockerByNumber(String lockerNumber) {
		Locker locker = lockerRepo.findLockerByNumber(lockerNumber);

		if (locker == null) {
			throw new LockerNotFoundException("Locker not found with number " + lockerNumber);
		}

		return lockerMapper.toDTO(locker);
	}

	@Override
	public List<LockerDTO> findLockersByAccountId(Long accountId) {
		List<Locker> lockers = lockerRepo.findLockersByAccountId(accountId);
		List<LockerDTO> lockerDTOs = new ArrayList<>();

		for (Locker locker : lockers) {
			lockerDTOs.add(lockerMapper.toDTO(locker));
		}

		return lockerDTOs;
	}

	@Override
	public void softDeleteLocker(Long lockerId) {
//		Locker locker = lockerRepo.findById(lockerId)
//				.orElseThrow(() -> new LockerNotFoundException("Locker not found with id " + lockerId));
//
//		locker.setDeleted_at(LocalDateTime.now());
//		lockerRepo.save(locker);
		lockerRepo.softDeleteById(lockerId);

	}

	@Override
	public void deleteLocker(Long lockerId) {
		lockerRepo.deleteById(lockerId);

	}
}
