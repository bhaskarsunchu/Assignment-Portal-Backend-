package com.scaler.backendinternassignment.repositories;

import com.scaler.backendinternassignment.models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

// Repository interface for managing Token entities in the MongoDB database
@Repository
public interface TokenRepository extends MongoRepository<Token,String> {
    Token findByValueAndExpiryAtGreaterThan(String value, Date date);
}
