package com.example.StudentManagement_demo.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.StudentManagement_demo.dto.StudentDTO;
import com.example.StudentManagement_demo.dto.StudentResponseDTO;
import com.example.StudentManagement_demo.entity.Student;
import com.example.StudentManagement_demo.repository.StudentRepository;
import com.example.StudentManagement_demo.repository.UserRepository;

// Contains business logic of the application
// Connects controller with repository

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repo;
    private final UserRepository userRepo;

    public StudentService(StudentRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    //ADD STUDENT
    public StudentResponseDTO add(StudentDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Adding student for user: {}", username);

        var user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student s = new Student();
        s.setName(dto.getName());
        s.setAge(dto.getAge());
        s.setEmail(dto.getEmail());
        s.setCourse(dto.getCourse());
        s.setCreatedDate(LocalDate.now());
        s.setUser(user);

        return map(repo.save(s));
    }

    //GET ALL WITH PAGINATION (ONLY METHOD)
    public List<StudentResponseDTO> getAll(int page, int size) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Fetching students for user: {}, page: {}, size: {}", username, page, size);

        return repo.findByUserUsername(username, PageRequest.of(page, size))
                .stream()
                .map(this::map)
                .toList();
    }

    //DELETE
    public String deleteStudent(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Student student = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!student.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        repo.delete(student);

        log.info("Student deleted: {}", id);

        return "Student deleted successfully";
    }

    //UPDATE
    public StudentResponseDTO update(Long id, StudentDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Student student = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!student.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());

        log.info("Student updated: {}", id);

        return map(repo.save(student));
    }

    //MAPPER
    private StudentResponseDTO map(Student s) {
        StudentResponseDTO d = new StudentResponseDTO();
        d.setId(s.getId());
        d.setName(s.getName());
        d.setAge(s.getAge());
        d.setEmail(s.getEmail());
        d.setCourse(s.getCourse());
        d.setCreatedDate(s.getCreatedDate());
        return d;
    }

    public List<StudentResponseDTO> getAllAdmin() {
        return repo.findAll()
                .stream()
                .map(this::map)
                .toList();
    }
}