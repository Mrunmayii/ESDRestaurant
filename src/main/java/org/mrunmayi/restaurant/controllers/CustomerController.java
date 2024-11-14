package org.mrunmayi.restaurant.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.CustomerRequest;
import org.mrunmayi.restaurant.dto.CustomerResponse;
import org.mrunmayi.restaurant.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomersByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }
}
