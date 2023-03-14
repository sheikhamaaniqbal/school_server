package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Entity.Standard;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;

import java.util.List;

public interface StandardService {
    void addStandard(Standard standard);
    List<Standard> fetchAllStandards();
    void addHomeroomToStandard(Long stan_id, Long tea_id) throws ObjectNotFoundException;
    void addStudentToStandard(Long stanId, Long stuId) throws ObjectNotFoundException, ObjectAlreadyExistsException;
    void deleteStandardById(Long id) throws ObjectNotFoundException;
}
