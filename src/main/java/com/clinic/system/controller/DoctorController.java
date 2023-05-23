package com.clinic.system.controller;

import com.clinic.system.domain.doctor.DoctorInputDto;
import com.clinic.system.domain.doctor.DoctorOutputDto;
import com.clinic.system.domain.doctor.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
@RestController
@RequestMapping("/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    @Autowired
    DoctorService service;
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid DoctorInputDto doctorDto, UriComponentsBuilder uriBuilder){
        var doctor = service.create(doctorDto);
        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getDoctor_id()).toUri();
        return ResponseEntity.created(uri).body(new DoctorOutputDto(doctor));
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id,  @RequestBody @Valid DoctorInputDto doctorDto){
        service.update(id,doctorDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
            new DoctorOutputDto(service.findByDoctorId(id)));
    }
    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.findAll().stream().map(DoctorOutputDto::new).toList());
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
