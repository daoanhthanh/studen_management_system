package hanu.edu.ems.domains.Timetable;

import hanu.edu.ems.domains.Timetable.dto.CreateTimetableDTO;
import hanu.edu.ems.domains.Timetable.dto.TimetableCellDTO;
import hanu.edu.ems.domains.Timetable.dto.UpdateTimetableDTO;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import hanu.edu.ems.domains.Timetable.entity.TimetableCell;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
        convertAndSetTimetableCells(timetable, createTimetableDTO.getCells());
        return timeTableRepository.save(timetable);
    }

    @Override
    public Timetable updateById(Long id, UpdateTimetableDTO updateTimetableDTO) {
        Timetable timetable = timeTableRepository.findById(id).orElseThrow(EntityExistsException::new);
        modelMapper.map(updateTimetableDTO, timetable);
        convertAndSetTimetableCells(timetable, updateTimetableDTO.getCells());
        return timeTableRepository.save(timetable);
    }

    private void convertAndSetTimetableCells(Timetable timetable, List<TimetableCellDTO> cells) {
        List<TimetableCell> timetableCells = new ArrayList<>();

        for (TimetableCellDTO timetableCellDTO: cells) {
            TimetableCell timetableCell = modelMapper.map(timetableCellDTO, TimetableCell.class);
            timetableCells.add(timetableCell);
            timetableCell.setTimetable(timetable);
        }
        timetable.setTimetableCells(timetableCells);
    }

    @Override
    public void deleteById(Long id) {
        timeTableRepository.deleteById(id);
    }

    @Override
    public List<Timetable> findAll() {
        return timeTableRepository.findAll();
    }

    @Override
    public Timetable getById(Long id) {
        return timeTableRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Timetable> findAll(Pageable pageable) {
        return timeTableRepository.findAll(pageable);
    }

    @Override
    public Timetable suggestForStudent(String studentID) {
        return null;
    }

    @Override
    public Timetable suggestForTeacher(String teacherID) {
        return null;
    }

    /**
     *
     * @param studentID The id of the student
     * @return
     */
    @Override
    public Timetable getForStudent(String studentID) {
        return null;
    }

    /**
     *
     * @param teacherID The id of the teacher
     * @return
     */
    @Override
    public Timetable getForTeacher(String teacherID) {
        return null;
    }
}
