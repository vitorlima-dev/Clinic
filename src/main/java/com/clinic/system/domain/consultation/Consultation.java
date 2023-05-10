package com.clinic.system.domain.consultation;

import com.clinic.system.domain.customer.Customer;
import com.clinic.system.domain.doctor.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
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
    private Long consultation_id;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    @Future
    @Column(name = "consultation_date_time",nullable = false)
    private LocalDateTime consultationDateTime;
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
