package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Entity.Department;
import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;

import java.util.List;

public interface DepartmentService {
    List<Department> fetchAllDepartments();
    Department addDepartment(Department department);
    void deleteDepartmentById(Long id) throws ObjectNotFoundException;
    void addCourseToDepartment(Long dep_id, Long cou_id) throws ObjectNotFoundException, ObjectAlreadyExistsException;
    void addTeacherToDepartment(Long dep_id, Long tea_id) throws ObjectNotFoundException, ObjectAlreadyExistsException;
    Department fetchDepartmentById(Long id) throws ObjectNotFoundException;
    void deleteCourseFromDepartment(Long depId, Long couId) throws ObjectNotFoundException;
    void deleteTeacherFromDepartment(Long depId, Long teaId) throws ObjectNotFoundException;
}
