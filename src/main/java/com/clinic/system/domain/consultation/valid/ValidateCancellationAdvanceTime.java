package com.clinic.system.domain.consultation.valid;

import com.clinic.system.domain.consultation.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCancellationAdvanceTime extends Valid<Long>{
    @Autowired
    ConsultationService service;
    @Override
    public boolean violated(Long Id) {
        var consultation = service.findByConsultationId(Id);
        return true;
    }

    @Override
    public String msgError(Long data) {
        return "true";
    }
}
