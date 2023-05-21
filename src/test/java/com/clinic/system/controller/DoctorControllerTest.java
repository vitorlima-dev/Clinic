package com.clinic.system.controller;

import com.clinic.system.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DoctorInputDto> doctorInputDtoJacksonTester;
    @Autowired
    private JacksonTester<DoctorOutputDto> doctorOutputDtoJacksonTester;
    @MockBean
    DoctorRepository repository;
    @Test
    @DisplayName("should return status code 403 - FORBIDDEN")
    void scene_1() throws Exception {
        var response = mvc.perform(post("/doctor"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("should return status code 201 and Json Expected")
    @WithMockUser
    void scene_2() throws Exception {
        var doctorInput = new DoctorInputDto("name", "11111111111", Specialization.PSYCHOLOGIST);

        when(repository.save(any())).thenReturn(new Doctor(doctorInput));

        var doctorOutput = new DoctorOutputDto(new Doctor(doctorInput));
        var jsonExpected = doctorOutputDtoJacksonTester.write(doctorOutput).getJson();

        var response = mvc.perform(post("/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorInputDtoJacksonTester.write(doctorInput).getJson()))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}