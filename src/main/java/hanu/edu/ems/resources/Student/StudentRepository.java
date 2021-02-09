package hanu.edu.ems.resources.Student;

import hanu.edu.ems.resources.Student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.firstName LIKE ?1")
    Page<Student> findByPartialStudentFirstName(String firstName, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.lastName LIKE ?1")
    Page<Student> findByPartialStudentLastName(String lastName, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Student findByStudentEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.phoneNumber = ?1")
    Student findByStudentPhoneNumber(String phoneNumber);

    @Query("SELECT s FROM Student s " +
            "WHERE s.firstName LIKE ?1 " +
            "OR s.phoneNumber LIKE ?1 " +
            "OR s.email LIKE ?1 " +
            "OR s.lastName LIKE ?1")
    Page<Student> findByKeyword(String keyword, Pageable pageable);
}
