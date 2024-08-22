package org.piggybank.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Transaction type cannot be null")
	private TransactionType transactionType;

	@NotNull(message = "Amount cannot be null")
	private Double amount;

	 private Long accountId;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Transaction reason cannot be null")
	private TransactionReason transactionReason;

	@JsonIgnore
	private LocalDateTime deleted_at;

	@CreationTimestamp
	@JsonIgnore
	private LocalDateTime created_at;

	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime updated_at;

}
