package com.clinic.system.domain.consultation;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ConsultationInputDto {
    @NotNull
    private Long customer_id;
    @NotNull
    private Long doctor_id;
    @NotNull
    private LocalDateTime consultationDateTime;

    public ConsultationInputDto(Long customerId, Long doctorId, LocalDateTime consultationDateTime) {
        this.customer_id = customerId;
        this.doctor_id = doctorId;
        this.consultationDateTime = consultationDateTime;
    }
}
