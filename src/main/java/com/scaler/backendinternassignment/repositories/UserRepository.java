package com.scaler.backendinternassignment.repositories;

import com.scaler.backendinternassignment.models.Role;
import com.scaler.backendinternassignment.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository interface for managing User/Admin entities in the MongoDB database
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    //User save(User user);

    User findByEmail(String email);

    Optional<User> findById(String id);

    Optional<User> findByUserNameAndRole(String userName, Role role);

    List<User> findByRole(Role role);

}
