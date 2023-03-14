package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;

import java.util.List;

public interface CourseMaterialService {

    public void addCourseMaterial(CourseMaterial courseMaterial);

    public List<CourseMaterial> fetchAllCourseMaterials();

    void deleteCourseMaterialById(Long id) throws ObjectNotFoundException;

    void updateCourseMaterialById(Long id, CourseMaterial courseMaterial) throws ObjectNotFoundException;

    CourseMaterial fetchCourseMaterialById(Long id) throws ObjectNotFoundException;
}
