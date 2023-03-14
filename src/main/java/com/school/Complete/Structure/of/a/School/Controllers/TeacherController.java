package com.school.Complete.Structure.of.a.School.Controllers;

import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher")
    public List<Teacher> fetchAllTeachers(){
        return teacherService.fetchAllTeachers();
    }

    @PostMapping("/teacher")
    public void addTeacher(@RequestBody @Valid Teacher teacher){
        teacherService.addTeacher(teacher);
    }

    @GetMapping("/teacher/{id}")
    public Teacher fetchTeacherById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return teacherService.fetchTeacherById(id);
    }

    @DeleteMapping("teacher/{id}")
    public void deleteTeacherById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        teacherService.deleteTeacherById(id);
    }

    @PutMapping("/teacher/{id}")
    public Teacher updateTeacherById(@PathVariable("id") Long id, @RequestBody Teacher teacher) throws ObjectNotFoundException {
        return teacherService.updateTeacherById(id,teacher);
    }

    @PutMapping("/teacher/department/{tea_id}/{dep_id}")
    public void addDepartmentToTeacher(@PathVariable("tea_id") Long tea_id, @PathVariable("dep_id") Long dep_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        teacherService.addDepartmentToTeacher(tea_id,dep_id);
    }
}
