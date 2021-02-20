package hanu.edu.ems.domains.Timetable;

import hanu.edu.ems.domains.Timetable.dto.CreateTimetableDTO;
import hanu.edu.ems.domains.Timetable.dto.UpdateTimetableDTO;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timeTableRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TimetableServiceImpl(TimetableRepository timeTableRepository, ModelMapper modelMapper) {
        this.timeTableRepository = timeTableRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Timetable create(CreateTimetableDTO createTimetableDTO) {
        Timetable timetable = modelMapper.map(createTimetableDTO, Timetable.class);
        return timeTableRepository.save(timetable);
    }

    @Override
    public Timetable updateById(Long id, UpdateTimetableDTO updateTimetableDTO) {
        Timetable timetable = timeTableRepository.findById(id).orElseThrow(EntityExistsException::new);
        modelMapper.map(updateTimetableDTO, timetable);
        return timeTableRepository.save(timetable);
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
