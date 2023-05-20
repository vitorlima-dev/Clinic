package com.clinic.system.domain.doctor;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class DoctorInputDto {
    @NotNull
    private String name;
    @NotNull
    private String identifier;
    @NotNull
    private Specialization specialization;
}
