package org.piggybank.controller;

import org.piggybank.model.LockerDTO;
import org.piggybank.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    @Autowired
    private LockerService lockerService;

    @PostMapping
    public ResponseEntity<LockerDTO> createLocker(@RequestBody LockerDTO lockerDTO) {
        LockerDTO createdLocker = lockerService.createLocker(lockerDTO);
        return new ResponseEntity<>(createdLocker, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LockerDTO> updateLocker(@PathVariable("id") Long lockerId, @RequestBody LockerDTO lockerDTO) {
        LockerDTO updatedLocker = lockerService.updateLocker(lockerId, lockerDTO);
        return new ResponseEntity<>(updatedLocker, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LockerDTO> getLockerById(@PathVariable("id") Long lockerId) {
        LockerDTO lockerDTO = lockerService.findLockerById(lockerId);
        return new ResponseEntity<>(lockerDTO, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<LockerDTO>> getLockersByType(@PathVariable("type") String lockerType) {
        List<LockerDTO> lockers = lockerService.findLockersByType(lockerType);
        return new ResponseEntity<>(lockers, HttpStatus.OK);
    }

    @GetMapping("/type/{type}/paged")
    public ResponseEntity<Page<LockerDTO>> getLockersByTypePaged(@PathVariable("type") String lockerType, Pageable pageable) {
        Page<LockerDTO> lockers = lockerService.findLockersByType(lockerType, pageable);
        return new ResponseEntity<>(lockers, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<LockerDTO>> getAllActiveLockers() {
        List<LockerDTO> lockers = lockerService.findAllActiveLockers();
        return new ResponseEntity<>(lockers, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<LockerDTO>> getAllAvailableLockers() {
        List<LockerDTO> lockers = lockerService.findAllAvailableLockers();
        return new ResponseEntity<>(lockers, HttpStatus.OK);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<LockerDTO> getLockerByNumber(@PathVariable("number") String lockerNumber) {
        LockerDTO lockerDTO = lockerService.findLockerByNumber(lockerNumber);
        return new ResponseEntity<>(lockerDTO, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<LockerDTO>> getLockersByAccountId(@PathVariable("accountId") Long accountId) {
        List<LockerDTO> lockers = lockerService.findLockersByAccountId(accountId);
        return new ResponseEntity<>(lockers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/soft")
    public ResponseEntity<Void> softDeleteLocker(@PathVariable("id") Long lockerId) {
        lockerService.softDeleteLocker(lockerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocker(@PathVariable("id") Long lockerId) {
        lockerService.deleteLocker(lockerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
