package hanu.edu.ems.domains.Enrollment;

import hanu.edu.ems.domains.Enrollment.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findAllByStudentId(Long studentId);

    Page<Enrollment> findByStudentId(Long studentId, Pageable pageable);

    Page<Enrollment> findAllByCourseReleaseId(Long courseReleaseId, Pageable pageable);

    List<Enrollment> findAllByCourseReleaseId(Long courseReleaseId);
}
