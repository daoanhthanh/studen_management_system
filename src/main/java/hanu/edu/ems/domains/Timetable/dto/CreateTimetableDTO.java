package hanu.edu.ems.domains.Timetable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTimetableDTO implements TimeStamps {

    private List<TimetableCellDTO> cells;

    @JsonIgnore
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}
