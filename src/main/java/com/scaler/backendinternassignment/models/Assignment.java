package com.scaler.backendinternassignment.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// Model representing an assignment entity in the database
@Data
@Document(collection = "assignments")
public class Assignment {
    @Id
    private String id;
    private String userId;
    private String adminId;
    private String task;
    private LocalDateTime timestamp;
    private AssignmentStatus status;
}
