package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Entity.Department;
import com.school.Complete.Structure.of.a.School.Entity.Teacher;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Repository.CourseRepository;
import com.school.Complete.Structure.of.a.School.Repository.DepartmentRepository;
import com.school.Complete.Structure.of.a.School.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(Long id) throws ObjectNotFoundException {
        Optional<Department> request_department = departmentRepository.findById(id);
        if(request_department.isPresent()){
            departmentRepository.deleteById(id);
        }
        else{
            throw new ObjectNotFoundException("No such department with ID: " + id);
        }
    }

    @Override
    public void addCourseToDepartment(Long dep_id, Long cou_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Optional<Department> request_department = departmentRepository.findById(dep_id);
        Optional<Course> request_course = courseRepository.findById(cou_id);

        if(request_department.isPresent()){
            if(request_course.isPresent()){
                Department department = request_department.get();
                Course course = request_course.get();
                if(department.isPresent(course)){
                    throw new ObjectAlreadyExistsException("Course with ID " + cou_id + " already exists.");
                }
                else{
                    course.setDepartment(department);
                    courseRepository.save(course);
                    department.addCourse(course);
                    departmentRepository.save(department);

                }
            }
            else{
                throw new ObjectNotFoundException("No such course with ID: " + cou_id);
            }
        }
        else{
            throw new ObjectNotFoundException("No such department with ID: " + dep_id);
        }
    }

    @Override
    public void addTeacherToDepartment(Long dep_id, Long tea_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Optional<Department> request_department = departmentRepository.findById(dep_id);
        Optional<Teacher> request_teacher = teacherRepository.findById(tea_id);

        if(request_department.isPresent()){
            if(request_teacher.isPresent()){
                Department department = request_department.get();
                Teacher teacher = request_teacher.get();
                if(department.isPresent(teacher)){
                    throw new ObjectAlreadyExistsException("Teacher with ID " + tea_id + " already exists.");
                }
                else{
                    department.addTeacher(teacher);
                    departmentRepository.save(department);
                    teacher.setDepartment(department);
                    teacherRepository.save(teacher);
                }
            }
            else{
                throw new ObjectNotFoundException("No such teacher with ID: " + tea_id);
            }
        }
        else{
            throw new ObjectNotFoundException("No such department with ID: " + dep_id);
        }
    }

    @Override
    public Department fetchDepartmentById(Long id) throws ObjectNotFoundException {
        Optional<Department> request_department = departmentRepository.findById(id);
        if(request_department.isPresent()){
            return request_department.get();
        }
        else{
            throw new ObjectNotFoundException("No such department with ID: " + id);
        }

    }

    @Override
    public void deleteCourseFromDepartment(Long dep_id, Long cou_id) throws ObjectNotFoundException {

        Optional<Department> request_department = departmentRepository.findById(dep_id);
        Optional<Course> request_course = courseRepository.findById(cou_id);

        if(request_department.isPresent()){
            if(request_course.isPresent()){
                Department department = request_department.get();
                Course course = request_course.get();
                List<Course> courses = department.getCourses();
                Iterator iterator = courses.iterator();
                while (iterator.hasNext()){
                    Course c = (Course) iterator.next();
                    if(c.getCourseId() == cou_id){
                        courses.remove(c);
                        course.setDepartment(null);
                        break;
                    }
                }
                departmentRepository.save(department);
                courseRepository.save(course);
            }
            else{
                throw new ObjectNotFoundException("No such course with ID: " + cou_id);
            }
        }
        else{
            throw new ObjectNotFoundException("No such department with ID: " + dep_id);
        }
    }

    @Override
    public void deleteTeacherFromDepartment(Long dep_id, Long tea_id) throws ObjectNotFoundException {
        Optional<Department> request_department = departmentRepository.findById(dep_id);
        Optional<Teacher> request_teacher = teacherRepository.findById(tea_id);

        if(request_department.isPresent()){
            if(request_teacher.isPresent()){
                Department department = request_department.get();
                Teacher teacher = request_teacher.get();
                List<Teacher> teachers = department.getTeachers();
                Iterator iterator = teachers.iterator();
                while (iterator.hasNext()){
                    Teacher t = (Teacher) iterator.next();
                    if(t.getTeacherId() == tea_id){
                        teachers.remove(t);
                        teacher.setDepartment(null);
                        break;
                    }
                }
                departmentRepository.save(department);
                teacherRepository.save(teacher);

            }
            else{
                throw new ObjectNotFoundException("No such teacher with ID: " + tea_id);
            }
        }
        else{
            throw new ObjectNotFoundException("No such department with ID: " + dep_id);
        }
    }


}
