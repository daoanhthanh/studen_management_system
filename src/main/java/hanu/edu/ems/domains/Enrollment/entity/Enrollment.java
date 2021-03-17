package hanu.edu.ems.domains.Enrollment.entity;

import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * Enrollment of a school
 * @author Nguyen Thi Thuy Duong
 */
@Entity
@Table(name = "enrollments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enrollment implements TimeStamps {

    public static final int MIN_LENGTH_NAME = 2;
    public static final int MAX_LENGTH_NAME = 50;
    
    public static final String MIN_ATTENDANCE_MARK = "0.0";
    public static final String MAX_ATTENDANCE_MARK = "10.0";

    public static final String MIN_MIDTERM_MARK = "0.0";
    public static final String MAX_MIDTERM_MARK = "10.0";

    public static final String MIN_FINAL_MARK = "0.0";
    public static final String MAX_FINAL_MARK = "10.0";

    /**
     * The ID of this course
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_release_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CourseRelease courseRelease;
    
    @DecimalMin(MIN_ATTENDANCE_MARK)
    @DecimalMax(MAX_ATTENDANCE_MARK)
    private Float attendanceMark;
    
    @DecimalMin(MIN_MIDTERM_MARK)
    @DecimalMax(MAX_MIDTERM_MARK)
    private Float midtermMark;

    @DecimalMin(MIN_FINAL_MARK)
    @DecimalMax(MAX_FINAL_MARK)
    private Float finalMark;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;
}
