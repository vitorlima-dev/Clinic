package com.clinic.system.domain.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerOutputDto(
     Long id,
     String name,
     String identifier,
     Address address,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
     LocalDate dateOfBird,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDateTime updateTimestamp,
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDateTime createTimestamp,
     boolean active
)
{
    public CustomerOutputDto(Customer customer){
        this(   customer.getCustomer_id(),
                customer.getName(),
                customer.getIdentifier(),
                customer.getAddress(),
                customer.getDateOfBird(),
                customer.getUpdateTimestamp(),
                customer.getCreateTimestamp(),
                customer.isActive());
    }
}
