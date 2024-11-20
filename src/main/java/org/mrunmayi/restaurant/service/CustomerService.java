package org.mrunmayi.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.CustomerDto.CustomerRequest;
import org.mrunmayi.restaurant.dto.CustomerDto.CustomerResponse;
import org.mrunmayi.restaurant.dto.LoginRequest;
import org.mrunmayi.restaurant.entity.Customer;
import org.mrunmayi.restaurant.exception.NotFoundException;
import org.mrunmayi.restaurant.helpers.EncryptionService;
import org.mrunmayi.restaurant.helpers.JWTAuthHelper;
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
    private final JWTAuthHelper jwtAuthHelper;

    public String createCustomer(CustomerRequest.CreateRequest request) {
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
                .orElseThrow(() -> new NotFoundException(
                        format("No customer found with the provided email:: %s", email)
                ));
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomerByEmail(request.email());
        if(!encryptionService.validatePassword(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }
        return jwtHelper.generateToken(request.email());
    }

    public CustomerResponse updateCustomer(
            String email,
            CustomerRequest.UpdateRequest updateRequest
    ) {
        Customer customer = repo.findByEmail(email).orElseThrow(() -> new NotFoundException(
                format("No customer found with the provided email: %s", email)
        ));
        if(updateRequest.firstName() != null){
            customer.setFirstName(updateRequest.firstName());
        }
        if(updateRequest.lastName() != null){
            customer.setLastName(updateRequest.lastName());
        }
        if(updateRequest.addr() != null){
            customer.setAddr(updateRequest.addr());
        }
        if(updateRequest.city() != null){
            customer.setCity(updateRequest.city());
        }
        if(updateRequest.pinCode() != null){
            customer.setPinCode(updateRequest.pinCode());
        }
        repo.save(customer);
        return mapper.toCustomerResponse(customer);
    }

    public String deleteCustomer( Long id ) {
        repo.findById(id).orElseThrow(() -> new NotFoundException(
                format("No customer found with the ID %s", id)
        ));
        repo.deleteById(id);
        return "Customer Deleted Successfully";
    }

}
