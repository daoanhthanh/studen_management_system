package hanu.edu.ems.domains.Teacher;

import hanu.edu.ems.domains.Teacher.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Page<Teacher> findByDepartmentId(Long departmentID, Pageable pageable);

    List<Teacher> findByDepartmentId(Long departmentID);

    @Query("SELECT t FROM Teacher t " +
        "JOIN User u ON t.id = u.id " +
        "WHERE u.firstName LIKE %?1% " +
        "OR u.phoneNumber LIKE %?1% " +
        "OR u.email LIKE %?1% " +
        "OR u.lastName LIKE %?1%")
    Page<Teacher> findByKeyword(String keyword, Pageable pageable);
}
