package hanu.edu.ems.domains.CourseRelease.dto;

import hanu.edu.ems.domains.Course.CourseRepository;
import hanu.edu.ems.domains.Course.entity.Course;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Teacher.TeacherRepository;
import hanu.edu.ems.domains.Teacher.entity.Teacher;
import hanu.edu.ems.domains.Timetable.TimetableRepository;
import hanu.edu.ems.domains.Timetable.entity.Timetable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCourseReleaseDTOConverter implements Converter<CreateCourseReleaseDTO, CourseRelease> {

    private final TeacherRepository teacherRepository;

    private final TimetableRepository timetableRepository;

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CreateCourseReleaseDTOConverter(TeacherRepository teacherRepository, TimetableRepository timetableRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.timetableRepository = timetableRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseRelease convert(CreateCourseReleaseDTO createCourseReleaseDTO) {
        Timetable timetable = timetableRepository.getOne(createCourseReleaseDTO.getTimetableID());
        Teacher teacher = teacherRepository.getOne(createCourseReleaseDTO.getTeacherID());
        Course course = courseRepository.getOne(createCourseReleaseDTO.getCourseID());

        CourseRelease courseRelease = modelMapper.map(createCourseReleaseDTO, CourseRelease.class);

        courseRelease.setTimeTable(timetable);
        courseRelease.setTeacher(teacher);
        courseRelease.setCourse(course);

        return courseRelease;
    }
}
