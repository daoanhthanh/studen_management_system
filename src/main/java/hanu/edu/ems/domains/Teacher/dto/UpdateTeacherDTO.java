package hanu.edu.ems.domains.Teacher.dto;

import hanu.edu.ems.domains.User.dto.UpdateUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdateTeacherDTO extends UpdateUserDTO {

    private Float performanceRating;

    @NotNull
    private Long departmentID;
}
