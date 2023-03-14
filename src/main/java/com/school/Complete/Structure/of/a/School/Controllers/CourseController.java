package com.school.Complete.Structure.of.a.School.Controllers;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Services.CourseService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public Course addCourse(@RequestBody @Valid Course course){
        return courseService.addCourse(course);
    }

    @PutMapping("/course/teacher/{id}/{tea_id}")
    public void addTeacherToCourse(@PathVariable("id") Long id, @PathVariable("tea_id") Long tea_id) throws ObjectNotFoundException {
        courseService.addTeacherToCourse(id,tea_id);
    }

    @PutMapping("/course/course_material/{id}/{coumat_id}")
    public void addCourseMaterialToCourse(@PathVariable("id") Long id, @PathVariable("coumat_id") Long coumat_id) throws ObjectNotFoundException {
        courseService.addCourseMaterialToCourse(id,coumat_id);
    }

    @GetMapping("course/{id}")
    public Course fetchCourseById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return courseService.fetchCourseById(id);
    }

    @GetMapping("course")
    public List<Course> fetchAllCourses(){
        return courseService.fetchAllCourses();
    }

    @DeleteMapping("course/{id}")
    public void deleteCourseById(@NotNull @PathVariable("id") Long id) throws ObjectNotFoundException {
        courseService.deleteCourseById(id);
    }

    @DeleteMapping("/course/course_material/{cou_id}")
    public void deleteCourseMaterialFromCourse(@PathVariable("cou_id") Long cou_id) throws ObjectNotFoundException {
        courseService.deleteCourseMaterialFromCourse(cou_id);
    }

    @DeleteMapping("/course/teacher/{cou_id}")
    public void deleteTeacherMaterialFromCourse(@PathVariable("cou_id") Long cou_id) throws ObjectNotFoundException {
        courseService.deleteTeacherFromCourse(cou_id);
    }

    @PutMapping("/course/department/{cou_id}/{dep_id}")
    public void addDepartmentToCourse(@PathVariable("cou_id") Long cou_id, @PathVariable("dep_id") Long dep_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        courseService.addDepartmentToCourse(cou_id,dep_id);
    }

}
