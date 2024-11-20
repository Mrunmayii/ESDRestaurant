package org.mrunmayi.restaurant.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.CustomerRequest;
import org.mrunmayi.restaurant.dto.CustomerResponse;
import org.mrunmayi.restaurant.entity.Customer;
import org.mrunmayi.restaurant.helpers.JWTAuthHelper;
import org.mrunmayi.restaurant.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JWTAuthHelper jwtAuthHelper;

//    @PostMapping
//    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
//        return ResponseEntity.ok(customerService.createCustomer(request));
//    }

    @GetMapping("/data")
    public ResponseEntity<CustomerResponse> getCustomersByEmail(HttpServletRequest request) {
        String email = jwtAuthHelper.checkJWT(request);
        if(email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @RequestBody @Valid CustomerRequest.UpdateRequest updateRequest,
            HttpServletRequest request
    ) {
        String email = jwtAuthHelper.checkJWT(request);
        if(email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customerService.updateCustomer(email, updateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {
        String email = jwtAuthHelper.checkJWT(request);
        if(email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
