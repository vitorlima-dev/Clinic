package com.clinic.system.domain.doctor;

import com.clinic.system.domain.doctor.Doctor;
import com.clinic.system.domain.doctor.DoctorInputDto;
import com.clinic.system.domain.doctor.DoctorRepository;
import com.clinic.system.infrastructure.handleError.CustomException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository repository;
    @Transactional
    public Doctor create(DoctorInputDto doctorDto){
        if(repository.existsByIdentifier(doctorDto.getIdentifier())){
            throw new CustomException("Doctor already exists");
        }

        return repository.save(new Doctor(doctorDto));
    }
    @Transactional
    public void update( Long id,DoctorInputDto doctorDto){
        var doctor = findByDoctorId(id);
        BeanUtils.copyProperties(doctorDto, doctor);
        repository.save(doctor);
    }
    public List<Doctor> findAll(){
        return repository.findAll();
    }
    @Transactional
    public void delete(Long id){
        var doctor = findByDoctorId(id);
        doctor.setActive(false);
        repository.save(doctor);
    }
    public Doctor findByDoctorId(Long id) {
        var doctor = repository.findById(id);

        if(doctor.isEmpty()){
            throw new EntityNotFoundException("Doctor Not Found");
        }

        return doctor.get();
    }
}
