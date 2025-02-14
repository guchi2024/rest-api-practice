package com.sparta.restapipractice.controller;

import com.sparta.restapipractice.db.StudentMap;
import com.sparta.restapipractice.dto.StudentRequestDto;
import com.sparta.restapipractice.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentMap studentMap;

    public StudentController(StudentMap studentMap) {
        this.studentMap = studentMap;
    }

    // 학생 등록
    // (POST) /student
    @PostMapping
    public ResponseEntity<?> addStudent(
            @RequestBody StudentRequestDto studentRequestDto) {
        this.studentMap.add(studentRequestDto.getId(), studentRequestDto);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("학생 등록이 완료되었습니다");
    }

    // id로 학생 조회
    // (GET) /students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = this.studentMap.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body((student));
    }

//    @GetMapping
//    public ResponseEntity<?> getStudents() {
//        List<Student> studentList = this.studentMap.getAll();
//        return ResponseEntity.status(HttpStatus.OK).body(studentList);
//    }

    // 학생 전체 조회
    @GetMapping
    public ResponseEntity<?> getStudents(
            @RequestParam(value = "number", required = false) String studentNumber) {
        if (StringUtils.hasLength(studentNumber)) {
            Student student = this.studentMap.getByNumber(studentNumber);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }

        List<Student> studentList = this.studentMap.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }
}
