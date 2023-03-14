package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Student;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    List<Student> fetchAllStudents();
    void addCourseToStudent(Long stuId, Long couId) throws ObjectNotFoundException, ObjectAlreadyExistsException;
    void deleteStudentById(Long id) throws ObjectNotFoundException;
    Student fetchStudentById(Long id) throws ObjectNotFoundException;
    void updateStudentById(Long id, Student student) throws ObjectNotFoundException;
    void addStandardtoStudent(Long stuId, Long stanId) throws ObjectNotFoundException, ObjectAlreadyExistsException;
}
