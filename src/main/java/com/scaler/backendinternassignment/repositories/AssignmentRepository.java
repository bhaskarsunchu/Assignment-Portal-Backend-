package com.scaler.backendinternassignment.repositories;

import com.scaler.backendinternassignment.models.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository interface for managing Assignment entities in the MongoDB database
@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    Assignment save(Assignment assignment);

    List<Assignment> findByAdminId(String adminId);
}
