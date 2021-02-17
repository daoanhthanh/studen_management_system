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
public class UpdateEnrollmentDTOConverter implements Converter<UpdateEnrollmentDTO, Enrollment> {

    private final CourseReleaseRepository courseReleaseRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UpdateEnrollmentDTOConverter(CourseReleaseRepository courseReleaseRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.courseReleaseRepository = courseReleaseRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Enrollment convert(UpdateEnrollmentDTO updateEnrollmentDTO) {
        CourseRelease courseRelease = courseReleaseRepository.getOne(updateEnrollmentDTO.getCourseReleaseID());
        Student student = studentRepository.getOne(updateEnrollmentDTO.getStudentID());

        Enrollment enrollment = modelMapper.map(updateEnrollmentDTO, Enrollment.class);

        enrollment.setStudent(student);
        enrollment.setCourseRelease(courseRelease);

        return enrollment;
    }
}
