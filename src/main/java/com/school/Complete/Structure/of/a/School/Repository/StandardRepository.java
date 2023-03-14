package com.school.Complete.Structure.of.a.School.Repository;

import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import com.school.Complete.Structure.of.a.School.Entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StandardRepository extends JpaRepository<Standard,Long> {
}
