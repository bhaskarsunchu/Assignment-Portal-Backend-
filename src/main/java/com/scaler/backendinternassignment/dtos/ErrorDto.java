package com.scaler.backendinternassignment.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDto {
    private String message;
    private String code;
}
