package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Entity.Standard;
import com.school.Complete.Structure.of.a.School.Entity.Student;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Repository.CourseRepository;
import com.school.Complete.Structure.of.a.School.Repository.StandardRepository;
import com.school.Complete.Structure.of.a.School.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StandardRepository standardRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addCourseToStudent(Long stu_id, Long cou_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Optional<Student> request_student = studentRepository.findById(stu_id);
        Optional<Course> request_course = courseRepository.findById(cou_id);

        if(request_student.isPresent()){
            if(request_course.isPresent()){
                Student student = request_student.get();
                Course course = request_course.get();
                if(student.isPresent(course)){
                    throw new ObjectAlreadyExistsException("Course with ID " + cou_id + " already exists.");
                }
                else{
                    student.addCourse(course);
                    studentRepository.save(student);
                }
            }
            else{
                throw new ObjectNotFoundException("No such course with id: " + cou_id);
            }
        }
        else{
            throw new ObjectNotFoundException("No such student with id: " + stu_id);
        }


    }

    @Override
    public void deleteStudentById(Long id) throws ObjectNotFoundException {
        Optional<Student> request_student = studentRepository.findById(id);
        if(request_student.isPresent()){
            studentRepository.deleteById(id);
        }
        else{
            throw new ObjectNotFoundException("No such student with id: " + id);
        }
    }

    @Override
    public Student fetchStudentById(Long id) throws ObjectNotFoundException {
        Optional<Student> request_student = studentRepository.findById(id);
        if(request_student.isPresent()){
            return request_student.get();
        }
        else{
            throw new ObjectNotFoundException("No such student with id: " + id);
        }

    }

    @Override
    public void updateStudentById(Long id, Student student) throws ObjectNotFoundException {
        Optional<Student> request_student = studentRepository.findById(id);
        if(request_student.isPresent()){
            Student s = request_student.get();
            if(student.getStudentGender() != '\0'){s.setStudentGender(student.getStudentGender());}
            if(student.getStudentName() != null){s.setStudentName(student.getStudentName());}
            studentRepository.save(s);
        }
        else{
            throw new ObjectNotFoundException("No such student with id: " + id);
        }
    }

    @Override
    public void addStandardtoStudent(Long stuId, Long stanId) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Optional<Student> request_student = studentRepository.findById(stuId);
        Optional<Standard> request_standard = standardRepository.findById(stanId);

        if(request_student.isPresent()){
            if(request_standard.isPresent()){
                Student student = request_student.get();
                Standard standard = request_standard.get();
                student.setStandard(standard);
                studentRepository.save(student);

                if(!standard.isPresent(student)){
                    standard.addStudent(student);
                    standardRepository.save(standard);
                }
            }
            else{
                throw new ObjectNotFoundException("No such standard with id: " + stanId);
            }
        }
        else{
            throw new ObjectNotFoundException("No such student with id: " + stuId);
        }
    }
}
