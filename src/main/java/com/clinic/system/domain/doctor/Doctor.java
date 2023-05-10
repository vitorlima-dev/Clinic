package com.clinic.system.domain.doctor;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctor_id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String identifier;
    @Column(nullable = false)
    private Specialization specialization;
    private boolean active = true;
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;
    @CreationTimestamp
    private LocalDateTime createTimestamp;

    public Doctor(DoctorInputDto doctorDto) {
        this.name = doctorDto.getName();
        this.identifier = doctorDto.getIdentifier();
        this.specialization = doctorDto.getSpecialization();
    }
}
