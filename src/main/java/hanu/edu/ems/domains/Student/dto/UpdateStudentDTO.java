package hanu.edu.ems.domains.Student.dto;

import hanu.edu.ems.domains.Student.entity.Student;
import hanu.edu.ems.domains.User.dto.UpdateUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import static hanu.edu.ems.domains.Student.entity.Student.MAX_LENGTH_FULL_ADDRESS;
import static hanu.edu.ems.domains.Student.entity.Student.MIN_LENGTH_FULL_ADDRESS;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdateStudentDTO extends UpdateUserDTO {

    @Min(Student.MIN_SCHOOL_YEAR)
    @Max(Student.MAX_SCHOOL_YEAR)
    private Integer sinceYear;

    @Size(min = Student.MIN_LENGTH_FATHER_NAME, max = Student.MAX_LENGTH_FATHER_NAME)
    private String fatherName;

    @Size(min = Student.MIN_LENGTH_MOTHER_NAME, max = Student.MAX_LENGTH_MOTHER_NAME)
    private String motherName;

    @Size(min = MIN_LENGTH_FULL_ADDRESS, max = MAX_LENGTH_FULL_ADDRESS)
    private String fullAddress;

    private Long departmentID;
}
