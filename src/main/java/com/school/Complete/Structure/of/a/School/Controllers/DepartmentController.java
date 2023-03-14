package com.school.Complete.Structure.of.a.School.Controllers;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Entity.Department;
import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public List<Department> fetchAllDepartments(){
        return departmentService.fetchAllDepartments();
    }

    @GetMapping("/department/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return departmentService.fetchDepartmentById(id);
    }

    @PostMapping("/department")
    public Department addDepartment(@RequestBody @Valid Department department){
        return departmentService.addDepartment(department);
    }

    @DeleteMapping("/department/{id}")
    public void deleteDepartmentById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        departmentService.deleteDepartmentById(id);
    }

    @PutMapping("/department/course/{dep_id}/{cou_id}")
    public void addCourseToDepartment(@PathVariable("dep_id") Long dep_id, @PathVariable("cou_id") Long cou_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        departmentService.addCourseToDepartment(dep_id,cou_id);
    }

    @PutMapping("/department/teacher/{dep_id}/{tea_id}")
    public void addTeacherToDepartment(@PathVariable("dep_id") Long dep_id, @PathVariable("tea_id") Long tea_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        departmentService.addTeacherToDepartment(dep_id,tea_id);
    }

    @DeleteMapping("/department/course/{dep_id}/{cou_id}")
    public void deleteCourseFromDepartment(@PathVariable("dep_id") Long dep_id, @PathVariable("cou_id") Long cou_id) throws ObjectNotFoundException {
        departmentService.deleteCourseFromDepartment(dep_id,cou_id);
    }

    @DeleteMapping("/department/teacher/{dep_id}/{tea_id}")
    public void deleteTeacherFromDepartment(@PathVariable("dep_id") Long dep_id, @PathVariable("tea_id") Long tea_id) throws ObjectNotFoundException {
        departmentService.deleteTeacherFromDepartment(dep_id,tea_id);
    }

}
