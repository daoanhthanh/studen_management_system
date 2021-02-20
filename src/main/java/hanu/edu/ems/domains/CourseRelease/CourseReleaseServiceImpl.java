package hanu.edu.ems.domains.CourseRelease;

import hanu.edu.ems.domains.Course.CourseRepository;
import hanu.edu.ems.domains.Course.dto.UpdateCourseDTO;
import hanu.edu.ems.domains.Course.entity.Course;

import hanu.edu.ems.domains.CourseRelease.dto.CreateCourseReleaseDTO;
import hanu.edu.ems.domains.CourseRelease.dto.UpdateCourseReleaseDTO;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Teacher.TeacherRepository;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import hanu.edu.ems.domains.Timetable.TimetableRepository;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseReleaseServiceImpl implements CourseReleaseService {

    private final TeacherRepository teacherRepository;

    private final TimetableRepository timetableRepository;

    private final CourseRepository courseRepository;

    private final CourseReleaseRepository courseReleaseRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public CourseReleaseServiceImpl(TeacherRepository teacherRepository, TimetableRepository timetableRepository, CourseReleaseRepository courseReleaseRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.timetableRepository = timetableRepository;
        this.courseReleaseRepository = courseReleaseRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CourseRelease create(CreateCourseReleaseDTO createCourseReleaseDTO) {
        CourseRelease courseRelease = modelMapper.map(createCourseReleaseDTO, CourseRelease.class);

        Teacher teacher = teacherRepository.findById(createCourseReleaseDTO.getTeacherID()).orElseThrow(EntityNotFoundException::new);
        courseRelease.setTeacher(teacher);

        Timetable timetable = timetableRepository.findById(createCourseReleaseDTO.getTimetableID()).orElseThrow(EntityNotFoundException::new);
        courseRelease.setTimeTable(timetable);

        Course course = courseRepository.findById(createCourseReleaseDTO.getCourseID()).orElseThrow(EntityNotFoundException::new);
        courseRelease.setCourse(course);

        return courseReleaseRepository.save(courseRelease);
    }

    @Override
    public CourseRelease updateById(Long id, UpdateCourseReleaseDTO updateCourseReleaseDTO) {
        CourseRelease courseRelease = courseReleaseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateCourseReleaseDTO, courseRelease);

        Teacher teacher = teacherRepository.findById(updateCourseReleaseDTO.getTeacherID()).orElseThrow(EntityNotFoundException::new);
        courseRelease.setTeacher(teacher);

        Timetable timetable = timetableRepository.findById(updateCourseReleaseDTO.getTimetableID()).orElseThrow(EntityNotFoundException::new);
        courseRelease.setTimeTable(timetable);

        Course course = courseRepository.findById(updateCourseReleaseDTO.getCourseID()).orElseThrow(EntityNotFoundException::new);
        courseRelease.setCourse(course);
        return courseReleaseRepository.save(courseRelease);
    }
    @Override
    public void deleteById(Long id) {
        courseReleaseRepository.deleteById(id);
    }

    @Override
    public List<CourseRelease> findAll() {
        return courseReleaseRepository.findAll();
    }

    @Override
    public CourseRelease getById(Long id) {
        return courseReleaseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<CourseRelease> findAll(Pageable pageable) {
        return courseReleaseRepository.findAll(pageable);
    }

    @Override
    public CourseRelease createForCourse(Long courseID, CreateCourseReleaseDTO createCourseReleaseDTO) {
        CourseRelease courseRelease = modelMapper.map(createCourseReleaseDTO, CourseRelease.class);

        Course course = courseRepository.findById(courseID).orElseThrow(EntityNotFoundException::new);
        courseRelease.setCourse(course);

        return courseReleaseRepository.save(courseRelease);
    }

    @Override
    public List<CourseRelease> findAllByCourseID(Long courseID) {
        return courseReleaseRepository.findAllByCourseId(courseID);
    }

    @Override
    public Page<CourseRelease> findAllByCourseID(Long courseID, Pageable pageable) {
        return courseReleaseRepository.findAllByCourseId(courseID, pageable);
    }
}
