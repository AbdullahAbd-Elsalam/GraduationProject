package com.springjwt.springjwt2.auth;

import com.springjwt.springjwt2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

     
    private String email;
    private String password;
    private Role role;
}
