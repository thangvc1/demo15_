package com.example.test.dto.request;

import com.example.test.dto.response.CustomerResponse;
import com.example.test.model.Customer;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {

        private Long id;

        @NotBlank(message = "Customer name must not be blank")
        private String name;

        @NotBlank(message = "Address must not be blank")
        private String address;

        private String sex;

        public CustomerRequest(CustomerResponse response) {
                this.id = response.getId();
                this.name = response.getName();
                this.address = response.getAddress();
                this.sex = String.valueOf(Customer.Gender.valueOf(response.getSex().toUpperCase()));
        }
}
