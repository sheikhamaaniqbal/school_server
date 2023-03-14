package com.school.Complete.Structure.of.a.School.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Department {

    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    private Long departmentId;
    @NotEmpty(message = "Enter a department name.")
    private String departmentName;
    @NotEmpty(message = "Enter a department code.")
    private String departmentCode;

    @JsonIgnore
    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                },
            fetch = FetchType.LAZY
    )
    @JoinTable(
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName = "departmentId"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    )
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinTable(
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName = "departmentId"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    )
    private List<Teacher> teachers;

    public void addTeacher(Teacher teacher){
        if(teachers.isEmpty()){
            teachers = new ArrayList<Teacher>();
        }
        teachers.add(teacher);
    }

    public void addCourse(Course course){
        if(courses.isEmpty()){
            courses = new ArrayList<Course>();
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

    public boolean isPresent(Teacher teacher) throws ObjectAlreadyExistsException {
        Iterator iterator = teachers.iterator();
        while(iterator.hasNext()){
            Teacher t = (Teacher) iterator.next();
            if(t.getTeacherId() == teacher.getTeacherId()){
                return true;
            }
        }
        return false;
    }

}
