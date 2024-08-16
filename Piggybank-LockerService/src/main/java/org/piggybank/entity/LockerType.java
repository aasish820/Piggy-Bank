package org.piggybank.entity;

public enum LockerType {
    SMALL(10), 
    MEDIUM(15), 
    LARGE(25), 
    HIGH_SECURITY(50);

    private final int monthlyFee;

    LockerType(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }
}
