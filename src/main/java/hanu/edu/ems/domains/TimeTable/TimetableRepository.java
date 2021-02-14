package hanu.edu.ems.domains.TimeTable;

import hanu.edu.ems.domains.TimeTable.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
