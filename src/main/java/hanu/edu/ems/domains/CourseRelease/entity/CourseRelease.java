package hanu.edu.ems.domains.CourseRelease.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * An associative entity between {@link Course} and many other Business domains:
 * - {@link Teacher}:
 * - {@link Timetable}:
 * - {@link Enrollment}
 */
@Entity
@Table(name = "course_releases")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRelease implements TimeStamps {

    public static final int MIN_LENGTH_NAME = 2;
    public static final int MAX_LENGTH_NAME = 50;

    public static final int MIN_RELEASE_YEAR = 1975;
    public static final int MAX_RELEASE_YEAR = 2021;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Min(MIN_RELEASE_YEAR)
    @Max(MAX_RELEASE_YEAR)
    private Integer releaseYear;

    @NotNull
    private Boolean isActive;

    // One course has many students
    @Singular
    @JsonIgnore
    @OneToMany(mappedBy = "courseRelease", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @OneToOne
    @JoinColumn(name = "timetable_id", nullable = false)
    private Timetable timeTable;

    private LocalDate startDate;

    private LocalDate endDate;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;
}
