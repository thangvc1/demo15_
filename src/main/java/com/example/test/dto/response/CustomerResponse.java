package com.example.test.dto.response;

import com.example.test.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerResponse {
    private Long id;
    private String name;
    private String address;
    private String sex;
}
