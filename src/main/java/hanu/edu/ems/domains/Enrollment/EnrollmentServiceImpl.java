package hanu.edu.ems.domains.Enrollment;

import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void updateById(Long id, Enrollment enrollment) {
        enrollment.setId(id);
        enrollmentRepository.save(enrollment);
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
        return enrollmentRepository.getOne(id);
    }

    @Override
    public Page<Enrollment> getMany(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }
}
