package com.scaler.backendinternassignment.dtos;

import com.scaler.backendinternassignment.models.Assignment;
import com.scaler.backendinternassignment.models.AssignmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentDto {

    private String userName;
    private String adminName;
    private String task;
    private LocalDateTime timestamp;
    private AssignmentStatus status;

    // Method to convert a single Assignment to AssignmentDto
    public static AssignmentDto from(Assignment assignment, String userName, String adminName) {
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setUserName(userName);
        assignmentDto.setAdminName(adminName);
        assignmentDto.setTask(assignment.getTask());
        assignmentDto.setTimestamp(assignment.getTimestamp());
        assignmentDto.setStatus(assignment.getStatus());
        return assignmentDto;
    }

}
