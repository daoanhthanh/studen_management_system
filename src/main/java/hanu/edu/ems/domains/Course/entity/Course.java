package hanu.edu.ems.domains.Course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Enrollment of a school
 * @author duong
 */
@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Course implements TimeStamps {

    public static final int MIN_LENGTH_REGISTRATION_CODE = 7;
    public static final int MAX_LENGTH_REGISTRATION_CODE = 7;

    public static final int MIN_LENGTH_NAME = 2;
    public static final int MAX_LENGTH_NAME = 50;

    public static final int MIN_NUMBER_OF_CREDITS = 0;
    public static final int MAX_NUMBER_OF_CREDITS = 10;

    public static final int MIN_REQUIRED_SCHOOL_YEAR = 1;
    public static final int MAX_REQUIRED_SCHOOL_YEAR = 4;

    /**
     * The ID of this course
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = MIN_LENGTH_REGISTRATION_CODE, max = MAX_LENGTH_REGISTRATION_CODE)
    private String registrationCode;

    /**
     * The name of the course
     */
    @NotNull
    @Size(min = MIN_LENGTH_NAME, max = MAX_LENGTH_NAME)
    private String name;

    @NotNull
    @Min(MIN_NUMBER_OF_CREDITS)
    @Max(MAX_NUMBER_OF_CREDITS)
    private Integer numberOfCredits;

    @NotNull
    @Min(MIN_REQUIRED_SCHOOL_YEAR)
    @Max(MAX_REQUIRED_SCHOOL_YEAR)
    private Integer requiredSchoolYear;

    private Integer activeReleasesCount;

    /**
     * One course belongs to one department
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Department department;

    /**
     * One course can have many course releases
     */
    @JsonIgnore
    @Singular
    @OneToMany(mappedBy = "course")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CourseRelease> courseReleases;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;
}
