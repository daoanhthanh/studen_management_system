package hanu.edu.ems.resources.Student;

import hanu.edu.ems.base.CRUDService;
import hanu.edu.ems.resources.Student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudentService extends CRUDService<Student, Long> {
    Page<Student> findByKeyWord(String keyword, Pageable pageRequest);

    Page<Student> getMany(Pageable pageRequest);
}
