package org.mrunmayi.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.CustomerRequest;
import org.mrunmayi.restaurant.dto.CustomerResponse;
import org.mrunmayi.restaurant.dto.LoginRequest;
import org.mrunmayi.restaurant.entity.Customer;
import org.mrunmayi.restaurant.exception.CustomerNotFoundException;
import org.mrunmayi.restaurant.helpers.EncryptionService;
import org.mrunmayi.restaurant.helpers.JWTHelper;
import org.mrunmayi.restaurant.mapper.CustomerMapper;
import org.mrunmayi.restaurant.repo.CustomerRepo;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encryptPassword(customer.getPassword()));
        repo.save(customer);
        return "Customer Created";
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomerByEmail(email);
        return mapper.toCustomerResponse(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomerByEmail(request.email());
        if(!encryptionService.validatePassword(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }
        return jwtHelper.generateToken(request.email());
    }

}
