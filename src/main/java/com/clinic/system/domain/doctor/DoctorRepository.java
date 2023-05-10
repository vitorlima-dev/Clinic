package com.clinic.system.domain.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository< Doctor, Long> {
        boolean existsByIdentifier(String identifier);
        @Query( """
                select m.active from Doctor m
                where m.id = :id
                """ )
        boolean findActiveById(Long id);
}
