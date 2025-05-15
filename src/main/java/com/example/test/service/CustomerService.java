package com.example.test.service;

import com.example.test.dto.request.CustomerRequest;
import com.example.test.dto.response.CustomerResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAllCustomers();

    void addCustomer(@Valid CustomerRequest customerRequest);

    void deleteCustomer(Long id);

    CustomerResponse getCustomerById(Long id);

    void updateCustomer(Long id, @Valid CustomerRequest customerRequest);
}
