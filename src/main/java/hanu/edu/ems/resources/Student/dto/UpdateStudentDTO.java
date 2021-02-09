package hanu.edu.ems.resources.Student.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateStudentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @Column
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Column
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column
    @NotBlank(message = "Phone Number is mandatory")
    private String phoneNumber;

    @Column
    @NotBlank(message = "Date of Birth is mandatory")
    private String dob;

    @Column
    @NotBlank(message = "School Year is mandatory")
    private int schoolYear;

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();
}
