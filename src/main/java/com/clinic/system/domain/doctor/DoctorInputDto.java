package com.clinic.system.domain.doctor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DoctorInputDto {
    @NotNull
    private String name;
    @NotNull
    private String identifier;
    @NotNull
    private Specialization specialization;
}
