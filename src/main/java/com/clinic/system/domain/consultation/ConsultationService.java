package com.clinic.system.domain.consultation;

import com.clinic.system.domain.consultation.valid.Valid;
import com.clinic.system.domain.customer.CustomerService;
import com.clinic.system.domain.doctor.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return repository.findAllByCancellationDateTimeIsNull();
    }
    @Transactional
    public void delete(CancellationInputDto cancellationInputDto ,Long id){
        var consultation = findByConsultationId(id);
        consultation.setCancellationDateTime(LocalDateTime.now());
        consultation.setCancellationReason(cancellationInputDto.getCancellationReason());
        repository.save(consultation);
    }
    public Consultation findByConsultationId(Long id){
        var consultation = repository.findByConsultationIdAndCancellationDateTimeIsNull(id);

        if(consultation == null){
            throw  new EntityNotFoundException("Consultation Not Fond");
        }

        return consultation;
    }


}
