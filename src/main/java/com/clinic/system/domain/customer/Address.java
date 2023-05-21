package com.clinic.system.domain.customer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Embeddable
public class Address {
    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
