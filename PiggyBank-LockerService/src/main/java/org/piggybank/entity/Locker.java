package org.piggybank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Locker")
public class Locker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lockerId;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Locker type cannot be null")
	private LockerType lockerType;

	@NotNull(message = "Locker number cannot be null")
	@Size(min = 1, max = 20, message = "Locker number must be between 1 and 20 characters")
	@Column(unique = true)
	private String lockerNumber;

	@NotNull(message = "Availability status cannot be null")
	private Boolean isAvailable;
	
	@Column(name="account_id")
	private Long account_id;

	@JsonIgnore
	private LocalDateTime deleted_at;

	@CreationTimestamp
	@JsonIgnore
	private LocalDateTime created_at;

	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime updated_at;

	public int getMonthlyFee() {
		return lockerType.getMonthlyFee();
	}
}
