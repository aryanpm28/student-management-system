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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<StudentResponseDTO> add(@Valid @RequestBody StudentDTO dto) {
        return new ApiResponse<>("Student created", service.add(dto));
    }

    @GetMapping
    public ApiResponse<List<StudentResponseDTO>> getAll(
            @RequestParam int page,
            @RequestParam int size) {

        return new ApiResponse<>("Success", service.getAll(page, size));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        return new ApiResponse<>("Deleted", service.deleteStudent(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<StudentResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO dto) {

        return new ApiResponse<>("Updated", service.update(id, dto));
    }

    @GetMapping("/admin/all")
    public List<StudentResponseDTO> getAllAdmin() {
        return service.getAllAdmin();
    }
}