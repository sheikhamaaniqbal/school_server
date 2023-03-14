package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Department;
import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Repository.DepartmentRepository;
import com.school.Complete.Structure.of.a.School.Repository.TeacherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Teacher> fetchAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher fetchTeacherById(Long id) throws ObjectNotFoundException {
        Optional<Teacher> request_teacher = teacherRepository.findById(id);
        if(request_teacher.isPresent()){
            return request_teacher.get();
        }
        else{
            throw new ObjectNotFoundException("No such teacher with ID: " + id);
        }
    }

    @Override
    public void deleteTeacherById(Long id) throws ObjectNotFoundException {
        Optional<Teacher> request_teacher = teacherRepository.findById(id);
        if(request_teacher.isPresent()){
            teacherRepository.deleteById(id);
        }
        else{
            throw new ObjectNotFoundException("No such teacher with ID: " + id);
        }

    }

    @Override
    public Teacher updateTeacherById(Long id, @NotNull Teacher teacher) throws ObjectNotFoundException {
        Optional<Teacher> request_teacher = teacherRepository.findById(id);
        if(request_teacher.isPresent()){
            Teacher t = request_teacher.get();
            if(teacher.getTeacherGender() != '\0'){t.setTeacherGender(teacher.getTeacherGender());}
            if(teacher.getTeacherName() != null){t.setTeacherName(teacher.getTeacherName());}
            return teacherRepository.save(t);
        }
        else{
            throw new ObjectNotFoundException("No such teacher with ID: " + id);
        }
    }

    @Override
    public void addDepartmentToTeacher(Long teaId, Long depId) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Optional<Teacher> request_teacher = teacherRepository.findById(teaId);
        Optional<Department> request_department = departmentRepository.findById(depId);
        if(request_teacher.isPresent()){
            if(request_department.isPresent()){
                Department department = request_department.get();
                Teacher teacher = request_teacher.get();
                teacher.setDepartment(department);
                teacherRepository.save(teacher);

                if(!department.isPresent(teacher)){
                    department.addTeacher(teacher);
                    departmentRepository.save(department);
                }
            }
            else{
                throw new ObjectNotFoundException("No Department with ID: " + depId);
            }
        }
        else{
            throw new ObjectNotFoundException("No Teacher with ID: " + teaId);
        }
    }


}
