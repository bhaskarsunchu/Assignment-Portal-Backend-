package com.scaler.backendinternassignment.dtos;

import lombok.Data;

@Data
public class AssignmentUploadRequestDto {
    private String userId;
    private String task;
    private String admin;
}
