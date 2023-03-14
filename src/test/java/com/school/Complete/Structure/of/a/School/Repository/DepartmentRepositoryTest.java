package com.school.Complete.Structure.of.a.School.Repository;

import com.school.Complete.Structure.of.a.School.Entity.Department;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;


    @BeforeEach
    public void setUp() throws Exception {
        Department department = Department.builder().departmentName("Mechanical").departmentCode("ME-22").build();
        departmentRepository.save(department);
    }

    @Test
    public void whenValidDepartmentId_returnDepartment(){
        Department d = departmentRepository.findByDepartmentName("Mechanical");
        assertEquals(d.getDepartmentName(), "Mechanical");
        assertEquals(d.getDepartmentCode(), "ME-22");
    }

    @Test
    public void addDepartment(){
        Department d = Department.builder().departmentName("Chemical Engg").departmentCode("CE-12").build();
        Department savedDepartment = departmentRepository.save(d);
        assertEquals(savedDepartment.getDepartmentName(), d.getDepartmentName());
        assertEquals(savedDepartment.getDepartmentCode(), d.getDepartmentCode());
    }
}