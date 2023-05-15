package com.clinic.system.controller;

import com.clinic.system.domain.customer.CustomerOutputDto;
import com.clinic.system.domain.customer.CustomerInputDto;
import com.clinic.system.domain.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CustomerInputDto customerInputDto, UriComponentsBuilder uriBuilder) {
        var customer = service.create(customerInputDto);
        var uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getCustomer_id()).toUri();
        return ResponseEntity.created(uri).body( new CustomerOutputDto(customer));
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id , @RequestBody @Valid CustomerInputDto customerDto)  {
        service.update(id,customerDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCustomerId(id));
    }
    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.findAll().stream().map(CustomerOutputDto::new).toList());
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
