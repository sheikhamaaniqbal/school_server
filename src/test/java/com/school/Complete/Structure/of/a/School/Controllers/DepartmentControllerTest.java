package com.school.Complete.Structure.of.a.School.Controllers;

import com.school.Complete.Structure.of.a.School.Entity.Department;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder().departmentName("EEE").departmentCode("EE-22").departmentId(1L).build();
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);
        mockMvc.perform(get("/department/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }

    @Test
    void addDepartment() throws Exception {
        Department inputDepartment = Department.builder().departmentName("EEE").departmentCode("EE-22").departmentId(1L).build();
        Mockito.when(departmentService.addDepartment(inputDepartment)).thenReturn(department);
        mockMvc.perform(MockMvcRequestBuilders.post("/department").contentType(MediaType.APPLICATION_JSON).content("" +
                "{\n" +
                "    \"departmentName\": \"EEE\",\n" +
                "    \"departmentCode\": \"EE-22\"\n" +
                "}"))
                .andExpect(status().isOk());
    }
}