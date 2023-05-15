package com.clinic.system.domain.consultation.valid;

import com.clinic.system.domain.consultation.ConsultationInputDto;
import com.clinic.system.domain.consultation.valid.Valid;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ValidateClinicOpeningHours extends Valid<ConsultationInputDto> {
    @Override
    public boolean violated(ConsultationInputDto data) {
        var consultationDateTime = data.getConsultationDateTime();

        var sunday = consultationDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var saturday = consultationDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var beforeTheClinicOpens = consultationDateTime.getHour() < 7;
        var afterTheClinicClose = consultationDateTime.getHour() > 18 ||
                (consultationDateTime.getHour() == 18 && consultationDateTime.getMinute() > 0);

        return saturday || sunday || beforeTheClinicOpens || afterTheClinicClose;
    }
    @Override
    public String msgError(ConsultationInputDto data) {
        return "Appointment outside clinic opening hours. \n" +
                "time sent: " + data.getConsultationDateTime();
    }
}
