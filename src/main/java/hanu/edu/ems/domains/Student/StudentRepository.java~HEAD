package hanu.edu.ems.domains.Student;

import hanu.edu.ems.domains.Student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.util.List;

import static hanu.edu.ems.domains.User.entity.User.MAX_LENGTH_FIRST_NAME;
import static hanu.edu.ems.domains.User.entity.User.MAX_LENGTH_LAST_NAME;
import static hanu.edu.ems.domains.User.entity.User.MAX_LENGTH_PHONE_NUMBER;
import static hanu.edu.ems.domains.User.entity.User.MIN_LENGTH_FIRST_NAME;
import static hanu.edu.ems.domains.User.entity.User.MIN_LENGTH_LAST_NAME;
import static hanu.edu.ems.domains.User.entity.User.MIN_LENGTH_PHONE_NUMBER;


@Repository
@Transactional(readOnly = true)
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAllByDepartmentId(Long departmentId, Pageable pageable);

    List<Student> findAllByDepartmentId(Long departmentId);

    @Query("SELECT s FROM Student s " +
        "JOIN s.enrollments e " +
        "WHERE e.courseRelease.id = ?1")
    Page<Student> findByCourseReleaseId(Long courseReleaseId, Pageable pageable);

    @Query("SELECT s FROM Student s " +
        "JOIN s.enrollments e " +
        "JOIN e.courseRelease cr " +
        "WHERE cr.course.id = ?1")
    Page<Student> findByCourseId(Long courseId, Pageable pageable);

    @Query("SELECT s FROM Student s " +
            "JOIN User u ON s.id = u.id " +
            "WHERE u.firstName LIKE %?1% " +
            "OR u.phoneNumber LIKE %?1% " +
            "OR u.email LIKE %?1% " +
            "OR u.lastName LIKE %?1%")
    Page<Student> findAllByKeyword(String keyword, Pageable pageable);
}
