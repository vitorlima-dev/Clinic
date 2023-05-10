package com.clinic.system.service;

import com.clinic.system.domain.consultation.Consultation;
import com.clinic.system.domain.consultation.ConsultationInputDto;
import com.clinic.system.domain.consultation.ConsultationRepository;
import com.clinic.system.service.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository repository;
    @Autowired
    CustomerService customerService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    List<Valid<ConsultationInputDto>> validations;
    @Transactional
    public Consultation create(ConsultationInputDto consultationDto){
        var customer = customerService.findByCustomerId(consultationDto.getCustomer_id());
        var doctor = doctorService.findByDoctorId(consultationDto.getDoctor_id());

        validations.forEach(v -> v.validate(consultationDto));

        var consultation = new Consultation(
                customer, doctor, consultationDto.getConsultationDateTime()
        );
  
        return repository.save(consultation);
    }
    @Transactional
    public void update(Long id, ConsultationInputDto consultationDto){
        var consultation = findByConsultationId(id);
        BeanUtils.copyProperties(consultationDto, consultation);
        repository.save(consultation);
    }
    public List<Consultation> findAll(){
        return repository.findAll();
    }
    public void delete(Long id){
        var consultation = findByConsultationId(id);
        repository.delete(consultation);
    }
    public Consultation findByConsultationId(Long id){
        var consultation = repository.findById(id);

        if(consultation.isEmpty()){
            throw  new EntityNotFoundException("Consultation Not Fond");
        }

        return consultation.get();
    }


}
