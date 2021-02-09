package hanu.edu.ems.resources.Student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.resources.Department.entity.Department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import hanu.edu.ems.resources.Enrollment.entity.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    @Column
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Phone Number is mandatory")
    private String phoneNumber;

    @Column
    @NotNull(message = "Date Of birth is mandatory")
    private LocalDate dob;

    @Column
    @NotNull(message = "School Year is mandatory")
    private Integer schoolYear;

    @NotNull
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @JsonIgnore
    private LocalDateTime editedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "department_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Department department;

    @OneToMany(mappedBy = "student")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Enrollment> enrollments;
}
