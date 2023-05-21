package com.clinic.system.controller;

import com.clinic.system.domain.consultation.*;
import com.clinic.system.domain.customer.Address;
import com.clinic.system.domain.customer.Customer;
import com.clinic.system.domain.customer.CustomerInputDto;
import com.clinic.system.domain.doctor.Doctor;
import com.clinic.system.domain.doctor.DoctorInputDto;
import com.clinic.system.domain.doctor.Specialization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ConsultationService service;
    @Autowired
    private JacksonTester<ConsultationInputDto> consultationInputDtoJacksonTester;
    @Autowired
    private JacksonTester<ConsultationOutputDto> consultationOutputDtoJacksonTester;
    @Test
    @WithMockUser
    @DisplayName("Should return status 201 and expected json")
    void scene_1() throws Exception{
        var consultationInput = new ConsultationInputDto(1L, 1L, LocalDateTime.now());
        var consultation = new Consultation(customerData(), doctorData(), LocalDateTime.now());
        var consultationOutput = new ConsultationOutputDto(consultation);

        when(service.create(any())).thenReturn(consultation);
        var response = mvc
                .perform(
                        post("/consultation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( consultationInputDtoJacksonTester
                                        .write(consultationInput)
                                .getJson()))
                                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonExpected = consultationOutputDtoJacksonTester.write(consultationOutput).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

    private Customer customerData(){
        var customerInputDto = new CustomerInputDto(
                "test","00000000000",null,LocalDate.now());
        return new Customer(customerInputDto);
    }

    private Doctor doctorData(){
        var doctorInputDto = new DoctorInputDto(
                "test",
                "00000000000",
                Specialization.PSYCHOLOGIST
        );
        return new Doctor(doctorInputDto);
    }
}