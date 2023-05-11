package com.clinic.system.service.validation.consultation;

import com.clinic.system.domain.consultation.ConsultationInputDto;
import com.clinic.system.domain.consultation.ConsultationRepository;
import com.clinic.system.service.DoctorService;
import com.clinic.system.service.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateIfDoctorHasConsultationSameDateTime extends Valid<ConsultationInputDto> {
    @Autowired
    ConsultationRepository repository;
    @Autowired
    DoctorService service;
    @Override
    public boolean violated(ConsultationInputDto data) {

        var doctor = service.findByDoctorId(data.getDoctor_id());
        var startDateTime = data.getConsultationDateTime().plusMinutes(-59);
        var endDateTime = data.getConsultationDateTime().plusMinutes(59);

        var isViolated = !repository.findByDoctorAndConsultationDateTimeBetween(
                doctor,startDateTime,endDateTime).isEmpty();

        return isViolated ;
    }
    @Override
    public String msgError(ConsultationInputDto data) {
        return "The doctor has another consultation at the same date and time";
    }
}
