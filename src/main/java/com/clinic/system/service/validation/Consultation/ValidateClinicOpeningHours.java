package com.clinic.system.service.validation.Consultation;

import com.clinic.system.domain.consultation.ConsultationInputDto;
import com.clinic.system.service.validation.Valid;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ValidateClinicOpeningHours extends Valid<ConsultationInputDto> {
    @Override
    public boolean violated(ConsultationInputDto data) {
        var consultationDateTime = data.getConsultationDateTime();
        var sunday = consultationDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeTheClinicOpens = consultationDateTime.getHour() < 7;
        var afterTheClinicClose = consultationDateTime.getHour() > 19;
        return sunday || beforeTheClinicOpens || afterTheClinicClose;
    }
    @Override
    public String msgError(ConsultationInputDto data) {
        return "Appointment outside clinic opening hours. \n" +
                "time sent: " + data.getConsultationDateTime();
    }
}
