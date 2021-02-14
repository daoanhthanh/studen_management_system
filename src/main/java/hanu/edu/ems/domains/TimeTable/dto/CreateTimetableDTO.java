package hanu.edu.ems.domains.TimeTable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanu.edu.ems.base.TimeStamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimetableDTO implements TimeStamps {

    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    private LocalDateTime updatedAt = LocalDateTime.now();
}
