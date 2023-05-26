package com.clinic.system.domain.consultation.valid;

import com.clinic.system.domain.consultation.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateCancellationAdvanceTime extends Valid<Long>{
    @Autowired
    ConsultationService service;
    @Override
    public boolean violated(Long Id) {
        var consultation = service.findByConsultationId(Id);
        var cancellationDateTime = consultation.getCancellationDateTime();
        var advanceTime = Duration.between(cancellationDateTime, LocalDateTime.now()).toMinutes();
        return advanceTime < 30;
    }

    @Override
    public String msgError(Long data) {
        return "Minimum cancellation notice period must be respected";
    }
}
