package hanu.edu.ems.resources.Enrollment;

import hanu.edu.ems.resources.Enrollment.entity.Enrollment;
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
    public void updateByID(Long id, Enrollment enrollment) {
        enrollment.setId(id);
        enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteByID(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getByID(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Enrollment> getMany(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }
}
