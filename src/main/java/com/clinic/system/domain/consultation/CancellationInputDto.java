package com.clinic.system.domain.consultation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CancellationInputDto {
    @NotNull
    String cancellationReason;
}
