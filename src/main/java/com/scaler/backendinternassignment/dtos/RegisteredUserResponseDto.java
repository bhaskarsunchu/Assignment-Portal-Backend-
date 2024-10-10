package com.scaler.backendinternassignment.dtos;

import com.scaler.backendinternassignment.models.User;
import lombok.Data;

@Data
public class RegisteredUserResponseDto {
    private String userName;
    private String role;
    private String message;

    public static RegisteredUserResponseDto from(User user){
        if (user == null)   return null;

        RegisteredUserResponseDto responseDto = new RegisteredUserResponseDto();
        responseDto.setUserName(user.getUserName());
        responseDto.setRole(user.getRole().name());
        responseDto.setMessage("Hello, " +user.getUserName()+ ". Thank you for registering with our platform.");

        return responseDto;
    }
}
