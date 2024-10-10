package com.scaler.backendinternassignment.dtos;

import lombok.Data;

@Data
public class LoginUserResponseDto {
    private String email;
    private String token;
}
