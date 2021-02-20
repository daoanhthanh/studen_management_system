package hanu.edu.ems.domains.Timetable;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Timetable.dto.CreateTimetableDTO;
import hanu.edu.ems.domains.Timetable.dto.UpdateTimetableDTO;
import hanu.edu.ems.domains.Timetable.entity.Timetable;

public interface TimetableService extends CRUDService<Timetable, Long, CreateTimetableDTO, UpdateTimetableDTO> {
}
