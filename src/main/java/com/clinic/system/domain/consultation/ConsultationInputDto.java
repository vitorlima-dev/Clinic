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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime consultationDateTime;

}
