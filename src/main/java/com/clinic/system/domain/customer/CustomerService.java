package com.clinic.system.domain.customer;

import com.clinic.system.infra.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Transactional
    public Customer create(CustomerInputDto customerDto) {
        if(repository.existsByIdentifier(customerDto.getIdentifier())){
            throw new CustomException("Customer already exists");
        }
        return repository.save( new Customer(customerDto) );
    }
    @Transactional
    public void update(Long id , CustomerInputDto customerDto) {
        var customer = findByCustomerId(id);
        BeanUtils.copyProperties(customerDto, customer);
        repository.save(customer);
    }

    public List<Customer> findAll(){
        return repository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        var customer = findByCustomerId(id);
        customer.setActive(false);
        repository.save(customer);
    }
    public Customer findByCustomerId(Long id) {
        var customer = repository.findById(id);

        if(customer.isEmpty()){
            throw new EntityNotFoundException("Customer Not Found");
        }

        return customer.get();
    }
}
