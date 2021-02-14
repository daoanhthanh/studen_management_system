package hanu.edu.ems.domains.Course;

import hanu.edu.ems.domains.Course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByDepartmentId(Long departmentID);

    Page<Course> findAllByDepartmentId(Long departmentID, Pageable pageable);

    Page<Course> findAllByNameLike(String partialName, Pageable pageable);

    List<Course> findAllByNameLike(String partialName);

    boolean existsByRegistrationCode(String registrationCode);
}
