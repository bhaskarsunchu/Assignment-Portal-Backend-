package com.scaler.backendinternassignment.services;

import com.scaler.backendinternassignment.dtos.AssignmentDto;
import com.scaler.backendinternassignment.exceptions.AdminNotFoundException;
import com.scaler.backendinternassignment.exceptions.AssignmentNotFoundException;
import com.scaler.backendinternassignment.exceptions.InvalidStatusException;
import com.scaler.backendinternassignment.models.Assignment;
import com.scaler.backendinternassignment.models.AssignmentStatus;
import com.scaler.backendinternassignment.models.User;
import com.scaler.backendinternassignment.repositories.AssignmentRepository;
import com.scaler.backendinternassignment.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service class for handling admin-related operations
@Service
public class AdminService {

    private AssignmentRepository assignmentRepository;

    private UserRepository userRepository;

    // Constructor for dependency injection
    public AdminService(AssignmentRepository assignmentRepository,
                        UserRepository userRepository){
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
    }

    public List<AssignmentDto> fetchAllTaggedAssignments(String adminId) throws AdminNotFoundException {
        List<Assignment> assignments = assignmentRepository.findByAdminId(adminId);
        Optional<User> optionalAdmin = userRepository.findById(adminId);
        if (optionalAdmin.isEmpty()){
            throw new AdminNotFoundException("Admin not found with the Id you provided. Please recheck");
        }
        String adminName = optionalAdmin.get().getUserName();
        List<AssignmentDto> assignmentDtos = new ArrayList<>();
        for(Assignment assignment : assignments){
            String userName = "";
            Optional<User> optionalUser = userRepository.findById(assignment.getUserId());
            if(!(optionalUser.isEmpty())){
                userName = userName + optionalUser.get().getUserName();
            }
            assignmentDtos.add(AssignmentDto.from(assignment, userName, adminName));
        }
        return assignmentDtos;
    }

    public AssignmentDto validateAssignment(String assignMentId, String status) throws AssignmentNotFoundException, InvalidStatusException {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignMentId);
        if(optionalAssignment.isEmpty()){
            throw new AssignmentNotFoundException("Assignment not found with the Id you provided. Please recheck");
        }
        AssignmentStatus assignmentStatus;
        try {
            assignmentStatus = AssignmentStatus.valueOf(status.toUpperCase()); // Throws IllegalArgumentException if not valid
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid status: " + status + ". Allowed values are: PENDING, ACCEPTED, REJECTED.");
        }
        optionalAssignment.get().setStatus(assignmentStatus);
        Assignment updatedAssignment = assignmentRepository.save(optionalAssignment.get());
        Optional<User> optionalUser = userRepository.findById(updatedAssignment.getUserId());
        Optional<User> optionalAdmin = userRepository.findById(updatedAssignment.getAdminId());
        return AssignmentDto.from(updatedAssignment, optionalUser.get().getUserName(), optionalAdmin.get().getUserName());
    }
}
