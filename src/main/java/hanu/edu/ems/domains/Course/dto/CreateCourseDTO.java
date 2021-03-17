package hanu.edu.ems.domains.Course.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.Course.entity.Course;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class CreateCourseDTO implements TimeStamps {

    @Size(min = Course.MIN_LENGTH_NAME, max = Course.MAX_LENGTH_NAME)
    private String name;

    @NotNull
    private Long departmentID;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
