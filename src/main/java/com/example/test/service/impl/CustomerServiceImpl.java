package com.example.test.service.impl;

import com.example.test.dto.request.CustomerRequest;
import com.example.test.dto.response.CustomerResponse;
import com.example.test.model.Customer;
import com.example.test.repository.CustomerRepository;
import com.example.test.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl  implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.getCustomers();
    }

    @Override
    public void addCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setSex(Customer.Gender.valueOf(customerRequest.getSex()));
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .sex(String.valueOf(customer.getSex()))
                .build();
    }

    @Override
    public void updateCustomer(Long id, CustomerRequest customerRequest) {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

            customer.setName(customerRequest.getName());
            customer.setAddress(customerRequest.getAddress());
            customer.setSex(Customer.Gender.valueOf(customerRequest.getSex()));
            customerRepository.save(customer);
    }
}
