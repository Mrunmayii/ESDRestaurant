package org.mrunmayi.restaurant.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.CustomerResponse;
import org.mrunmayi.restaurant.helpers.JWTAuthHelper;
import org.mrunmayi.restaurant.service.CustomerService;
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
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }
}
