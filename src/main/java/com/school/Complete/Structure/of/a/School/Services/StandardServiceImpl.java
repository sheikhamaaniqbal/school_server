package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Standard;
import com.school.Complete.Structure.of.a.School.Entity.Student;
import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Repository.StandardRepository;
import com.school.Complete.Structure.of.a.School.Repository.StudentRepository;
import com.school.Complete.Structure.of.a.School.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandardServiceImpl implements StandardService{

    @Autowired
    private StandardRepository standardRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addStandard(Standard standard) {
        standardRepository.save(standard);
    }

    @Override
    public List<Standard> fetchAllStandards() {
        return standardRepository.findAll();
    }

    @Override
    public void addHomeroomToStandard(Long stan_id, Long tea_id) throws ObjectNotFoundException {
        Optional<Standard> request_standard = standardRepository.findById(stan_id);
        if(request_standard.isPresent()){
            Optional<Teacher> request_teacher = teacherRepository.findById(tea_id);
            if(request_teacher.isPresent()){
                Standard standard = request_standard.get();
                Teacher teacher = request_teacher.get();
                standard.addHomeroom(teacher);
                standardRepository.save(standard);
            }
            else{
                throw new ObjectNotFoundException("No such teacher with ID: " + tea_id);
            }
        }
        else{
            throw new ObjectNotFoundException("No such standard with ID: " + stan_id);
        }

    }

    @Override
    public void addStudentToStandard(Long stan_id, Long stu_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {

        Optional<Standard> request_standard = standardRepository.findById(stan_id);
        if(request_standard.isPresent()){
            Optional<Student> request_student = studentRepository.findById(stu_id);
            if(request_student.isPresent()){
                Standard standard = request_standard.get();
                Student student = request_student.get();
                if(standard.isPresent(student)){
                    throw new ObjectAlreadyExistsException("Student with ID " + stu_id + " already exists.");
                }
                else{
                    standard.addStudent(student);
                    standardRepository.save(standard);
                    student.setStandard(standard);
                    studentRepository.save(student);
                }
            }
            else{
                throw new ObjectNotFoundException("No such student with ID: " + stu_id);
            }
        }
        else{
            throw new ObjectNotFoundException("No such standard with ID: " + stan_id);
        }
    }

    @Override
    public void deleteStandardById(Long id) throws ObjectNotFoundException {
        Optional<Standard> request_standard = standardRepository.findById(id);
        if(request_standard.isPresent()){
            standardRepository.deleteById(id);
        }
        else{
            throw new ObjectNotFoundException("No such standard with ID: " + id);
        }

    }
}
