package com.school.Complete.Structure.of.a.School.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;
    @NotEmpty(message = "Enter a student name.")
    private String studentName;
    private char studentGender;


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "student_student_id", referencedColumnName = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "courses_course_id", referencedColumnName = "courseId")
    )
    private List<Course> courses;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    private Standard standard;


    public void addCourse(Course course){
        if(courses.isEmpty()){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

    public boolean isPresent(Course course) throws ObjectAlreadyExistsException {
        Iterator iterator = courses.iterator();
        while(iterator.hasNext()){
            Course c = (Course) iterator.next();
            if(c.getCourseId() == course.getCourseId()){
                return true;
            }
        }
        return false;
    }
}
