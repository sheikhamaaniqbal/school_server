CREATE SEQUENCE  IF NOT EXISTS course_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE course (
  course_id BIGINT NOT NULL,
   course_name VARCHAR(255),
   credit BIGINT NOT NULL,
   course_material_id BIGINT,
   teacher_id BIGINT,
   department_department_id BIGINT,
   CONSTRAINT pk_course PRIMARY KEY (course_id)
);

CREATE SEQUENCE  IF NOT EXISTS course_material_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE course_material (
  course_material_id BIGINT NOT NULL,
   modules BIGINT NOT NULL,
   url VARCHAR(255),
   CONSTRAINT pk_coursematerial PRIMARY KEY (course_material_id)
);

CREATE SEQUENCE  IF NOT EXISTS department_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE department (
  department_id BIGINT NOT NULL,
   department_name VARCHAR(255),
   department_code VARCHAR(255),
   CONSTRAINT pk_department PRIMARY KEY (department_id)
);

CREATE TABLE department_courses (
  course_id BIGINT NOT NULL,
   department_id BIGINT NOT NULL
);

CREATE TABLE department_teachers (
  department_id BIGINT NOT NULL,
   teacher_id BIGINT NOT NULL
);



CREATE SEQUENCE  IF NOT EXISTS standard_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE standard (
  standard_id BIGINT NOT NULL,
   section CHAR NOT NULL,
   homeroom_teacher_id BIGINT,
   CONSTRAINT pk_standard PRIMARY KEY (standard_id)
);


CREATE TABLE standard_students (
  standard_id BIGINT NOT NULL,
   student_id BIGINT NOT NULL
);

CREATE SEQUENCE  IF NOT EXISTS student_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE student (
  student_id BIGINT NOT NULL,
   student_name VARCHAR(255),
   student_gender CHAR NOT NULL,
   standard_standard_id BIGINT,
   CONSTRAINT pk_student PRIMARY KEY (student_id)
);


CREATE TABLE student_courses (
  courses_course_id BIGINT NOT NULL,
   student_student_id BIGINT NOT NULL
);

CREATE SEQUENCE  IF NOT EXISTS teacher_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE teacher (
  teacher_id BIGINT NOT NULL,
   teacher_name VARCHAR(255),
   teacher_gender CHAR NOT NULL,
   department_department_id BIGINT,
   CONSTRAINT pk_teacher PRIMARY KEY (teacher_id)
);


ALTER TABLE teacher ADD CONSTRAINT FK_TEACHER_ON_DEPARTMENT_DEPARTMENTID FOREIGN KEY (department_department_id) REFERENCES department (department_id) ON DELETE CASCADE;
ALTER TABLE student_courses ADD CONSTRAINT fk_stucou_on_course FOREIGN KEY (courses_course_id) REFERENCES course (course_id) ON DELETE CASCADE;
ALTER TABLE student_courses ADD CONSTRAINT fk_stucou_on_student FOREIGN KEY (student_student_id) REFERENCES student (student_id) ON DELETE CASCADE;
ALTER TABLE student ADD CONSTRAINT FK_STUDENT_ON_STANDARD_STANDARDID FOREIGN KEY (standard_standard_id) REFERENCES standard (standard_id) ON DELETE CASCADE;
ALTER TABLE standard_students ADD CONSTRAINT uc_standard_students_student UNIQUE (student_id);
ALTER TABLE standard_students ADD CONSTRAINT fk_stastu_on_standard FOREIGN KEY (standard_id) REFERENCES standard (standard_id) ON DELETE CASCADE;
ALTER TABLE standard_students ADD CONSTRAINT fk_stastu_on_student FOREIGN KEY (student_id) REFERENCES student (student_id) ON DELETE CASCADE;
ALTER TABLE standard ADD CONSTRAINT FK_STANDARD_ON_HOMEROOM_TEACHER FOREIGN KEY (homeroom_teacher_id) REFERENCES teacher (teacher_id) ON DELETE CASCADE;
ALTER TABLE department_teachers ADD CONSTRAINT uc_department_teachers_teacher UNIQUE (teacher_id);
ALTER TABLE department_teachers ADD CONSTRAINT fk_deptea_on_department FOREIGN KEY (department_id) REFERENCES department (department_id) ON DELETE CASCADE;
ALTER TABLE department_teachers ADD CONSTRAINT fk_deptea_on_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id) ON DELETE CASCADE;
ALTER TABLE department_courses ADD CONSTRAINT uc_department_courses_course UNIQUE (course_id);
ALTER TABLE department_courses ADD CONSTRAINT fk_depcou_on_course FOREIGN KEY (course_id) REFERENCES course (course_id) ON DELETE CASCADE;
ALTER TABLE department_courses ADD CONSTRAINT fk_depcou_on_department FOREIGN KEY (department_id) REFERENCES department (department_id) ON DELETE CASCADE;
ALTER TABLE course ADD CONSTRAINT FK_COURSE_ON_COURSEMATERIAL FOREIGN KEY (course_material_id) REFERENCES course_material (course_material_id) ON DELETE CASCADE;
ALTER TABLE course ADD CONSTRAINT FK_COURSE_ON_DEPARTMENT_DEPARTMENTID FOREIGN KEY (department_department_id) REFERENCES department (department_id) ON DELETE CASCADE;
ALTER TABLE course ADD CONSTRAINT FK_COURSE_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id) ON DELETE CASCADE;