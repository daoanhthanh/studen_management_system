package hanu.edu.ems.domains.TimeTable;

import hanu.edu.ems.domains.TimeTable.entity.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timeTableRepository;

    @Autowired
    public TimetableServiceImpl(TimetableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }

    @Override
    public Timetable create(Timetable timeTable) {
        return timeTableRepository.save(timeTable);
    }

    @Override
    public void updateById(Long id, Timetable timeTable) {
        timeTable.setId(id);
        timeTableRepository.save(timeTable);
    }

    @Override
    public void deleteById(Long id) {
        timeTableRepository.deleteById(id);
    }

    @Override
    public List<Timetable> getAll() {
        return timeTableRepository.findAll();
    }

    @Override
    public Timetable getById(Long id) {
        return timeTableRepository.getOne(id);
    }

    @Override
    public Page<Timetable> getMany(Pageable pageable) {
        return timeTableRepository.findAll(pageable);
    }
}
