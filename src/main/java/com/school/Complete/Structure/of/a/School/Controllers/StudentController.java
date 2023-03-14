package com.school.Complete.Structure.of.a.School.Controllers;

import com.electronwill.nightconfig.core.conversion.Path;
import com.school.Complete.Structure.of.a.School.Entity.Student;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public void addStudent(@RequestBody @Valid Student student){
        studentService.addStudent(student);
    }

    @GetMapping("/student")
    public List<Student> fetchAllStudents(){
        return studentService.fetchAllStudents();
    }

    @PutMapping("/student/course/{stu_id}/{cou_id}")
    public void addCourseToStudent(@PathVariable("stu_id") Long stu_id, @PathVariable("cou_id") Long cou_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        studentService.addCourseToStudent(stu_id, cou_id);
    }

    @DeleteMapping("student/{id}")
    public void deleteStudentById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        studentService.deleteStudentById(id);
    }

    @GetMapping("student/{id}")
    public Student fetchStudentById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return studentService.fetchStudentById(id);
    }

    @PutMapping("student/{id}")
    public void updateStudentById(@PathVariable("id") Long id, @RequestBody Student student) throws ObjectNotFoundException {
        studentService.updateStudentById(id,student);
    }

    @PutMapping("student/standard/{stu_id}/{stan_id}")
    public void addStandardtoStudent(@PathVariable("stu_id") Long stu_id, @PathVariable("stan_id") Long stan_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        studentService.addStandardtoStudent(stu_id,stan_id);
    }
}
