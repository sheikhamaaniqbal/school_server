package com.school.Complete.Structure.of.a.School.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    @NotEmpty(message = "Enter a course name.")
    private String courseName;
    @NotNull(message = "Enter the course credits.")
    private Long credit;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "courseMaterial_id",
            referencedColumnName = "courseMaterialId"
    )
    private CourseMaterial courseMaterial;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @JsonIgnore
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    private Department department;


    public CourseMaterial getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(CourseMaterial courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public @NotEmpty(message = "Enter a course name.") String getCourseName() {
        return this.courseName;
    }

    public @NotNull(message = "Enter the course credits.") Long getCredit() {
        return this.credit;
    }

    public Department getDepartment() {
        return this.department;
    }
}
