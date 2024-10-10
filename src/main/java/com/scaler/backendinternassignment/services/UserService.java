package com.scaler.backendinternassignment.services;

import com.scaler.backendinternassignment.dtos.AssignmentUploadResponseDto;
import com.scaler.backendinternassignment.exceptions.AdminNotFoundException;
import com.scaler.backendinternassignment.models.Assignment;
import com.scaler.backendinternassignment.models.AssignmentStatus;
import com.scaler.backendinternassignment.models.Role;
import com.scaler.backendinternassignment.models.User;
import com.scaler.backendinternassignment.repositories.AssignmentRepository;
import com.scaler.backendinternassignment.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Service class for handling user-related operations
@Service
public class UserService {

    private UserRepository userRepository;
    private AssignmentRepository assignmentRepository;

    // Constructor for dependency injection
    public UserService(UserRepository userRepository,
                       AssignmentRepository assignmentRepository){
        this.userRepository = userRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public AssignmentUploadResponseDto uploadAssignment(String userId, String task, String admin) throws AdminNotFoundException {

        Optional<User> optionalAdmin = userRepository.findByUserNameAndRole(admin, Role.ADMIN);
        if(optionalAdmin.isEmpty()){
            throw new AdminNotFoundException("Admin not found with the Id you provided. Please recheck and provide a valid Admin Id");
        }

        Optional<User> user = userRepository.findById(userId);
        Assignment assignmentToBeUpload = new Assignment();
        assignmentToBeUpload.setUserId(user.get().getId());
        assignmentToBeUpload.setAdminId(optionalAdmin.get().getId());
        assignmentToBeUpload.setTask(task);
        assignmentToBeUpload.setTimestamp(LocalDateTime.now());
        assignmentToBeUpload.setStatus(AssignmentStatus.PENDING);

        Assignment uploadedAssignment = assignmentRepository.save(assignmentToBeUpload);

        AssignmentUploadResponseDto responseDto = new AssignmentUploadResponseDto();
        responseDto.setUserName(user.get().getUserName());
        responseDto.setAdmin(optionalAdmin.get().getUserName());
        responseDto.setTimestamp(uploadedAssignment.getTimestamp());
        responseDto.setMessage("Dear " + user.get().getUserName() +
                ", your Assignment is Successfully Submitted to ADMIN - " + optionalAdmin.get().getUserName() +
                " at " + LocalDateTime.now());

        return responseDto;
    }

    public List<User> fetchAllAdmins(){
        return userRepository.findByRole(Role.ADMIN);
    }
}
