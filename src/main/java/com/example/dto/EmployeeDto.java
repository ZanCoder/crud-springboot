package com.example.dto;

import com.example.Entity.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String avatar;
    private Set<Role> roles = new HashSet<>();
}
