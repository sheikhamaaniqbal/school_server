package com.school.Complete.Structure.of.a.School.Controllers;

import com.school.Complete.Structure.of.a.School.Entity.Standard;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Services.StandardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StandardController {
    @Autowired
    private StandardService standardService;

    @PostMapping("/standard")
    public void addStandard(@RequestBody @Valid Standard standard){
        standardService.addStandard(standard);
    }

    @GetMapping("/standard")
    public List<Standard> fetchAllStandards(){
        return standardService.fetchAllStandards();
    }

    @PutMapping("/standard/teacher/{stan_id}/{tea_id}")
    public void addHomeroomToStandard(@PathVariable("stan_id") Long stan_id, @PathVariable("tea_id") Long tea_id) throws ObjectNotFoundException {
        standardService.addHomeroomToStandard(stan_id,tea_id);
    }

    @PutMapping("/standard/student/{stan_id}/{stu_id}")
    public void addStudentToStandard(@PathVariable("stan_id") Long stan_id, @PathVariable("stu_id") Long stu_id) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        standardService.addStudentToStandard(stan_id,stu_id);
    }

    @DeleteMapping("/standard/{id}")
    public void deleteStudentById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        standardService.deleteStandardById(id);
    }
}
