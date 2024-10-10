package com.scaler.backendinternassignment.dtos;

import com.scaler.backendinternassignment.models.User;
import lombok.Data;

@Data
public class ValidateTokenResponseDto {
    private String userName;
    private String email;
    private String role;

    public static ValidateTokenResponseDto from(User user){
        ValidateTokenResponseDto responseDto = new ValidateTokenResponseDto();
        responseDto.setUserName(user.getUserName());
        responseDto.setEmail(user.getEmail());
        responseDto.setRole(user.getRole().name());
        return responseDto;
    }
}
