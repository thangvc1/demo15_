package com.example.test.controller;

import com.example.test.dto.request.CustomerRequest;
import com.example.test.dto.response.CustomerResponse;
import com.example.test.model.Customer;
import com.example.test.repository.CustomerRepository;
import com.example.test.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String getAllCustomers(Model model) {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "/index";
    }

    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customerRequest", new CustomerRequest());
        model.addAttribute("genders", Arrays.asList(Customer.Gender.MALE, Customer.Gender.FEMALE));
        return "/add";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute @Valid CustomerRequest customerRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genders", Arrays.asList(Customer.Gender.MALE, Customer.Gender.FEMALE));
            return "/add";
        }
        customerService.addCustomer(customerRequest);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        CustomerResponse customer = customerService.getCustomerById(id);
        CustomerRequest customerRequest = new CustomerRequest(customer);

        model.addAttribute("customerRequest", customerRequest);
        model.addAttribute("genders", Arrays.asList(Customer.Gender.MALE, Customer.Gender.FEMALE));
        return "update"; // file edit.html
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id,
                                 @ModelAttribute @Valid CustomerRequest customerRequest,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genders", Arrays.asList(Customer.Gender.MALE, Customer.Gender.FEMALE));
            return "/update";
        }
        customerService.updateCustomer(id, customerRequest);
        return "redirect:/customers";
    }
}
