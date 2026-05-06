package com.example.StudentManagement_demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String course;
    private LocalDate createdDate;
}
