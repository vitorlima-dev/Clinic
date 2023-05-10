package com.clinic.system.domain.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
public record DoctorOutputDto(
        Long id,
        String name,
        String identifier,
        Specialization specialization,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime updateTimestamp,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createTimestamp,
        boolean active) {
    public DoctorOutputDto(Doctor doctor) {
        this(   doctor.getDoctor_id(),
                doctor.getName(),
                doctor.getIdentifier(),
                doctor.getSpecialization(),
                doctor.getUpdateTimestamp(),
                doctor.getCreateTimestamp(),
                doctor.isActive());
    }
}
