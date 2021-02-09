package hanu.edu.ems.resources.Department.entity;

import hanu.edu.ems.resources.Course.entity.Course;
import hanu.edu.ems.resources.Student.entity.Student;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String code;

    // One department has many students
    @OneToMany(mappedBy = "department")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Student> students;

    // One department has many courses
    @OneToMany(mappedBy = "department")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Course> courses;
}
