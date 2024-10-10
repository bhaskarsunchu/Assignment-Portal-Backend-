package com.scaler.backendinternassignment.dtos;

import com.scaler.backendinternassignment.models.User;
import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String email;
    private String role;

    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        return userDto;
    }
}
