package com.vani.ams.model;

import java.sql.Date;

import lombok.Data;
@Data
public class AccountDTO {
	private long id;
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private String email;
    private String phoneNumber;
    private Double balance;
    private Date creationDate;
    private Date lastUpdatedDate;
    private Long userId;
	
}
