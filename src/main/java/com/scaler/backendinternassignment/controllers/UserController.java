package com.scaler.backendinternassignment.controllers;

import com.scaler.backendinternassignment.dtos.AssignmentUploadRequestDto;
import com.scaler.backendinternassignment.dtos.AssignmentUploadResponseDto;
import com.scaler.backendinternassignment.dtos.UserDto;
import com.scaler.backendinternassignment.exceptions.AdminNotFoundException;
import com.scaler.backendinternassignment.models.Assignment;
import com.scaler.backendinternassignment.models.User;
import com.scaler.backendinternassignment.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// Maps requests to /users to this controller
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    // Constructor for dependency injections
    public UserController(UserService userService){
        this.userService = userService;
    }

    // Endpoint for uploading an assignment
    @PostMapping("/upload")
    public AssignmentUploadResponseDto uploadAssignment(@RequestBody AssignmentUploadRequestDto requestDto) throws AdminNotFoundException {
        return userService.uploadAssignment(requestDto.getUserId(),
                requestDto.getTask(), requestDto.getAdmin());
    }

    // Endpoint for fetching all admins
    @GetMapping("/admins")
    public List<UserDto> fetchAllAdmins(){
        List<User> users = userService.fetchAllAdmins();

        return users.stream()
                .map(UserDto::from) // Utilize the static method from UserDto
                .collect(Collectors.toList());
    }
}
