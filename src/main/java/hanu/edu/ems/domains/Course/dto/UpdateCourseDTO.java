package hanu.edu.ems.domains.Course.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.domains.Course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class UpdateCourseDTO {

    @NotNull
    @Size(min = Course.MIN_LENGTH_REGISTRATION_CODE, max = Course.MAX_LENGTH_REGISTRATION_CODE)
    private String registrationCode;

    private Long departmentID;

    @NotNull
    @Min(Course.MIN_NUMBER_OF_CREDITS)
    @Max(Course.MAX_NUMBER_OF_CREDITS)
    private Integer numberOfCredits;

    @NotNull
    @Min(Course.MIN_REQUIRED_SCHOOL_YEAR)
    @Max(Course.MAX_REQUIRED_SCHOOL_YEAR)
    private Integer requiredSchoolYear;

    @Size(min = Course.MIN_LENGTH_NAME, max = Course.MAX_LENGTH_NAME)
    private String name;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
