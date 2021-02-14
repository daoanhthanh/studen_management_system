package hanu.edu.ems.domains.Student;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.domains.Student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudentService extends CRUDService<Student, Long> {
    Page<Student> findByKeyWord(String keyword, Pageable pageRequest);

    Page<Student> getMany(Pageable pageRequest);
}
