package hanu.edu.ems.domains.Enrollment;

import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Page<Enrollment> findAllByStudentId(Long studentId, Pageable pageable);

    Page<Enrollment> findAllByCourseReleaseId(Long courseReleaseId, Pageable pageable);
}
