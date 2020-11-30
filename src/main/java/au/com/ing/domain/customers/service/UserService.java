package au.com.ing.domain.customers.service;

import au.com.ing.domain.customers.model.User;
import au.com.ing.domain.customers.repository.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {


    @Autowired
    CustomerDetailsRepository customerDetailsRepository;



    public Optional<User> findByID(Long id) {
        Optional<User> customer = customerDetailsRepository.findById(id);
        return  customer;
    }


    public User save(User customer ) {
        return  customerDetailsRepository.save(customer);
    }




}
