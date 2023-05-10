package com.clinic.system.domain.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate dateOfBird;
    @Column(unique = true,nullable = false)
    private String identifier;
    @Embedded
    private Address address;
    private boolean active = true;
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;
    @CreationTimestamp
    private LocalDateTime createTimestamp;


    public Customer(CustomerInputDto customerDto){
        this.name = customerDto.getName();
        this.identifier = customerDto.getIdentifier();
        this.address = customerDto.getAddress();
        this.dateOfBird = customerDto.getDateOfBird();
    }
}
