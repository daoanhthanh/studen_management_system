package hanu.edu.ems.domains.Timetable.dto;

import hanu.edu.ems.domains.Timetable.entity.Timetable;
import lombok.Data;

@Data
public class StudentTimetableResponseDTO {
    private Timetable content;
}
