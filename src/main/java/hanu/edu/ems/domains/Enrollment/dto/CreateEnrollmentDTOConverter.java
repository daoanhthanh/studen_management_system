package hanu.edu.ems.domains.Enrollment.dto;

import hanu.edu.ems.domains.CourseRelease.CourseReleaseRepository;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import hanu.edu.ems.domains.Student.StudentRepository;
import hanu.edu.ems.domains.Student.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateEnrollmentDTOConverter implements Converter<CreateEnrollmentDTO, Enrollment> {
    private final CourseReleaseRepository courseReleaseRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CreateEnrollmentDTOConverter(CourseReleaseRepository courseReleaseRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.courseReleaseRepository = courseReleaseRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Enrollment convert(CreateEnrollmentDTO createEnrollmentDTO) {
        CourseRelease courseRelease = courseReleaseRepository.getOne(createEnrollmentDTO.getCourseReleaseID());
        Student student = studentRepository.getOne(createEnrollmentDTO.getStudentID());

        Enrollment enrollment = modelMapper.map(createEnrollmentDTO, Enrollment.class);

        enrollment.setStudent(student);
        enrollment.setCourseRelease(courseRelease);

        return enrollment;
    }
}
