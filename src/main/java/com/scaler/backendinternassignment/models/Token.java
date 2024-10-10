package com.scaler.backendinternassignment.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

// Represents the Token model for the MongoDB tokens collection
@Data
@Document("tokens")
public class Token {
    private String value;
    private String userId;
    private Date expiryAt;
}
