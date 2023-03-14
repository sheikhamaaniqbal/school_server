package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;

import java.util.List;

public interface CourseService {
    Course addCourse(Course course);
    Course fetchCourseById(Long id) throws ObjectNotFoundException;
    List<Course> fetchAllCourses();
    void deleteCourseById(Long id) throws ObjectNotFoundException;
    void deleteCourseMaterialFromCourse(Long couId) throws ObjectNotFoundException;
    void deleteTeacherFromCourse(Long couId) throws ObjectNotFoundException;
    void addTeacherToCourse(Long id, Long teaId) throws ObjectNotFoundException;
    void addCourseMaterialToCourse(Long id, Long coumatId) throws ObjectNotFoundException;
    void addDepartmentToCourse(Long couId, Long depId) throws ObjectNotFoundException, ObjectAlreadyExistsException;
}
