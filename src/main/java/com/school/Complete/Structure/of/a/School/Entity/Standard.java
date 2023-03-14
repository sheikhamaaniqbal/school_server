package com.school.Complete.Structure.of.a.School.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.school.Complete.Structure.of.a.School.Exception.ObjectAlreadyExistsException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

public class Standard {

    @Id
    @SequenceGenerator(
            name = "standard_sequence",
            sequenceName = "standard_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "standard_sequence"
    )
    private Long standardId;
    @NotNull(message = "Enter standard section.")
    private char section;

    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    @JoinTable(
            joinColumns = @JoinColumn(name = "standard_id", referencedColumnName = "standardId"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    )
    @JsonIgnore
    private List<Student> students;

    @OneToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "homeroom_teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher homeRoomTeacher;

    public void addHomeroom(Teacher teacher){
        this.homeRoomTeacher = teacher;
    }

    public void addStudent(Student student){
        if(students.isEmpty()){
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public boolean isPresent(Student student) throws ObjectAlreadyExistsException {
        Iterator iterator = students.iterator();
        while(iterator.hasNext()){
            Student s = (Student) iterator.next();
            if(s.getStudentId() == student.getStudentId()){
                return true;
            }
        }
        return false;
    }
}