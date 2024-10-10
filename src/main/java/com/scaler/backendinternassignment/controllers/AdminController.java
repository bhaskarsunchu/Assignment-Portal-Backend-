package com.scaler.backendinternassignment.controllers;

import com.scaler.backendinternassignment.dtos.AssignmentDto;
import com.scaler.backendinternassignment.exceptions.AdminNotFoundException;
import com.scaler.backendinternassignment.exceptions.AssignmentNotFoundException;
import com.scaler.backendinternassignment.exceptions.InvalidStatusException;
import com.scaler.backendinternassignment.models.Assignment;
import com.scaler.backendinternassignment.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Maps requests to /admins to this controller
@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService adminService;

    // Constructor for dependency injections
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    // Endpoint to fetch all assignments tagged to a specific admin
    @GetMapping("/assignments/{adminId}")
    public List<AssignmentDto> fetchAllTaggedAssignments(@PathVariable String adminId) throws AdminNotFoundException {
        return adminService.fetchAllTaggedAssignments(adminId);
    }

    // Endpoint to validate an assignment (ACCEPT or REJECT)
    @PutMapping("/assignments/{id}/{status}")
    public AssignmentDto validateAssignment(@PathVariable String id,
                                            @PathVariable String status) throws AssignmentNotFoundException, InvalidStatusException {
        return adminService.validateAssignment(id, status);
    }
}
