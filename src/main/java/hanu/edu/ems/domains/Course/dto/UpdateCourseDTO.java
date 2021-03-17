package hanu.edu.ems.domains.Course.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.domains.Course.entity.Course;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class UpdateCourseDTO {

    private Long departmentID;

    @Size(min = Course.MIN_LENGTH_NAME, max = Course.MAX_LENGTH_NAME)
    private String name;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
