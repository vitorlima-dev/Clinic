package com.clinic.system.domain.consultation.valid;

import static org.junit.Assert.*;
import com.clinic.system.domain.consultation.ConsultationInputDto;
import com.clinic.system.infrastructure.handleError.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class ValidateAdvanceTimeTest {
    ValidateAdvanceTime validateAdvanceTime = new ValidateAdvanceTime();
    ConsultationInputDto inputDto;
    @Test
    @DisplayName("should return error when 30 minutes lead time is not respected")
    public void scene_1(){
        this.inputDto = new ConsultationInputDto( 1l,1l, LocalDateTime.now().plusMinutes(25));
        var ex = assertThrows(CustomException.class,
                () -> validateAdvanceTime.validate(inputDto));

        assertEquals("Appointment must be scheduled at least 30 minutes in advance",ex.getMessage());
    }
    @Test
    @DisplayName("must return the code 0 when the consultation schedule is 30 minutes in advance")
    public void scene_2(){
        this.inputDto = new ConsultationInputDto(1l,1l, LocalDateTime.now().plusMinutes(30));
        assertEquals(0, validateAdvanceTime.validate(inputDto));
    }
}
