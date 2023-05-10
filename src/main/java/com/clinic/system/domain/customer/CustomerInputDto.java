package com.clinic.system.domain.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CustomerInputDto {

    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 11, max = 11)
    private String identifier;
    private Address address;
    @NotNull
    private LocalDate dateOfBird;
}
