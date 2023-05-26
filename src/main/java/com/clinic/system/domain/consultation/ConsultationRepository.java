package com.clinic.system.domain.consultation;

import com.clinic.system.domain.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultationRepository  extends JpaRepository<Consultation, Long> {
    List<Consultation> findByDoctorAndConsultationDateTimeBetween(Doctor doctor, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Consultation> findAllByCancellationDateTimeIsNull();
    Consultation findByConsultationIdAndCancellationDateTimeIsNull(Long Id);
}
