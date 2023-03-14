package com.school.Complete.Structure.of.a.School.Controllers;

import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Services.CourseMaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CourseMaterialController {

    @Autowired
    private CourseMaterialService courseMaterialService;

    @PostMapping("/course_material")
    public void addCourseMaterial(@RequestBody @Valid CourseMaterial courseMaterial){
        courseMaterialService.addCourseMaterial(courseMaterial);
    }

    @GetMapping("/course_material")
    public List<CourseMaterial> fetchAllCourseMaterials(){
        return courseMaterialService.fetchAllCourseMaterials();
    }

    @DeleteMapping("/course_material/{id}")
    public void deleteCourseMaterialById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        courseMaterialService.deleteCourseMaterialById(id);
    }

    @PutMapping("/course_material/{id}")
    public void updateCourseMaterialById(@PathVariable("id") Long id, @RequestBody CourseMaterial courseMaterial) throws ObjectNotFoundException {
        courseMaterialService.updateCourseMaterialById(id,courseMaterial);
    }

    @GetMapping("/course_material/{id}")
    public CourseMaterial fetchCourseMaterialById(@PathVariable("id") Long id) throws ObjectNotFoundException {
        return courseMaterialService.fetchCourseMaterialById(id);
    }

}
