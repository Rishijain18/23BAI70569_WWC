package com.example.studentapi.service;

import com.example.studentapi.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final Map<Integer, Student> studentMap = new HashMap<>();

    // Register student
    public Student addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            return null;
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    // Get student by ID
    public Student getStudentById(int id) {
        return studentMap.get(id);
    }

    // Delete student
    public boolean deleteStudent(int id) {
        return studentMap.remove(id) != null;
    }
}
