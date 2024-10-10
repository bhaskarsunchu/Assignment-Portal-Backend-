package com.scaler.backendinternassignment.controllers;

import com.scaler.backendinternassignment.dtos.*;
import com.scaler.backendinternassignment.exceptions.InvalidTokenException;
import com.scaler.backendinternassignment.exceptions.PasswordNotMatchingException;
import com.scaler.backendinternassignment.exceptions.UserNotFoundException;
import com.scaler.backendinternassignment.models.Token;
import com.scaler.backendinternassignment.models.User;
import com.scaler.backendinternassignment.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Maps requests to /auth to this controller
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    // Constructor for dependency injections
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    // Endpoint for new user registration
    @PostMapping("/register")
    public RegisteredUserResponseDto register(@RequestBody RegisterUserRequestDto requestDto){

        User user = authenticationService.signUp(
                requestDto.getUserName(),
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getRole()
        );

        return RegisteredUserResponseDto.from(user);
    }

    // Endpoint for the existing user login
    @PostMapping("/login")
    public LoginUserResponseDto login(@RequestBody LoginUserRequestDto requestDto) throws UserNotFoundException, PasswordNotMatchingException {
        Token token = authenticationService.login(requestDto.getEmail(),
                requestDto.getPassword());
        LoginUserResponseDto responseDto = new LoginUserResponseDto();
        responseDto.setEmail(requestDto.getEmail());
        responseDto.setToken(token.getValue());
        return responseDto;
    }

    // Endpoint for validating the token
    @GetMapping("/validate")
    public ResponseEntity<ValidateTokenResponseDto> validateToken(@RequestHeader("Authorization") String authorizationHeader) throws InvalidTokenException {
        // Extract the token from the Authorization header
        String token = authorizationHeader.replace("Bearer ", ""); // Remove "Bearer " prefix
        User user = authenticationService.validateToken(token);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ValidateTokenResponseDto.from(user), HttpStatus.OK);
    }
}
