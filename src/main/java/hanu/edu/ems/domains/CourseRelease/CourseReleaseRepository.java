package hanu.edu.ems.domains.CourseRelease;

import hanu.edu.ems.domains.CourseRelease.entity.CourseRelease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CourseReleaseRepository extends JpaRepository<CourseRelease, Long> {
    Page<CourseRelease> findAllByCourseId(Long courseId, Pageable pageable);

    List<CourseRelease> findAllByCourseId(Long courseId);
}
