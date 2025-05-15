package com.example.test.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "address", length = 45)
    private String address;

    public enum Gender {
        MALE,
        FEMALE
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", length = 10)
    private Gender sex;
}
