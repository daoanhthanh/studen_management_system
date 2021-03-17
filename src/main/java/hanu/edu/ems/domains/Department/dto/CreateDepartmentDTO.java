package hanu.edu.ems.domains.Department.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import hanu.edu.ems.domains.Department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDepartmentDTO implements TimeStamps {

    @Size(min = Department.MIN_LENGTH_NAME, max = Department.MAX_LENGTH_NAME)
    private String name;

    @Size(min = Department.MIN_LENGTH_CODE, max = Department.MAX_LENGTH_CODE)
    private String code;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
