package hanu.edu.ems.domains.Enrollment;

import hanu.edu.ems.domains.CourseRelease.CourseReleaseRepository;
import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import hanu.edu.ems.domains.Department.entity.Department;
import hanu.edu.ems.domains.Enrollment.dto.CreateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.dto.UpdateEnrollmentDTO;
import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import hanu.edu.ems.domains.Student.StudentRepository;
import hanu.edu.ems.domains.Student.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final CourseReleaseRepository courseReleaseRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseReleaseRepository courseReleaseRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseReleaseRepository = courseReleaseRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Enrollment create(CreateEnrollmentDTO createEnrollmentDTO) {
        Enrollment enrollment = modelMapper.map(createEnrollmentDTO, Enrollment.class);

        CourseRelease courseRelease = courseReleaseRepository.findById(createEnrollmentDTO.getCourseReleaseID()).orElseThrow(EntityNotFoundException::new);
        enrollment.setCourseRelease(courseRelease);

        Student student = studentRepository.findById(createEnrollmentDTO.getStudentID()).orElseThrow(EntityNotFoundException::new);
        enrollment.setStudent(student);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment updateById(Long id, UpdateEnrollmentDTO updateEnrollmentDTO) {

        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(updateEnrollmentDTO, enrollment);

        CourseRelease courseRelease = courseReleaseRepository.findById(updateEnrollmentDTO.getCourseReleaseID()).orElseThrow(EntityNotFoundException::new);
        enrollment.setCourseRelease(courseRelease);

        Student student = studentRepository.findById(updateEnrollmentDTO.getStudentID()).orElseThrow(EntityNotFoundException::new);
        enrollment.setStudent(student);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Enrollment> getMany(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }
}
