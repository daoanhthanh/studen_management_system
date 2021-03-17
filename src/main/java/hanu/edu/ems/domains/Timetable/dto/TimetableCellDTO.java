package hanu.edu.ems.domains.Timetable.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class TimetableCellDTO {

    @NotNull
    private Integer sessionStartIndex;

    @NotNull
    private Integer sessionLength;
}
