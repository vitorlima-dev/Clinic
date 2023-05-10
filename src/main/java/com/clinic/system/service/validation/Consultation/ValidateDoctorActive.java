package com.clinic.system.service.validation.Consultation;

import com.clinic.system.domain.consultation.ConsultationInputDto;
import com.clinic.system.domain.doctor.DoctorRepository;
import com.clinic.system.service.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorActive extends Valid<ConsultationInputDto> {
    @Autowired
    DoctorRepository repository;
    @Override
    public boolean violated(ConsultationInputDto data) {
        return !repository.findActiveById(data.getDoctor_id());
    }
    @Override
    public String msgError(ConsultationInputDto data) {
        return "The Doctor is not active!";
    }
}
