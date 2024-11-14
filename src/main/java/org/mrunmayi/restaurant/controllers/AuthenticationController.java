package org.mrunmayi.restaurant.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.LoginRequest;
import org.mrunmayi.restaurant.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

        private final CustomerService customerService;

        @PostMapping("/login")
        public ResponseEntity<String> createCustomer(@RequestBody @Valid LoginRequest request) {
            return ResponseEntity.ok(customerService.login(request));
        }
}
