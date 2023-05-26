package com.clinic.system.domain.consultation;

import com.clinic.system.domain.customer.Customer;
import com.clinic.system.domain.doctor.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    @Column(name = "consultation_date_time",nullable = false)
    private LocalDateTime consultationDateTime;
    private LocalDateTime cancellationDateTime;
    private String cancellationReason;
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;
    @CreationTimestamp
    private LocalDateTime createTimestamp;

    public Consultation(Customer customer, Doctor doctor, LocalDateTime consultationDateTime) {
        this.customer = customer;
        this.doctor = doctor;
        this.consultationDateTime = consultationDateTime;
    }
}
