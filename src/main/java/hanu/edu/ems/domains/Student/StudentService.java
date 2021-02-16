package hanu.edu.ems.domains.Student;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface StudentService extends CRUDService<Student, Long> {

    Page<Student> findByDepartmentId(Long departmentId, Pageable pageable);

    Page<Student> findByCourseReleaseId(Long courseReleaseId, Pageable pageable);

    Page<Student> findByCourseId(Long courseId, Pageable pageable);

    Page<Student> findByKeyWord(String keyword, Pageable pageRequest);

    Page<Student> getMany(Pageable pageRequest);
}
