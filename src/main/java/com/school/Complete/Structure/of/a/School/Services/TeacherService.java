package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    List<Teacher> fetchAllTeachers();

    void addTeacher(Teacher teacher);

    Teacher fetchTeacherById(Long id) throws ObjectNotFoundException;

    void deleteTeacherById(Long id) throws ObjectNotFoundException;

    Teacher updateTeacherById(Long id, Teacher teacher) throws ObjectNotFoundException;

    void addDepartmentToTeacher(Long teaId, Long depId) throws ObjectNotFoundException, ObjectAlreadyExistsException;
}
