package hanu.edu.ems.domains.Department.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.Student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department implements TimeStamps {

    public static final int MIN_LENGTH_NAME = 2;
    public static final int MAX_LENGTH_NAME = 50;

    public static final int MIN_LENGTH_CODE = 2;
    public static final int MAX_LENGTH_CODE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = MIN_LENGTH_NAME, max = MAX_LENGTH_NAME)
    private String name;

    @Column(unique = true)
    @Size(min = MIN_LENGTH_CODE, max = MAX_LENGTH_CODE)
    private String code;

    // One department has many students
    @JsonIgnore
    @Singular
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Student> students;

    // One department has many courses
    @JsonIgnore
    @Singular
    @OneToMany(mappedBy = "department")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Course> courses;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;
}
