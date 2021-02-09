package hanu.edu.ems.resources.Enrollment.entity;

import hanu.edu.ems.resources.Course.entity.Course;
import hanu.edu.ems.resources.Student.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "enrollments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // One enrollment belongs to one student
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;

    // One enrollment belongs to one course
    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    @Column
    @Min(value = 0, message = "Mark cannot be negative")
    private Double finalMark;

    @Column
    @Min(value = 0, message = "Mark cannot be negative")
    private Double internalMark;

    @Column
    @Min(value = 0, message = "Mark cannot be negative")
    private Double examMark;
}
