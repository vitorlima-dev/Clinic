package com.clinic.system.domain.consultation.valid;

import com.clinic.system.domain.consultation.ConsultationInputDto;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateAdvanceTime extends Valid<ConsultationInputDto> {
    @Override
    public boolean violated(ConsultationInputDto data) {
        var consultationDateTime = data.getConsultationDateTime();
        var dateTimeNow = LocalDateTime.now();
        var difBetween = Duration.between(dateTimeNow, consultationDateTime).toMinutes();
        return difBetween <= 28;
    }
    @Override
    public String msgError(ConsultationInputDto data) {
        return "Appointment must be scheduled at least 30 minutes in advance";
    }
}
