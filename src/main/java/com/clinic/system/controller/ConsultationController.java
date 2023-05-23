package com.clinic.system.controller;

import com.clinic.system.domain.consultation.ConsultationInputDto;
import com.clinic.system.domain.consultation.ConsultationOutputDto;
import com.clinic.system.domain.consultation.ConsultationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultation")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {
    @Autowired
    private ConsultationService service;
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ConsultationInputDto consultationDto, UriComponentsBuilder uriBuilder){
        var consultation = service.create(consultationDto);
        var uri = uriBuilder.path("{id}").buildAndExpand(consultation.getConsultation_id()).toUri();
        return ResponseEntity.created(uri).body(new ConsultationOutputDto(consultation));
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid ConsultationInputDto consultationDto){
        service.update(id,consultationDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.findAll().stream().map(ConsultationOutputDto::new).toList());
    }
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new ConsultationOutputDto(
                service.findByConsultationId(id)));
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
