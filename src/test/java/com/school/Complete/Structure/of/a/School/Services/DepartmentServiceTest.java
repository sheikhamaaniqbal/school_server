package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Course;
import com.school.Complete.Structure.of.a.School.Entity.Department;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Repository.CourseRepository;
import com.school.Complete.Structure.of.a.School.Repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() throws ObjectNotFoundException, ObjectAlreadyExistsException, InterruptedException {
        Department department = Department.builder().departmentId(12L).departmentName("Unit testing").departmentCode("UT-121").build();
        Course course = Course.builder().courseName("junit").courseId(2L).credit(20L).build();

        Mockito.when(departmentRepository.findById(12L)).thenReturn(Optional.ofNullable(department));
        Mockito.when(departmentRepository.save(department)).thenReturn(department);;
    }

    @Test
    public void whenValidDepartmentId_thenReturnDepartment() throws ObjectNotFoundException {
        Long id = 12L;
        Department department = departmentService.fetchDepartmentById(id);
        assertEquals(department.getDepartmentId(), id);
    }

    @Test
    public void whenDepartmentAddedProperly_thenReturnDepartment() throws ObjectNotFoundException {
        Department department = Department.builder().departmentName("Unit testing").departmentCode("UT-121").departmentId(12L).build();
        Department departmentAdded = departmentService.addDepartment(department);
        assertEquals(departmentAdded.getDepartmentName(), department.getDepartmentName());
        assertEquals(departmentAdded.getDepartmentId(), department.getDepartmentId());
        assertEquals(departmentAdded.getDepartmentCode(), department.getDepartmentCode());
    }

}