package com.scaler.backendinternassignment.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String userName;
    private String email;
    private String password;
    private String role;
}
