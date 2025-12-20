package com.example.studentapi.controller;

import com.example.studentapi.model.Student;
import com.example.studentapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ===============================
    // ADD STUDENT USING PATH VARIABLES
    // ===============================
    // Example:
    // POST http://localhost:8080/students/1/Rishi/SpringBoot
    @PostMapping("/{id}/{name}/{course}")
    public ResponseEntity<?> addStudentByPath(
            @PathVariable int id,
            @PathVariable String name,
            @PathVariable String course) {

        if (name.isEmpty() || course.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Name and course must not be empty");
        }

        Student student = new Student(id, name, course);
        Student saved = service.addStudent(student);

        if (saved == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Student with this ID already exists");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // ===============================
    // ADD STUDENT USING QUERY PARAMETERS
    // ===============================
    // Example:
    // POST http://localhost:8080/students/register?id=2&name=Aman&course=Java
    @PostMapping("/register")
    public ResponseEntity<?> addStudentByQuery(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String course) {

        if (name.isEmpty() || course.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Name and course must not be empty");
        }

        Student student = new Student(id, name, course);
        Student saved = service.addStudent(student);

        if (saved == null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Student with this ID already exists");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // ===============================
    // GET ALL STUDENTS
    // ===============================
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // ===============================
    // GET STUDENT BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Student student = service.getStudentById(id);

        if (student == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + id);
        }

        return ResponseEntity.ok(student);
    }

    // ===============================
    // DELETE STUDENT BY ID
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        boolean deleted = service.deleteStudent(id);

        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + id);
        }

        return ResponseEntity.ok("Student deleted successfully");
    }
}
