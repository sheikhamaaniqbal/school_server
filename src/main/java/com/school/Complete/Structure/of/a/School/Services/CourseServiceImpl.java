package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import com.school.Complete.Structure.of.a.School.Entity.Department;
import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Repository.CourseMaterialRepository;
import com.school.Complete.Structure.of.a.School.Repository.CourseRepository;
import com.school.Complete.Structure.of.a.School.Repository.DepartmentRepository;
import com.school.Complete.Structure.of.a.School.Repository.TeacherRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    @Override
    public Course fetchCourseById(Long id) throws ObjectNotFoundException {
        Optional<Course> request_course = courseRepository.findById(id);
        if(request_course.isPresent()){
            return courseRepository.findById(id).get();
        }
        throw new ObjectNotFoundException("No such course with id: " + id);
    }

    @Override
    public List<Course> fetchAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourseById(Long id) throws ObjectNotFoundException {
        Optional<Course> request_course = courseRepository.findById(id);
        if(request_course.isPresent()){
            courseRepository.deleteById(id);
        }
        else{
            throw new ObjectNotFoundException("No such course with id: " + id);
        }
    }

    @Override
    public void deleteCourseMaterialFromCourse(Long cou_id) throws ObjectNotFoundException {
        Optional<Course> request_course = courseRepository.findById(cou_id);
        if(request_course.isPresent()){
            Course course = request_course.get();
            course.setCourseMaterial(null);
            courseRepository.save(course);
        }
        else{
            throw new ObjectNotFoundException("No such course with id: " + cou_id);
        }
    }

    @Override
    public void deleteTeacherFromCourse(Long cou_id) throws ObjectNotFoundException {
        Optional<Course> request_course = courseRepository.findById(cou_id);
        if(request_course.isPresent()){
            Course course = request_course.get();
            course.setTeacher(null);
            courseRepository.save(course);
        }
        else{
            throw new ObjectNotFoundException("No such course with id: " + cou_id);
        }
    }

    @Override
    public void addTeacherToCourse(Long id, Long teaId) throws ObjectNotFoundException {
        Optional<Course> request_course = courseRepository.findById(id);
        if(request_course.isPresent()){
            Course course = courseRepository.findById(id).get();
            Optional<Teacher> request_teacher = teacherRepository.findById(teaId);
            if(request_teacher.isPresent()){
                Teacher teacher = request_teacher.get();
                course.setTeacher(teacher);
                courseRepository.save(course);
            }
            else{
                throw new ObjectNotFoundException("No such teacher with id: " + teaId);
            }
        }
        else{
            throw new ObjectNotFoundException("No such course with id: " + id);
        }

    }

    @Override
    public void addCourseMaterialToCourse(Long id, Long coumatId) throws ObjectNotFoundException {
        Optional<Course> request_course = courseRepository.findById(id);
        if(request_course.isPresent()){
            Course course = courseRepository.findById(id).get();
            Optional<CourseMaterial> request_courseMaterial = courseMaterialRepository.findById(coumatId);
            if(request_courseMaterial.isPresent()){
                CourseMaterial courseMaterial = request_courseMaterial.get();
                course.setCourseMaterial(courseMaterial);
                courseRepository.save(course);
            }
            else{
                throw new ObjectNotFoundException("No such course material with id: " + coumatId);
            }
        }
        else{
            throw new ObjectNotFoundException("No such course with id: " + id);
        }
    }

    @Override
    public void addDepartmentToCourse(Long couId, Long depId) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Optional<Course> request_course = courseRepository.findById(couId);
        Optional<Department> request_department = departmentRepository.findById(depId);

        if(request_course.isPresent()){
            if(request_department.isPresent()){
                Course course = request_course.get();
                Department department = request_department.get();

                course.setDepartment(department);
                courseRepository.save(course);
                if(!department.isPresent(course)){
                    department.addCourse(course);
                    departmentRepository.save(department);
                }
            }
            else{
                throw new ObjectNotFoundException("Department with ID: " + depId);
            }
        }
        else{
            throw new ObjectNotFoundException("Course with ID: " + couId);
        }

    }
}
