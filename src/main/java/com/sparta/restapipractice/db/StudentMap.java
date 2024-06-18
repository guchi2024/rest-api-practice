package com.sparta.restapipractice.db;

import com.sparta.restapipractice.dto.StudentRequestDto;
import com.sparta.restapipractice.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StudentMap {
    private final Map<Long, Student> studentMap;

    public StudentMap() {
        this.studentMap = new ConcurrentHashMap<>();
    }

    public void add(Long id, StudentRequestDto studentRequestDto) {
        Student student = this.converDtoToStudent(studentRequestDto);
        this.studentMap.put(id, student);
    }

    // 특정 ID 의 Student를 가져오는 메소드
    public Student getById(Long id) {
        return this.studentMap.get(id);
    }

    public List<Student> getAll() {
        return this.studentMap.values().stream().toList();
    }

    public Student getByNumber(String studentNumber) {
        // StudentList 에서 StudentNumber에 해당하는 Student을 찾는다
        List<Student> studentList = this.studentMap.values().stream()
                .filter(student -> student.getStudentNumber().equals((studentNumber)))
                .toList();

        return studentList.get(0);
    }

    // DTO를 Student로 변환
    private Student converDtoToStudent(StudentRequestDto studentRequestDto) {
        return new Student(
                studentRequestDto.getId(),
                studentRequestDto.getStudentNumber(),
                studentRequestDto.getName(),
                studentRequestDto.getEmail()
        );
    }
}
