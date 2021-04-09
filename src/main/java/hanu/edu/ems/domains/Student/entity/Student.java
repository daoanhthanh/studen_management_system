package hanu.edu.ems.domains.Student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import hanu.edu.ems.domains.Department.entity.Department;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import hanu.edu.ems.domains.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
public class Student extends User {

    public static final int MIN_SCHOOL_YEAR = 1975;
    public static final int MAX_SCHOOL_YEAR = 2020; // Now

    public static final int MIN_LENGTH_FATHER_NAME = 2;
    public static final int MAX_LENGTH_FATHER_NAME = 50;

    public static final int MIN_LENGTH_MOTHER_NAME = 2;
    public static final int MAX_LENGTH_MOTHER_NAME = 50;

    public static final int MIN_LENGTH_FULL_ADDRESS = 2;
    public static final int MAX_LENGTH_FULL_ADDRESS = 255;

    /**
     * The year when this student joined our school
     */
    @Min(MIN_SCHOOL_YEAR)
    @Max(MAX_SCHOOL_YEAR)
    private Integer sinceYear;

    /**
     * The father's name of this student
     */
    @Size(min = MIN_LENGTH_FATHER_NAME, max = MAX_LENGTH_FATHER_NAME)
    private String fatherName;

    /**
     * The mother's name of this student
     */
    @Size(min = MIN_LENGTH_MOTHER_NAME, max = MAX_LENGTH_MOTHER_NAME)
    private String motherName;

    @Size(min = MIN_LENGTH_FULL_ADDRESS, max = MAX_LENGTH_FULL_ADDRESS)
    private String fullAddress;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Department department;

    @JsonIgnore
    @Singular
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;
}
