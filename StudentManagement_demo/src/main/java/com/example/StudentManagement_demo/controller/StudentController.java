package com.example.StudentManagement_demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentManagement_demo.dto.ApiResponse;
import com.example.StudentManagement_demo.dto.StudentDTO;
import com.example.StudentManagement_demo.dto.StudentResponseDTO;
import com.example.StudentManagement_demo.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Student Management", description = "Student CRUD APIs")
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new student")
    @PostMapping
    public ApiResponse<StudentResponseDTO> add(@Valid @RequestBody StudentDTO dto) {
        return new ApiResponse<>("Student created", service.add(dto));
    }

    @Operation(summary = "Get all students with pagination")
    @GetMapping
    public ApiResponse<List<StudentResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return new ApiResponse<>("Success", service.getAll(page, size));
    }

    @Operation(summary = "Search students by name")
    @GetMapping("/search")
    public ApiResponse<List<StudentResponseDTO>> search(
            @RequestParam String name) {

        return new ApiResponse<>("Success",
                service.searchByName(name));
    }

    @Operation(summary = "Delete student by ID")
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        return new ApiResponse<>("Deleted", service.deleteStudent(id));
    }

    @Operation(summary = "Update student by ID")
    @PutMapping("/{id}")
    public ApiResponse<StudentResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO dto) {

        return new ApiResponse<>("Updated", service.update(id, dto));
    }

    @Operation(summary = "Get all students (Admin only)")
    @GetMapping("/admin/all")
    public List<StudentResponseDTO> getAllAdmin() {
        return service.getAllAdmin();
    }
}