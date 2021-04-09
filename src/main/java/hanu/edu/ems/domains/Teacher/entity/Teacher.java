package hanu.edu.ems.domains.Teacher.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Teacher extends User {

    private Float performanceRating;

    /**
     * One teacher belongs to one department
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Department department;

    /**
     * One teacher has many responsibilities of course releases
     */
    @Singular
    @OneToMany(mappedBy = "teacher")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<CourseRelease> courseReleases;
}
