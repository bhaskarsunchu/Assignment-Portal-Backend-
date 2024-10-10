package com.scaler.backendinternassignment.advice;

import com.scaler.backendinternassignment.dtos.ErrorDto;
import com.scaler.backendinternassignment.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Class consisting all the Exception Handling methods
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Invalid input: " + exception.getMessage());
        errorDto.setCode("INVALID_INPUT");

        // Return 400 Bad Request for invalid input
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handlePasswordNotMatchingException(PasswordNotMatchingException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Invalid input: " + exception.getMessage());
        errorDto.setCode("INVALID_INPUT");

        // Return 400 Bad Request for invalid input
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleInvalidTokenException(InvalidTokenException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Invalid input: " + exception.getMessage());
        errorDto.setCode("INVALID_INPUT");

        // Return 400 Bad Request for invalid input
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleAdminNotFoundException(AdminNotFoundException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Invalid input: " + exception.getMessage());
        errorDto.setCode("INVALID_INPUT");

        // Return 400 Bad Request for invalid input
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleAssignmentNotFoundException(AssignmentNotFoundException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Invalid input: " + exception.getMessage());
        errorDto.setCode("INVALID_INPUT");

        // Return 400 Bad Request for invalid input
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleInvalidStatusException(InvalidStatusException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Invalid input: " + exception.getMessage());
        errorDto.setCode("INVALID_INPUT");

        // Return 400 Bad Request for invalid input
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
