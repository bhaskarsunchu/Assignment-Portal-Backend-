package com.scaler.backendinternassignment.dtos;

import com.scaler.backendinternassignment.models.Assignment;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AssignmentUploadResponseDto {
    private String userName;
    private String admin;
    private LocalDateTime timestamp;
    private String message;

}
