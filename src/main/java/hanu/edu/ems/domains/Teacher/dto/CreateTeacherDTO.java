package hanu.edu.ems.domains.Teacher.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.domains.Authority.entity.AuthorityName;
import hanu.edu.ems.domains.User.dto.CreateUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class CreateTeacherDTO extends CreateUserDTO {

    private Float performanceRating;

    @NotNull
    private Long departmentID;

    @JsonIgnore
    @Builder.Default
    private AuthorityName role = AuthorityName.TEACHER;
}
