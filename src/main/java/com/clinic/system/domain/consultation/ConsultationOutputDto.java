package com.clinic.system.domain.consultation;

import com.clinic.system.domain.customer.CustomerOutputDto;
import com.clinic.system.domain.doctor.DoctorOutputDto;
import java.time.LocalDateTime;

public record ConsultationOutputDto (
        Long consultation_id,
        CustomerOutputDto customer,
        DoctorOutputDto doctor,
        LocalDateTime consultationDateTime
    ){
    public ConsultationOutputDto(Consultation consultation) {
        this(   consultation.getConsultation_id(),
                new CustomerOutputDto(consultation.getCustomer()),
                new DoctorOutputDto(consultation.getDoctor()),
                consultation.getConsultationDateTime());
    }
}
