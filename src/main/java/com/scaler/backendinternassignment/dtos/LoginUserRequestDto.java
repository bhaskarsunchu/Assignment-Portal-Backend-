package com.scaler.backendinternassignment.dtos;

import lombok.Data;

@Data
public class LoginUserRequestDto {
    private String email;
    private String password;
}
