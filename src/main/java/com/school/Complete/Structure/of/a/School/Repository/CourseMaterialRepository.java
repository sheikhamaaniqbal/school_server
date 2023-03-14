package com.school.Complete.Structure.of.a.School.Repository;

import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {
}
